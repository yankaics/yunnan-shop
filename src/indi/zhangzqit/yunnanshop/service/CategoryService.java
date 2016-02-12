package indi.zhangzqit.yunnanshop.service;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Category;

public interface CategoryService extends BaseService<Category> {
	// ��ѯ���������Ա,֧�ַ�ҳ��ʾ
	public List<Category> queryJoinAccount(String type, int page, int rows);

	// ��ѯ�ܼ�¼��
	public Long count(String type);

	// ����id ��ֵɾ��������¼
	public void deleteByIds(String ids);

	// ��ѯ�ȵ�/���ȵ����
	public List<Category> queryByHot(boolean hot);
}
