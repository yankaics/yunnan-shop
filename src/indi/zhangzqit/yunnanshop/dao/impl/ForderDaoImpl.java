package indi.zhangzqit.yunnanshop.dao.impl;

import org.springframework.stereotype.Repository;

import indi.zhangzqit.yunnanshop.dao.ForderDao;
import indi.zhangzqit.yunnanshop.model.Forder;

@Repository("forderDao")
public class ForderDaoImpl extends BaseDaoImpl<Forder> implements ForderDao {

	@Override
	public void updateStatus(int id, int sid) {
		String hql = "update Forder f SET f.status.id=:sid WHERE f.id=:id";
		getSession().createQuery(hql).setInteger("id", id)//
				.setInteger("sid", sid)//
				.executeUpdate();
	}

}
