package indi.zhangzqit.yunnanshop.dao;

import java.math.BigDecimal;

import indi.zhangzqit.yunnanshop.model.Forder;

public interface ForderDao extends BaseDao<Forder> {

	// ͨ������id���¶���״̬
	public void updateStatus(int id, int sid);
}
