package indi.zhangzqit.yunnanshop.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import indi.zhangzqit.yunnanshop.model.Account;

public interface AccountDao extends BaseDao<Account> {

	public Account login(Account account);

	public List<Account> query(String name, int page, int rows);

	public Long count(String name);

	public void deleteByIds(String ids);

	public void updateHql(Account account);

	public Account getJoinRole(int id);

	public UserDetails loadUserByUsername(String username);
}
