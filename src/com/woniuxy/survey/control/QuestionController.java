package com.woniuxy.survey.control;

import com.woniuxy.survey.core.CommonUtils;
import com.woniuxy.survey.model.Answer;
import com.woniuxy.survey.model.Question;
import com.woniuxy.survey.model.Survey;
import java.util.List;
import com.jfinal.core.Controller;

public class QuestionController extends Controller {
	
	public void index() {
		if (this.getSessionAttr("islogin") != "true") {
			render("/page/error.html");
			return;
		}
		
		int sid = getParaToInt(0);
		Survey survey = Survey.dao.findById(sid);
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
		
		render("/page/question.html");
	}
	
	public void add() {
		if (this.getSessionAttr("islogin") != "true") {
			render("/page/error.html");
			return;
		}
		
		int sid = getParaToInt("sid");
		String title = getPara("title");
		String type = getPara("type");
		String nowTime = CommonUtils.generateDateTime("yyyy-MM-dd HH:mm:ss");
		
		Question question = new Question();
		question.set("sid", sid);
		question.set("title", title);
		question.set("type", type);
		question.set("source", "新增");
		question.set("createtime", nowTime);
		boolean isSaved = question.save();
		if (isSaved) {
			if (type.equals("简答题")) {
				Answer answer = new Answer();
				answer.set("qid", question.getInt("qid"));
				answer.set("content", "#textarea#");
				answer.set("count", 0);
				answer.set("createtime", nowTime);
				boolean isSaved2 = answer.save();
				if (isSaved2) {
					renderText("add-successful");
				}
				else {
					renderText("add-failed");
				}
			}
			renderText("add-successful");
		}
		else {
			renderText("add-failed");
		}
	}
	
	public void close() {
		if (this.getSessionAttr("islogin") != "true") {
			render("/page/error.html");
			return;
		}
		
		int qid = getParaToInt("qid");
		boolean isUpdated = Question.dao.findById(qid).set("status", 0).update();
		if (isUpdated) {
			renderText("close-successful");
		}
		else {
			renderText("close-failed");
		}
	}
	
	public void start() {
		if (this.getSessionAttr("islogin") != "true") {
			render("/page/error.html");
			return;
		}
		
		int qid = getParaToInt("qid");
		boolean isUpdated = Question.dao.findById(qid).set("status", 1).update();
		if (isUpdated) {
			renderText("start-successful");
		}
		else {
			renderText("start-failed");
		}
	}
	
	public void edit() {
		if (this.getSessionAttr("islogin") != "true") {
			render("/page/error.html");
			return;
		}
		
		int qid = getParaToInt("qid");
		String title = getPara("title");
		boolean isUpdated = Question.dao.findById(qid).set("title", title).update();
		if (isUpdated) {
			renderText("edit-successful");
		}
		else {
			renderText("edit-failed");
		}
	}
}
