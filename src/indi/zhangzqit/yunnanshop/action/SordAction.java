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
		// 没有指定root默认会把栈顶的值转化为json格式
		ActionContext.getContext().getValueStack().push(obj);
		return "jsonList";
	}

	public String addSorder() {
		Product product = productService.get(model.getProduct().getId());
		Forder forder = (Forder) session.get("forder");
		// 添加购物项到购物车中,且判断重复
		forder = sorderService.addSorder(forder, product);
		forder.setTotal(forderService.cluTotal(forder));
		return "showCar";
	}

	public String alterSorder() {
		System.out.println(model + "," + model.getProduct());
		// 从session中获取购物车
		Forder forder = (Forder) session.get("forder");
		// 根据商品编号更新商品数量
		forder = sorderService.alterSorder(forder, model);
		// 计算新的总价格
		forder.setTotal(forderService.cluTotal(forder));
		// 通过流的方式返回新的总价格
		inputStream = new ByteArrayInputStream(forder.getTotal().toString()
				.getBytes());
		return "stream";
	}
}
