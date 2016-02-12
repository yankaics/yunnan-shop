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
 * 完成了公共业务逻辑的实现, 具体的子业务逻辑类继承即可
 */
@SuppressWarnings("unchecked")
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {

	private Class clazz; // clazz中存储了子类当前操作实体类型

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
		System.out.println("获取父类信息:" + this.getClass().getSuperclass());
		System.out.println("获取父类信息包括泛型信息:"
				+ this.getClass().getGenericSuperclass());
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
		// 此处 dao还有没有实例化, 不能实现给baseDao的赋值操作
	}

	@PostConstruct
	public void init() throws Exception {
		baseDao = categoryDao;
		// 1: 根据具体的泛型, 获取相应的Field字段, categoryDao
		String clazzName = clazz.getSimpleName();
		String clazzDaoName = clazzName.substring(0, 1).toLowerCase()
				+ clazzName.substring(1) + "Dao";
		Field clazzField = this.getClass().getSuperclass().getDeclaredField(
				clazzDaoName);
		// 2: 获取baseDao Filed字段
		Field baseField = this.getClass().getSuperclass().getDeclaredField(
				"baseDao");
		// 3: 把categoryDao的值赋值给baseDao
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
