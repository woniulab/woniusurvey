package com.woniuxy.survey.model;

import com.jfinal.plugin.activerecord.Model;

public class Survey extends Model<Survey> {
	public static final Survey dao = new Survey().dao();
}
