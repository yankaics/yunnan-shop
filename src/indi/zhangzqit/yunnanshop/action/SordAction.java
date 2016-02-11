package indi.zhangzqit.yunnanshop.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import indi.zhangzqit.yunnanshop.model.Forder;
import indi.zhangzqit.yunnanshop.model.Product;
import indi.zhangzqit.yunnanshop.model.Sorder;

public class SorderAction extends BaseAction<Sorder> {

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public String queryByYear() {
		List<Object> obj = sorderService.querySaleTop10(model.getNumber());
		// û��ָ��rootĬ�ϻ��ջ����ֵת��Ϊjson��ʽ
		ActionContext.getContext().getValueStack().push(obj);
		return "jsonList";
	}

	public String addSorder() {
		Product product = productService.get(model.getProduct().getId());
		Forder forder = (Forder) session.get("forder");
		// ��ӹ�������ﳵ��,���ж��ظ�
		forder = sorderService.addSorder(forder, product);
		forder.setTotal(forderService.cluTotal(forder));
		return "showCar";
	}

	public String alterSorder() {
		System.out.println(model + "," + model.getProduct());
		// ��session�л�ȡ���ﳵ
		Forder forder = (Forder) session.get("forder");
		// ������Ʒ��Ÿ�����Ʒ����
		forder = sorderService.alterSorder(forder, model);
		// �����µ��ܼ۸�
		forder.setTotal(forderService.cluTotal(forder));
		// ͨ�����ķ�ʽ�����µ��ܼ۸�
		inputStream = new ByteArrayInputStream(forder.getTotal().toString()
				.getBytes());
		return "stream";
	}
}
