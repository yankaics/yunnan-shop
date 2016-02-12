package indi.zhangzqit.yunnanshop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import indi.zhangzqit.yunnanshop.dao.ProductDao;
import indi.zhangzqit.yunnanshop.model.Product;

@SuppressWarnings("unchecked")
@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

	@Override
	public List<Product> queryJoinCategory(String name, int page, int rows) {
		String hql = "FROM Product p LEFT JOIN FETCH p.category WHERE p.name LIKE :name";
		Session session = getSession();
		return session.createQuery(hql) //
				.setString("name", "%" + name + "%") //
				.setFirstResult((page - 1) * rows)//
				.setMaxResults(rows) //
				.list();
	}

	@Override
	public Long count(String name) {
		String hql = "SELECT COUNT(p) FROM Product p WHERE p.name LIKE :name";
		Session session = getSession();
		return (Long) session.createQuery(hql) //
				.setString("name", "%" + name + "%") //
				.uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
	}

	@Override
	public List<Product> queryByCid(int cid) {
		String hql = "FROM Product p LEFT JOIN FETCH p.category WHERE p.commend=true AND p.open=true AND p.category.id=:cid ORDER BY p.date DESC";
		return getSession().createQuery(hql).setFirstResult(0).setMaxResults(4)
				.setInteger("cid", cid).list(); //
	}
}
