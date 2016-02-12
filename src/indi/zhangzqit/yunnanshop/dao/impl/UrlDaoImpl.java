package indi.zhangzqit.yunnanshop.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import indi.zhangzqit.yunnanshop.dao.UrlDao;
import indi.zhangzqit.yunnanshop.model.Url;

@Repository("urlDao")
public class UrlDaoImpl extends BaseDaoImpl<Url> implements UrlDao {

	private Map<String, Url> urlMap = new HashMap<String, Url>();

	@PostConstruct
	// ��ȡ���е� url��ַ �� URL���� ��ӳ��ؼ�
	public void getAllRoles() {
		Session session = sessionFactory.openSession();
		String hql = "FROM Url u JOIN FETCH u.privilege up JOIN FETCH up.roleSet";
		List<Url> urlLists = (List) session.createQuery(hql).list();
		for (Url o : urlLists) {
			System.out.println("������ַΪ:" + o.getUrl() + ", ��Ӧ�Ľ�ɫ����Ϊ:"
					+ o.getPrivilege().getRoleSet());
			urlMap.put(o.getUrl(), o);
		}
		session.close();
	}

	// ͨ��URL��ַ��ȡ��ӦȨ��Ȼ���ڻ�ȡ��Ӧ�Ľ�ɫ����
	public Url getRoleByUrl(String url) {
		return urlMap.get(url);
	}
}
