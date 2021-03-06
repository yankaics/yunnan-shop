package indi.zhangzqit.yunnanshop.service;

import java.util.List;

public interface BaseService<T> {

	public void save(T t);

	public void update(T t);

	public void delete(int id);

	// 仅仅查询当前对象, 不支持级联查询
	public List<T> query();

	public T get(int id);
}
