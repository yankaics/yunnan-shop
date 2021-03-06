package indi.zhangzqit.yunnanshop.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import indi.zhangzqit.yunnanshop.model.Role;
import indi.zhangzqit.yunnanshop.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {

	@Override
	public List<Role> query(String name, int page, int rows) {
		return roleDao.query(name, page, rows);
	}

	@Override
	public Long count(String name) {
		return roleDao.count(name);
	}

	@Override
	public void deleteByIds(String ids) {
		roleDao.deleteByIds(ids);
	}

	@Override
	public Role getJoinPrvilege(int id) {
		return roleDao.getJoinPrvilege(id);
	}

	@Override
	public int[] getRoleId(Set<Role> roleSet) {
		int[] ids = new int[roleSet.size()];
		int num = 0;
		for (Role role : roleSet) {
			ids[num++] = role.getId();
		}
		return ids;
	}
}
