package indi.zhangzqit.yunnanshop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import indi.zhangzqit.yunnanshop.dao.RoleDao;
import indi.zhangzqit.yunnanshop.model.Role;

@SuppressWarnings("unchecked")
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public List<Role> query(String name, int page, int rows) {
		String hql = "FROM Role r WHERE r.name LIKE :name";
		Session session = getSession();
		return session.createQuery(hql) //
				.setString("name", "%" + name + "%") //
				.setFirstResult((page - 1) * rows)//
				.setMaxResults(rows) //
				.list();
	}

	@Override
	public Long count(String name) {
		String hql = "SELECT COUNT(r) FROM Role r WHERE r.name LIKE :name";
		Session session = getSession();
		return (Long) session.createQuery(hql) //
				.setString("name", "%" + name + "%") //
				.uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		Session session = getSession();
		for (String id : ids.split(",")) {
			session.delete(new Role(Integer.parseInt(id)));
		}
	}

	@Override
	public Role getJoinPrvilege(int id) {
		String hql = "FROM Role r LEFT JOIN FETCH r.privilegeSet WHERE r.id=:id";
		return (Role) getSession().createQuery(hql) //
				.setInteger("id", id).uniqueResult();
	}
}
