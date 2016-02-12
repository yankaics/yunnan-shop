package indi.zhangzqit.yunnanshop.dao;

import indi.zhangzqit.yunnanshop.model.User;

public interface UserDao extends BaseDao<User> {

	public User login(User user);
}
