package indi.zhangzqit.yunnanshop.dao;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Privilege;

public interface PrivilegeDao extends BaseDao<Privilege> {
	// ����Ȩ�޵ļ���,֧����״�ṹ��ʾ
	public List<Privilege> queryForTree();
}
