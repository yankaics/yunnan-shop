package indi.zhangzqit.yunnanshop.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import indi.zhangzqit.yunnanshop.model.Category;
import indi.zhangzqit.yunnanshop.service.CategoryService;

@SuppressWarnings("unchecked")
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements
		CategoryService {

	@Override
	public List<Category> queryJoinAccount(String type, int page, int rows) {
		return categoryDao.queryJoinAccount(type, page, rows);
	}

	@Override
	public Long count(String type) {
		return categoryDao.count(type);
	}

	@Override
	public void deleteByIds(String ids) {
		categoryDao.deleteByIds(ids);
	}

	@Override
	public List<Category> queryByHot(boolean hot) {
		return categoryDao.queryByHot(hot);
	}
}
