package com.woniuxy.survey.control;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.woniuxy.survey.model.Answer;
import com.woniuxy.survey.model.Question;
import com.woniuxy.survey.model.Result;
import com.woniuxy.survey.model.Survey;
import com.woniuxy.survey.model.User;

public class MainConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		PropKit.use("db.properties");
		me.setDevMode(true);
		me.setEncoding("utf-8");
		me.setViewType(ViewType.JFINAL_TEMPLATE);
	}
	
	@Override
	public void configPlugin(Plugins me) {
		DruidPlugin dp = new DruidPlugin(PropKit.get("db_url"), PropKit.get("db_username"), PropKit.get("db_password"));
		me.add(dp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		me.add(arp);
		arp.addMapping("user", "uid", User.class);
		arp.addMapping("survey", "sid", Survey.class);
		arp.addMapping("question", "qid", Question.class);
		arp.addMapping("answer", "aid", Answer.class);
		arp.addMapping("result", "rid", Result.class);
	}

	@Override
	public void configRoute(Routes me) {
		me.setBaseViewPath("/");
		me.add("/", IndexController.class);
		me.add("/survey", SurveyController.class);
		me.add("/question", QuestionController.class);
		me.add("/answer", AnswerController.class);
		me.add("/result", ResultController.class);
		me.add("/user", UserController.class);
	}

	@Override
	public void configEngine(Engine me) {
		
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("basePath"));
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new SessionInViewInterceptor());
	}

}