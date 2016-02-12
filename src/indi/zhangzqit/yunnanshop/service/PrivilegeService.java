package indi.zhangzqit.yunnanshop.service;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Privilege;

public interface PrivilegeService extends BaseService<Privilege> {
	// 返回权限的集合,支持树状结构显示
	public List<Privilege> queryForTree();
}
