package indi.zhangzqit.yunnanshop.dao;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Category;

public interface CategoryDao extends BaseDao<Category> {
	// ��ѯ���������Ա,֧�ַ�ҳ��ʾ
	public List<Category> queryJoinAccount(String type, int page, int rows);

	// ��ѯ�ܼ�¼��
	public Long count(String type);

	// ����id ��ֵɾ��������¼
	public void deleteByIds(String ids);

	public List<Category> queryByHot(boolean hot);
}
