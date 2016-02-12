package indi.zhangzqit.yunnanshop.service;

import java.util.List;
import java.util.Set;

import indi.zhangzqit.yunnanshop.model.Role;

public interface RoleService extends BaseService<Role> {

	public List<Role> query(String name, int page, int rows);

	public Long count(String name);

	public void deleteByIds(String ids);

	public Role getJoinPrvilege(int id);

	public int[] getRoleId(Set<Role> roleSet);
}
