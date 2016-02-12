package indi.zhangzqit.yunnanshop.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import indi.zhangzqit.yunnanshop.model.Product;
import indi.zhangzqit.yunnanshop.service.ProductService;

@SuppressWarnings("unchecked")
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements
		ProductService {

	@Override
	public List<Product> queryJoinCategory(String name, int page, int rows) {
		return productDao.queryJoinCategory(name, page, rows);
	}

	@Override
	public Long count(String name) {
		return productDao.count(name);
	}

	@Override
	public void deleteByIds(String ids) {
		productDao.deleteByIds(ids);
	}

	@Override
	public List<Product> queryByCid(int cid) {
		return productDao.queryByCid(cid);
	}
}
