package indi.zhangzqit.yunnanshop.service;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Forder;
import indi.zhangzqit.yunnanshop.model.Product;
import indi.zhangzqit.yunnanshop.model.Sorder;

public interface SorderService extends BaseService<Sorder> {

	// �ѹ�������ӵ����ﳵ��,�ж���Ʒ�ظ�
	public Forder addSorder(Forder forder, Product product);

	public Forder alterSorder(Forder forder, Sorder sorder);

	public List<Object> querySaleTop10(int number);

}
