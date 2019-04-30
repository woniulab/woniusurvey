package com.woniuxy.survey.model;

import com.jfinal.plugin.activerecord.Model;

public class Question extends Model<Question> {
	public static final Question dao = new Question().dao();
}
