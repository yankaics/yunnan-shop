package indi.zhangzqit.yunnanshop.service;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Privilege;

public interface PrivilegeService extends BaseService<Privilege> {
	// ����Ȩ�޵ļ���,֧����״�ṹ��ʾ
	public List<Privilege> queryForTree();
}
