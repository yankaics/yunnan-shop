package indi.zhangzqit.yunnanshop.action;

import java.util.HashSet;
import java.util.Set;

import indi.zhangzqit.yunnanshop.model.PageModel;
import indi.zhangzqit.yunnanshop.model.Privilege;
import indi.zhangzqit.yunnanshop.model.Role;

public class RoleAction extends BaseAction<Role> {

	public String query() {
		pageModel = new PageModel<Role>();
		pageModel.setRows(roleService.query(model.getName(), page, rows));
		pageModel.setTotal(roleService.count(model.getName()));
		return "pageModel";
	}

	public void save() {
		roleService.save(model);
	}

	public void deleteByIds() {
		roleService.deleteByIds(ids);
	}

	// 如果想在没有更新权限的时候不更新 privilege_role 则需要把更新角色与角色的授权分开来实现
	public void update() {
		Set<Privilege> privilegeSet = null;
		if (ids != "") {
			privilegeSet = new HashSet<Privilege>();
			for (String temp : ids.split(",")) {
				privilegeSet.add(new Privilege(Integer.parseInt(temp)));
			}
		}
		model.setPrivilegeSet(privilegeSet);
		roleService.update(model);
		System.out.println("**************");
		urlService.getAllRoles();
	}
}