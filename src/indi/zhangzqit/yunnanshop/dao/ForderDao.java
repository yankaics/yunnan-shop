package indi.zhangzqit.yunnanshop.dao;

import java.math.BigDecimal;

import indi.zhangzqit.yunnanshop.model.Forder;

public interface ForderDao extends BaseDao<Forder> {

	// 通过订单id更新订单状态
	public void updateStatus(int id, int sid);
}
