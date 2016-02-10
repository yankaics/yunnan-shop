package indi.zhangzqit.yunnanshop.action;

import indi.zhangzqit.yunnanshop.model.User;

public class UserAction extends BaseAction<User> {

	public UserAction() {
		System.out.println("******UserAction()*******");
	}

	public String login() {
		model = userService.login(model);
		if (model == null) {
			session.put("error", "登陆失败!");
			return "ulogin";
		} else {
			session.put("user", model);
			if (session.get("goURL") == null) {
				return "index";
			} else {
				return "goURL";
			}
		}
	}

	public String demo() {
		try {
			Integer.parseInt("xyz");
		} catch (Exception e) {
			throw new RuntimeException("类型出错了!");
		}
		return "index";
	}
}
