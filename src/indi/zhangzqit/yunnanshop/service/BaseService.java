package indi.zhangzqit.yunnanshop.service;

import java.util.List;

public interface BaseService<T> {

	public void save(T t);

	public void update(T t);

	public void delete(int id);

	// ������ѯ��ǰ����, ��֧�ּ�����ѯ
	public List<T> query();

	public T get(int id);
}
