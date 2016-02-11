package indi.zhangzqit.yunnanshop.action;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import indi.zhangzqit.yunnanshop.model.PageModel;
import indi.zhangzqit.yunnanshop.service.AccountService;
import indi.zhangzqit.yunnanshop.service.CategoryService;
import indi.zhangzqit.yunnanshop.service.FileUploadUtil;
import indi.zhangzqit.yunnanshop.service.ForderService;
import indi.zhangzqit.yunnanshop.service.PayService;
import indi.zhangzqit.yunnanshop.service.PrivilegeService;
import indi.zhangzqit.yunnanshop.service.ProductService;
import indi.zhangzqit.yunnanshop.service.RoleService;
import indi.zhangzqit.yunnanshop.service.SendUtil;
import indi.zhangzqit.yunnanshop.service.SorderService;
import indi.zhangzqit.yunnanshop.service.UrlService;
import indi.zhangzqit.yunnanshop.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 *BaseAction用来编写所有的子Action公共代码,包括 model ServletConfigInterceptor: 此拦截器用来注入相应的
 * jsp内置对象,和相应的map。
 */
public class BaseAction<T> extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware, ModelDriven<T> {

	protected String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Resource
	// 默认名称为当前变量名称
	protected AccountService accountService;
	@Resource
	protected ProductService productService;
	@Resource
	protected FileUploadUtil fileUploadUtil;
	@Resource
	protected CategoryService categoryService;
	@Resource
	protected SorderService sorderService;
	@Resource
	protected ForderService forderService;
	@Resource
	protected UserService userService;
	@Resource
	protected PayService payService;
	@Resource
	protected RoleService roleService;
	@Resource
	protected UrlService urlService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected SendUtil sendUtil;

	// 在调用构造方法的时候给model赋值
	protected T model;

	protected List<T> jsonList = null;

	protected PageModel<T> pageModel = null;

	protected Integer page;

	protected Integer rows;

	public PageModel<T> getPageModel() {
		return pageModel;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public List<T> getJsonList() {
		return jsonList;
	}

	public void setJsonList(List<T> jsonList) {
		this.jsonList = jsonList;
	}

	public BaseAction() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	// 返回的对象将要压栈
	public T getModel() {
		return model;
	}

	protected Map<String, Object> request;

	protected Map<String, Object> session;

	protected Map<String, Object> application;

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}
}
