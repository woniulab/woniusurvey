package com.woniuxy.survey.control;

import com.woniuxy.survey.core.CommonUtils;
import com.woniuxy.survey.model.Answer;
import com.woniuxy.survey.model.Question;
import com.woniuxy.survey.model.Result;
import com.woniuxy.survey.model.Survey;
import com.woniuxy.survey.model.User;

import java.util.List;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class SurveyController extends Controller {
	
	public void index() {
		if (this.getPara() == null || this.getPara().length() == 0) {
			List<Record> surveyList = null;
			if (this.getSessionAttr("islogin") == "true") {
				System.out.println(getSessionAttr("username").toString());
				if (getSessionAttr("username").toString().equals("admin")) {
					String sqlSurvey = "select sid, branch, classid, status, left(createtime, 10) createtime from survey order by sid desc";
					surveyList = Db.find(sqlSurvey);
				}
				else {
					String sqlSurvey = "select sid, branch, classid, status, left(createtime, 10) createtime from survey where branch=? order by sid desc";
					if (getSessionAttr("username").toString().equals("chengdu")) {
						surveyList = Db.find(sqlSurvey, "成都");
					}
					else if (getSessionAttr("username").toString().equals("chongqing")) {
						surveyList = Db.find(sqlSurvey, "重庆");
					}
					else if (getSessionAttr("username").toString().equals("xian")) {
						surveyList = Db.find(sqlSurvey, "西安");
					}
					else if (getSessionAttr("username").toString().equals("shanghai")) {
						surveyList = Db.find(sqlSurvey, "上海");
					}
				}
					
				setAttr("surveyList", surveyList);
				render("/page/survey-admin.html");
			}
			else {
				render("/page/login.html");
			}
		}
		else {
			// 如果没有Cookie，则表明是第一次进行调查，则利用SessionID来生成Cookie，结合IP地址进行重复判断
			String woniuSessionId = getCookie("WoniuSessionID");
			if (woniuSessionId == null || woniuSessionId.length() == 0) {
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
	
	public void add() {
		if (this.getSessionAttr("islogin") != "true") {
			render("/page/error.html");
			return;
		}
		
		String branch = getPara("branch");
		String classid = getPara("classid");
		String nowTime = CommonUtils.generateDateTime("yyyy-MM-dd HH:mm:ss");
		
		Survey survey = new Survey();
		survey.set("branch", branch);
		survey.set("classid", classid);
		survey.set("status", "1");
		survey.set("createtime", nowTime);
		boolean isSaved = survey.save();
		
		if (isSaved) {
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
		
		String sid = getPara("sid");
		boolean isUpdated = Survey.dao.findById(sid).set("status", 0).update();
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
		
		String sid = getPara("sid");
		boolean isUpdated = Survey.dao.findById(sid).set("status", 1).update();
		if (isUpdated) {
			renderText("start-successful");
		}
		else {
			renderText("start-failed");
		}
	}
	
	public void copy() {
		if (this.getSessionAttr("islogin") != "true") {
			render("/page/error.html");
			return;
		}
		
		String sourceSid = getPara("sid");
		String branch = getPara("branch");
		String classid = getPara("classid");
		String nowTime = CommonUtils.generateDateTime("yyyy-MM-dd HH:mm:ss");
		
		Survey survey = new Survey();
		survey.set("branch", branch);
		survey.set("classid", classid);
		survey.set("status", "1");
		survey.set("createtime", nowTime);
		boolean isSaved1 = survey.save();
		if (isSaved1) {
			int sid = survey.getInt("sid");
			String sqlQuestion = "select qid, title, type from question where sid=?";
			List<Question> sourceQuestionList = Question.dao.find(sqlQuestion, sourceSid);
			for (Question sourceQuestion: sourceQuestionList) {
				Question question = new Question();
				question.set("sid", sid);
				question.set("title", sourceQuestion.getStr("title"));
				question.set("type", sourceQuestion.getStr("type"));
				question.set("source", "复制"+sourceSid);
				question.set("createtime", nowTime);
				boolean isSaved2 = question.save();
				if (isSaved2) {
					int sourceQid = sourceQuestion.getInt("qid");
					String sqlAnswer = "select aid, content from answer where qid=?";
					List<Answer> sourceAnswerList = Answer.dao.find(sqlAnswer, sourceQid);
					for (Answer sourceAnswer: sourceAnswerList) {
						Answer answer = new Answer();
						answer.set("qid", question.getInt("qid"));
						answer.set("content", sourceAnswer.getStr("content"));
						answer.set("count", 0);
						answer.set("createtime", nowTime);
						boolean isSaved3 = answer.save();
						if (isSaved3) {
							renderText("copy-successful");
						}
						else {
							renderText("copy-failed");
						}
					}
				}
				else {
					renderText("copy-failed");
				}
			}
		}
		else {
			renderText("copy-failed");
		}
	}
}
