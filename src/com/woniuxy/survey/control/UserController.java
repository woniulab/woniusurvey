package com.woniuxy.survey.control;

import java.util.List;
import com.jfinal.core.Controller;
import com.woniuxy.survey.model.User;

public class UserController extends Controller {
	
	public void login() {
		String username = getPara("username");
		String password = getPara("password");
		boolean isVerified = validateCaptcha("verifycode");
		if (isVerified) {
			String sql = "select * from user where username=? and password=?";
			List<User> list = User.dao.find(sql, username, password);
			if (list.size() == 1) {
				setSessionAttr("islogin", "true");
				setSessionAttr("uid", list.get(0).getInt("userid"));
				setSessionAttr("username", list.get(0).getStr("username"));
				setSessionAttr("role", list.get(0).getStr("role"));
				renderText("login-pass");
				return;
			}
			else {
				renderText("login-fail");	// 登录失败
				return;
			}
		}
		else {
			renderText("vcode-error");	// 验证码错误
			return;
		}
	}
	
	public void logout() {
		removeSessionAttr("islogin");
		removeSessionAttr("uid");
		removeSessionAttr("username");
		removeSessionAttr("role");
		renderText("logout-pass");
		return;
	}
}
