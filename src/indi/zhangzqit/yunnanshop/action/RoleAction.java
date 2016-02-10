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

	// �������û�и���Ȩ�޵�ʱ�򲻸��� privilege_role ����Ҫ�Ѹ��½�ɫ���ɫ����Ȩ�ֿ���ʵ��
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