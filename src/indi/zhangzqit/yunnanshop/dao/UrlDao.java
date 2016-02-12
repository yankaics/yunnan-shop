package indi.zhangzqit.yunnanshop.dao;

import indi.zhangzqit.yunnanshop.model.Url;

public interface UrlDao extends BaseDao<Url> {

	public Url getRoleByUrl(String url);

	public void getAllRoles();
}
