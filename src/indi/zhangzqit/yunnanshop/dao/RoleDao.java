package indi.zhangzqit.yunnanshop.dao;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Role;

public interface RoleDao extends BaseDao<Role> {

	public List<Role> query(String name, int page, int rows);

	public Long count(String name);

	public void deleteByIds(String ids);

	public Role getJoinPrvilege(int id);
}
