package indi.zhangzqit.yunnanshop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import indi.zhangzqit.yunnanshop.dao.SorderDao;
import indi.zhangzqit.yunnanshop.model.Sorder;

@Repository("sorderDao")
@SuppressWarnings("unchecked")
public class SorderDaoImpl extends BaseDaoImpl<Sorder> implements SorderDao {

	@Override
	public List<Object> querySaleTop10(int number) {
		String hql = "SELECT p.name,sum(s.number) FROM Sorder s LEFT JOIN s.product p GROUP BY p.id";
		return getSession().createQuery(hql).setFirstResult(0).setMaxResults(
				number).list();
	}

}
