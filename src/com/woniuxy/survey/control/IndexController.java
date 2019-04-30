package com.woniuxy.survey.control;

import java.util.List;

import com.jfinal.core.Controller;
import com.woniuxy.survey.model.Answer;
import com.woniuxy.survey.model.Question;
import com.woniuxy.survey.model.Result;
import com.woniuxy.survey.model.Survey;
import com.woniuxy.survey.model.User;

public class IndexController extends Controller {
	
	public void index() {
		if (this.getPara() == null || this.getPara().length() == 0) {
			if (getSessionAttr("islogin") == "true") {
				redirect("/survey");
			}
			else {
				render("/page/index.html");
			}
		}
		else {
			String woniuSessionId = getCookie("WoniuSessionID");
			if (woniuSessionId == null || woniuSessionId.length() == 0) {
//				String sqlSession = "select max(sessionid) sessionid from result";
//				String sessionId = String.valueOf(Result.dao.find(sqlSession).get(0).getInt("sessionid")+1);
//				setCookie("WoniuSessionID", sessionId, 5*24*60*60);  // 有效期3天
				
				String sqlSession = "select uid, username from user where role='sessionid'";
				User user = User.dao.find(sqlSession).get(0);
				int userSessionId = Integer.parseInt(user.getStr("username")) + 1;
				String sessionId = String.valueOf(userSessionId);
				setCookie("WoniuSessionID", sessionId, 5*24*60*60);  // 有效期3天
				User.dao.findById(user.getInt("uid")).set("username", userSessionId).update();
			}
			else {
				String sql = "select sessionid, ip from result where sessionid=? and ip=?";
				String ip = getRequest().getRemoteAddr();
				List<Result> resultList = Result.dao.find(sql, woniuSessionId, ip);
				if (resultList.size() > 0) {
					render("/page/repeat.html");
					return;
				}
			}
			
			int sid = getParaToInt(0);
			Survey survey = Survey.dao.findById(sid);
			
			if (survey == null) {
				render("/page/error.html");
				return;
			}
			
			if (survey.getInt("status") != 1) {
				render("/page/error.html");
				return;
			}
			
			survey.set("createtime", survey.get("createtime").toString().substring(0, 10));
			setAttr("survey", survey);
			
			String sqlQuestion = "select * from question where sid=?";
			List<Question> questionList = Question.dao.find(sqlQuestion, sid);
			for (Question question: questionList) {
				int qid = question.getInt("qid");
				String sqlAnswer = "select aid, qid, content from answer where qid=?";
				List<Answer> answerList = Answer.dao.find(sqlAnswer, qid);
				question.put("answerList", answerList);
			}
			setAttr("questionList", questionList);
			
			render("/page/survey.html");
		}
	}
	
	public void vcode() {
		renderCaptcha();
	}
	
}
