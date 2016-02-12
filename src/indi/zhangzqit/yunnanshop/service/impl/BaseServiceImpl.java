package indi.zhangzqit.yunnanshop.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import indi.zhangzqit.yunnanshop.dao.AccountDao;
import indi.zhangzqit.yunnanshop.dao.BaseDao;
import indi.zhangzqit.yunnanshop.dao.CategoryDao;
import indi.zhangzqit.yunnanshop.dao.ForderDao;
import indi.zhangzqit.yunnanshop.dao.PrivilegeDao;
import indi.zhangzqit.yunnanshop.dao.ProductDao;
import indi.zhangzqit.yunnanshop.dao.RoleDao;
import indi.zhangzqit.yunnanshop.dao.SorderDao;
import indi.zhangzqit.yunnanshop.dao.UrlDao;
import indi.zhangzqit.yunnanshop.dao.UserDao;
import indi.zhangzqit.yunnanshop.service.BaseService;

/**
 * ����˹���ҵ���߼���ʵ��, �������ҵ���߼���̳м���
 */
@SuppressWarnings("unchecked")
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {

	private Class clazz; // clazz�д洢�����൱ǰ����ʵ������

	protected BaseDao baseDao;

	@Resource(name = "accountDao")
	protected AccountDao accountDao;

	@Resource(name = "categoryDao")
	protected CategoryDao categoryDao;

	@Resource(name = "forderDao")
	protected ForderDao forderDao;

	@Resource(name = "productDao")
	protected ProductDao productDao;

	@Resource(name = "sorderDao")
	protected SorderDao sorderDao;

	@Resource(name = "userDao")
	protected UserDao userDao;

	@Resource(name = "roleDao")
	protected RoleDao roleDao;

	@Resource(name = "privilegeDao")
	protected PrivilegeDao privilegeDao;

	@Resource(name = "urlDao")
	protected UrlDao urlDao;

	public BaseServiceImpl() {
		System.out.println(this);
		System.out.println("��ȡ������Ϣ:" + this.getClass().getSuperclass());
		System.out.println("��ȡ������Ϣ����������Ϣ:"
				+ this.getClass().getGenericSuperclass());
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
		// �˴� dao����û��ʵ����, ����ʵ�ָ�baseDao�ĸ�ֵ����
	}

	@PostConstruct
	public void init() throws Exception {
		baseDao = categoryDao;
		// 1: ���ݾ���ķ���, ��ȡ��Ӧ��Field�ֶ�, categoryDao
		String clazzName = clazz.getSimpleName();
		String clazzDaoName = clazzName.substring(0, 1).toLowerCase()
				+ clazzName.substring(1) + "Dao";
		Field clazzField = this.getClass().getSuperclass().getDeclaredField(
				clazzDaoName);
		// 2: ��ȡbaseDao Filed�ֶ�
		Field baseField = this.getClass().getSuperclass().getDeclaredField(
				"baseDao");
		// 3: ��categoryDao��ֵ��ֵ��baseDao
		baseField.set(this, clazzField.get(this));
		System.out.println("baseDao:" + baseDao);
	}

	@Override
	public void save(T t) {
		baseDao.save(t);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public void delete(int id) {
		baseDao.delete(id);
	}

	@Override
	public List<T> query() {
		return baseDao.query();
	}

	@Override
	public T get(int id) {
		return (T) baseDao.get(id);
	}

}
