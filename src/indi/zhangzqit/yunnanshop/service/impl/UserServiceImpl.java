package indi.zhangzqit.yunnanshop.service.impl;

import org.springframework.stereotype.Service;

import indi.zhangzqit.yunnanshop.model.User;
import indi.zhangzqit.yunnanshop.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

}
