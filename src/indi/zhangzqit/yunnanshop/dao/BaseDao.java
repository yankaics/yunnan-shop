package indi.zhangzqit.yunnanshop.dao;

import java.util.List;

public interface BaseDao<T> {

	public void save(T t);

	public void update(T t);

	public void delete(int id);

	// ������ѯ��ǰ����, ��֧�ּ�����ѯ
	public List<T> query();

	public T get(int id);
}
