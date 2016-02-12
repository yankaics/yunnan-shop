package indi.zhangzqit.yunnanshop.service;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Product;

public interface ProductService extends BaseService<Product> {
	// ��ѯ���������Ա,֧�ַ�ҳ��ʾ
	public List<Product> queryJoinCategory(String name, int page, int rows);

	// ��ѯ�ܼ�¼��
	public Long count(String name);

	// ����id ��ֵɾ��������¼
	public void deleteByIds(String ids);

	// �����ȵ������,��ѯ��ǰ�����Ƽ���Ʒ
	public List<Product> queryByCid(int cid);
}
