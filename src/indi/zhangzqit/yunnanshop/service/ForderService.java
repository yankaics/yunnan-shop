package indi.zhangzqit.yunnanshop.service;

import java.math.BigDecimal;

import indi.zhangzqit.yunnanshop.model.Forder;

public interface ForderService extends BaseService<Forder> {
	// ���ݹ������ ���㹺�ﳵ���ܼ۸�
	public BigDecimal cluTotal(Forder forder);

	// ͨ������id���¶���״̬
	public void updateStatus(int id, int sid);
}
