package indi.zhangzqit.yunnanshop.service;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Forder;
import indi.zhangzqit.yunnanshop.model.Product;
import indi.zhangzqit.yunnanshop.model.Sorder;

public interface SorderService extends BaseService<Sorder> {

	// 把购物项添加到购物车中,判断商品重复
	public Forder addSorder(Forder forder, Product product);

	public Forder alterSorder(Forder forder, Sorder sorder);

	public List<Object> querySaleTop10(int number);

}
