package indi.zhangzqit.yunnanshop.service;

import indi.zhangzqit.yunnanshop.model.User;

public interface UserService extends BaseService<User> {

	public User login(User user);
}
