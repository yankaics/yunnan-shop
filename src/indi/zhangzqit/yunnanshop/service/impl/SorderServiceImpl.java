package indi.zhangzqit.yunnanshop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import indi.zhangzqit.yunnanshop.model.Forder;
import indi.zhangzqit.yunnanshop.model.Product;
import indi.zhangzqit.yunnanshop.model.Sorder;
import indi.zhangzqit.yunnanshop.service.SorderService;

@Service("sorderSerive")
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements
		SorderService {

	// ��product����ת��Ϊsorder����
	private Sorder productToSorder(Product product) {
		Sorder sorder = new Sorder();
		sorder.setName(product.getName());
		sorder.setNumber(1);
		sorder.setPrice(product.getPrice());
		sorder.setProduct(product);
		return sorder;
	}

	// ��ӹ�����, �����ж��ظ�
	@Override
	public Forder addSorder(Forder forder, Product product) {
		boolean isHave = false;
		Sorder sorder = this.productToSorder(product);
		// ��ȡ��ǰ���ﳵ�����еĹ�����
		for (Sorder old : forder.getSorderList()) {
			if (old.getProduct().getId().equals(sorder.getProduct().getId())) {
				// ����Ҫ�ظ����,�޸���������
				old.setNumber(old.getNumber() + sorder.getNumber());
				isHave = true;
				break;
			}
		}
		// ��ǰ���������,��ӵ����ﳵ��
		if (!isHave) {
			// ָ���������빺�ﳵ����(��ʱforder.id��null,����������ʱ������forder��sorder,��ʱforder����������)
			System.out.println("forder.id:" + forder.getId());
			sorder.setForder(forder);
			forder.getSorderList().add(sorder);
		}
		return forder;
	}

	@Override
	// ���¹���������
	public Forder alterSorder(Forder forder, Sorder sorder) {
		for (Sorder old : forder.getSorderList()) {
			if (old.getProduct().getId().equals(sorder.getProduct().getId())) {
				// ���µ�ǰ��Ʒ������
				old.setNumber(sorder.getNumber());
				break;
			}
		}
		return forder;
	}

	@Override
	public List<Object> querySaleTop10(int number) {
		return sorderDao.querySaleTop10(number);
	}

}
