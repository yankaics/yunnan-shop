package indi.zhangzqit.yunnanshop.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import indi.zhangzqit.yunnanshop.model.Forder;
import indi.zhangzqit.yunnanshop.model.Sorder;
import indi.zhangzqit.yunnanshop.service.ForderService;

@Service("forderService")
public class ForderServiceImpl extends BaseServiceImpl<Forder> implements
		ForderService {

	@Override
	public BigDecimal cluTotal(Forder forder) {
		BigDecimal total = new BigDecimal(0.00);
		for (Sorder sorder : forder.getSorderList()) {
			System.out.println(sorder.getPrice() + "," + sorder.getNumber());
			BigDecimal temp = sorder.getPrice().multiply(
					new BigDecimal(sorder.getNumber()));
			System.out.println(temp);
			total = total.add(temp);
		}
		return total;
	}

	@Override
	public void updateStatus(int id, int sid) {
		forderDao.updateStatus(id, sid);
	}
}
