package com.woniuxy.survey.control;

import com.woniuxy.survey.core.CommonUtils;
import com.woniuxy.survey.model.Answer;
import com.woniuxy.survey.model.Question;
import com.woniuxy.survey.model.Result;
import com.woniuxy.survey.model.Survey;

import java.util.Iterator;
import java.util.List;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;
import org.json.*;

public class ResultController extends Controller {
	
	public void index() {
		if (this.getPara() == null || this.getPara().length() == 0) {
			render("/page/error.html");
			return;
		}
		
		if (this.getSessionAttr("islogin") != "true") {
			render("/page/error.html");
			return;
		}
		
		/*
		 * select question.qid, question.title, question.type, answer.aid, answer.content, answer.count, 
			result.rid, result.answer from question, answer, result where question.qid=answer.qid 
			and answer.aid=result.aid and question.sid=4 and question.qid=6
		 */
		
		String sid = getPara(0);
		Survey survey = Survey.dao.findById(sid);
		survey.set("createtime", survey.get("createtime").toString().substring(0, 10));
		setAttr("survey", survey);
		
		String sqlQuestion = "select * from question where sid=?";
		List<Question> questionList = Question.dao.find(sqlQuestion, sid);
		for (Question question: questionList) {
			int qid = question.getInt("qid");
			String sqlAnswer = "select aid, qid, content, count from answer where qid=?";
			List<Answer> answerList = Answer.dao.find(sqlAnswer, qid);
			for (Answer answer: answerList) {
				String sqlTextArea = "select answer from result where aid=?";
				List<Result> textAreaList = Result.dao.find(sqlTextArea, answer.getInt("aid"));
				answer.put("textAreaList", textAreaList);
			}
			question.put("answerList", answerList);
			
			// 如果某个问题没有答案时，则不管
			try {
				String sqlCount = "select sum(count) totalcount from answer where qid=? group by qid";
				int totalCount = Answer.dao.find(sqlCount, qid).get(0).getInt("totalcount");
				question.put("totalCount", totalCount);
			}
			catch(Exception e) {
				
			}
		}

		setAttr("questionList", questionList);
		render("/page/result.html");
	}
	
	public void add() {
		String woniuSessionId = getCookie("WoniuSessionID");
		String ip = getRequest().getRemoteAddr();
		String resultStr = getPara("result");
		String nowTime = CommonUtils.generateDateTime("yyyy-MM-dd HH:mm:ss");
		System.out.println(resultStr);
		try {
			JSONObject jo = new JSONObject(resultStr);
			// System.out.println(jo.get("question-checkbox-3").toString());
			Iterator it = jo.keys();
			while (it.hasNext()) {
				String key = it.next().toString();
				String value = jo.get(key).toString();
				String qid = key.split("-")[2];
				if (key.contains("radio")) {
					//  新增一条调查结果明细记录
					Result result = new Result();
					result.set("qid", qid);
					result.set("aid", value);
					result.set("answer", "1");
					result.set("sessionid", woniuSessionId);
					result.set("ip", ip);
					result.set("createtime", nowTime);
					result.save();
					// 更新answer表中的count字段的值
					int count = Answer.dao.findById(value).getInt("count");
					Answer.dao.findById(value).set("count", count+1).update();
				}
				else if (key.contains("checkbox")) {
					String[] aidValues = value.split("##");
					for (int i=0; i<aidValues.length; i++) {
						Result result = new Result();
						result.set("qid", qid);
						result.set("aid", aidValues[i]);
						result.set("answer", "1");
						result.set("sessionid", woniuSessionId);
						result.set("ip", ip);
						result.set("createtime", nowTime);
						result.save();
						
						int count = Answer.dao.findById(aidValues[i]).getInt("count");
						Answer.dao.findById(aidValues[i]).set("count", count+1).update();
					}
				}
				else {
					Result result = new Result();
					result.set("qid", qid);
					result.set("aid", value.split("##")[0]);
					result.set("answer", this.filter(value.split("##")[1]));
					result.set("sessionid", woniuSessionId);
					result.set("ip", ip);
					result.set("createtime", nowTime);
					result.save();
					
					int count = Answer.dao.findById(value.split("##")[0]).getInt("count");
					Answer.dao.findById(value.split("##")[0]).set("count", count+1).update();
				}
			}
		}
		catch (Exception e) {
			
		}
		renderText("sumbit-successful");
	}
	
	public void done() {
		render("/page/done.html");
	}
	
	// 过滤HTML和JS字符
	public String filter(String message){
    	if(message==null)
            return null ;
        char content[]=new char[message.length()];
        message.getChars(0, message.length(), content,0);
        StringBuilder result=new StringBuilder(content.length+50);
        for(int i=0;i<content.length;i++){
            //控制对尖括号等特殊字符进行转义
            switch(content[i]){
            case '<':
            result.append("&lt;");
            break;
            case '>':
                result.append("&gt;");
                break;
            case '&':
                result.append("&amp;");
                break;
            case '"':
                result.append("&quot;");
                break;
            default:
                result.append(content[i]);
            }
             
        }
        return (result.toString());
    }
}
