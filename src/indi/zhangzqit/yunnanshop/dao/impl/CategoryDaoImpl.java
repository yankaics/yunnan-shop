package indi.zhangzqit.yunnanshop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import indi.zhangzqit.yunnanshop.dao.CategoryDao;
import indi.zhangzqit.yunnanshop.model.Category;

@SuppressWarnings("unchecked")
@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements
		CategoryDao {

	@Override
	public List<Category> queryJoinAccount(String type, int page, int rows) {
		String hql = "FROM Category c LEFT JOIN FETCH c.account WHERE c.type LIKE :type";
		Session session = getSession();
		List<Category> categoryList = session.createQuery(hql) //
				.setString("type", "%" + type + "%") //
				.setFirstResult((page - 1) * rows)//
				.setMaxResults(rows) //
				.list();
		return categoryList;
	}

	@Override
	public Long count(String type) {
		String hql = "SELECT COUNT(c) FROM Category c WHERE c.type LIKE :type";
		Session session = getSession();
		return (Long) session.createQuery(hql) //
				.setString("type", "%" + type + "%") //
				.uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Category WHERE id in (" + ids + ")";
		Session session = getSession();
		session.createQuery(hql) //
				.executeUpdate();
	}

	@Override
	public List<Category> queryByHot(boolean hot) {
		String hql = "FROM Category c WHERE c.hot=:hot";
		return getSession().createQuery(hql) //
				.setBoolean("hot", hot) //
				.list();
	}
}
