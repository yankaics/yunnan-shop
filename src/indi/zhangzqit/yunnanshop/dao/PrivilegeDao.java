package indi.zhangzqit.yunnanshop.dao;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Privilege;

public interface PrivilegeDao extends BaseDao<Privilege> {
	// 返回权限的集合,支持树状结构显示
	public List<Privilege> queryForTree();
}
