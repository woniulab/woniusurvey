package com.woniuxy.survey.control;

import java.util.List;
import com.jfinal.core.Controller;
import com.woniuxy.survey.core.CommonUtils;
import com.woniuxy.survey.model.Answer;
import com.woniuxy.survey.model.Result;

public class AnswerController extends Controller {
	
	public void index() {
		render("/page/error.html");
	}
	
	public void add() {
		if (this.getSessionAttr("islogin") != "true") {
			render("/page/error.html");
			return;
		}
		
		int qid = getParaToInt("qid");
		String content = getPara("content");
		String nowTime = CommonUtils.generateDateTime("yyyy-MM-dd HH:mm:ss");
		
		Answer answer = new Answer();
		answer.set("qid", qid);
		answer.set("content", content);
		answer.set("count", 0);
		answer.set("createtime", nowTime);
		boolean isSaved = answer.save();
		if (isSaved) {
			renderText(answer.getInt("aid").toString());
		}
		else {
			renderText("add-failed");
		}
	}
	
	public void delete() {
		if (this.getSessionAttr("islogin") != "true") {
			render("/page/error.html");
			return;
		}
		
		int aid = getParaToInt("aid");
		String sql = "select * from result where aid=?";
		List<Result> resultList = Result.dao.find(sql, aid);
		if (resultList == null || resultList.size() == 0) {
			boolean isDeleted = Answer.dao.findById(aid).delete();
			if (isDeleted) {
				renderText("delete-successful");
			}
			else {
				renderText("delete-failed");
			}
		}
		else {
			renderText("cannot-delete");
		}
	}
	
	public void edit() {
		if (this.getSessionAttr("islogin") != "true") {
			render("/page/error.html");
			return;
		}
		
		int aid = getParaToInt("aid");
		String content = getPara("content");
		boolean isUpdated = Answer.dao.findById(aid).set("content", content).update();
		if (isUpdated) {
			renderText("edit-successful");
		}
		else {
			renderText("edit-failed");
		}
	}
}
