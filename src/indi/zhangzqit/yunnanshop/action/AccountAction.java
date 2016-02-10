package indi.zhangzqit.yunnanshop.action;

import java.util.HashSet;
import java.util.Set;

import indi.zhangzqit.yunnanshop.model.Account;
import indi.zhangzqit.yunnanshop.model.PageModel;
import indi.zhangzqit.yunnanshop.model.Role;

public class AccountAction extends BaseAction<Account> {

	private static final long serialVersionUID = -515496382921247138L;

	private Integer[] rids;

	public Integer[] getRids() {
		return rids;
	}

	public void setRids(Integer[] rids) {
		this.rids = rids;
	}

	public void save() {
		accountService.save(model);
	}

	public void updateHql() {
		System.out.println(model);
		accountService.updateHql(model);
	}

	public void grantRole() {
		System.out.println(model);
		model = accountService.get(model.getId());
		Set<Role> roleSet = null;
		if (rids != null) {
			roleSet = new HashSet<Role>();
			for (int rid : rids) {
				roleSet.add(new Role(rid));
			}
		}
		model.setRoleSet(roleSet);
		accountService.update(model);
	}

	public void deleteByIds() {
		accountService.deleteByIds(ids);
	}

	public String queryForPage() {
		pageModel = new PageModel<Account>();
		pageModel.setRows(accountService.query(model.getLogin(), page, rows));
		pageModel.setTotal(accountService.count(model.getLogin()));
		return "pageModel";
	}

	public String getAccount() {
		System.out.println(model.getId());
		model = accountService.getJoinRole(model.getId());
		request.put("myRids", roleService.getRoleId(model.getRoleSet()));
		request.put("roleList", roleService.query());
		return "grantRole";
	}

	public String query() {
		jsonList = accountService.query();
		System.out.println("jsonList:" + jsonList);
		return "jsonList";
	}
}
