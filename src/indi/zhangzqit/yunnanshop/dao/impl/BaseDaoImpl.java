package indi.zhangzqit.yunnanshop.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import indi.zhangzqit.yunnanshop.dao.BaseDao;

/**
 * ����˹���ҵ���߼���ʵ��, �������ҵ���߼���̳���
 */
@SuppressWarnings("unchecked")
@Repository("baseDao")
@Lazy(true)
public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class clazz; // clazz�д洢�����൱ǰ����ʵ������

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	public BaseDaoImpl() {
		// ���������õ�ǰ���췽��,this��������������
		System.out.println(this);
		System.out.println("��ȡ������Ϣ:" + this.getClass().getSuperclass());
		System.out.println("��ȡ������Ϣ����������Ϣ:"
				+ this.getClass().getGenericSuperclass());
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
		System.out.println("clazz:" + clazz);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(int id) {
		String hql = "DELETE FROM " + clazz.getSimpleName()
				+ " c WHERE c.id=:id";
		getSession().createQuery(hql)//
				.setInteger("id", id)//
				.executeUpdate();
	}

	@Override
	public List<T> query() {
		String hql = "FROM " + clazz.getSimpleName();
		return getSession().createQuery(hql) //
				.list();
	}

	@Override
	public T get(int id) {
		return (T) getSession().get(clazz, id);
	}
}
