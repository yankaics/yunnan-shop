package indi.zhangzqit.yunnanshop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import indi.zhangzqit.yunnanshop.dao.PrivilegeDao;
import indi.zhangzqit.yunnanshop.model.Privilege;

@SuppressWarnings("unchecked")
@Repository("privilegeDao")
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements
		PrivilegeDao {

	@Override
	public List<Privilege> queryForTree() {
		// 1: ��ѯ���и��˵�
		String hql = "FROM Privilege p WHERE p.parent IS NULL";
		// 2: ��ѯĳ�����˵�������Ӧ�Ӳ˵�, ��һ�Զ�Ĳ�ѯ��, һ������ظ���,��DISTINCT ȥ����
		hql = "SELECT DISTINCT p FROM Privilege p LEFT JOIN FETCH p.children WHERE p.id=1";
		// 3: ��ѯ���˵�������Ӧ���Ӳ˵�
		hql = "SELECT DISTINCT p FROM Privilege p LEFT JOIN FETCH p.children WHERE p.parent IS NULL";
		return getSession().createQuery(hql).list();
	}
}
