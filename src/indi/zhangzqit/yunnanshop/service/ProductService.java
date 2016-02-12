package indi.zhangzqit.yunnanshop.service;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Product;

public interface ProductService extends BaseService<Product> {
	// 查询类别级联管理员,支持分页显示
	public List<Product> queryJoinCategory(String name, int page, int rows);

	// 查询总记录数
	public Long count(String name);

	// 根据id 数值删除多条记录
	public void deleteByIds(String ids);

	// 根据热点类别编号,查询当前类别的推荐商品
	public List<Product> queryByCid(int cid);
}
