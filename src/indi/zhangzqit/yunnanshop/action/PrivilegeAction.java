package indi.zhangzqit.yunnanshop.action;

import indi.zhangzqit.yunnanshop.model.Privilege;
import indi.zhangzqit.yunnanshop.model.Role;

public class PrivilegeAction extends BaseAction<Privilege> {

	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String queryForTree() {
		System.out.println(role.getId());
		role = roleService.getJoinPrvilege(role.getId());
		System.out.println(role.getPrivilegeSet().size());
		jsonList = privilegeService.queryForTree();
		// ʵ��Ȩ����Ϣ�Ļ���
		for (Privilege myPrivilege : role.getPrivilegeSet()) {
			for (Privilege parent : jsonList) {
				boolean isOk = false;
				// ��ȡ��ǰ���˵����Ӳ˵�, ֻ��Ҫ��tree���Ӳ˵��Ƚϼ���
				for (Privilege children : parent.getChildren()) {
					if (myPrivilege.getId().equals(children.getId())) {
						children.setChecked(true);
						isOk = true;
						break;
					}
				}
				if (isOk == true) {
					break;
				}
			}
		}

		return "jsonList";
	}
}
