package indi.zhangzqit.yunnanshop.dao;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Forder;
import indi.zhangzqit.yunnanshop.model.Product;
import indi.zhangzqit.yunnanshop.model.Sorder;

public interface SorderDao extends BaseDao<Sorder> {
	// ��ѯ��Ʒ����ǰ10��
	public List<Object> querySaleTop10(int number);
}
