package indi.zhangzqit.yunnanshop.service;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Account;

public interface AccountService extends BaseService<Account> {

	public Account login(Account account);

	public List<Account> query(String name, int page, int rows);

	public Long count(String name);

	public void deleteByIds(String ids);

	public void updateHql(Account account);

	public Account getJoinRole(int id);
}
