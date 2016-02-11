package indi.zhangzqit.yunnanshop.dao;

import java.util.List;

import indi.zhangzqit.yunnanshop.model.Category;

public interface CategoryDao extends BaseDao<Category> {
	// 查询类别级联管理员,支持分页显示
	public List<Category> queryJoinAccount(String type, int page, int rows);

	// 查询总记录数
	public Long count(String type);

	// 根据id 数值删除多条记录
	public void deleteByIds(String ids);

	public List<Category> queryByHot(boolean hot);
}
