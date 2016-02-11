package indi.zhangzqit.yunnanshop.dao;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Product;

public interface ProductDao extends BaseDao<Product> {
	// ��ѯ���������Ա,֧�ַ�ҳ��ʾ
	public List<Product> queryJoinCategory(String name, int page, int rows);

	// ��ѯ�ܼ�¼��
	public Long count(String name);

	// ����id ��ֵɾ��������¼
	public void deleteByIds(String ids);

	public List<Product> queryByCid(int cid);
}
