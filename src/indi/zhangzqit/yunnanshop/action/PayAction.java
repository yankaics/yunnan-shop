package indi.zhangzqit.yunnanshop.action;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import indi.zhangzqit.yunnanshop.model.BackData;
import indi.zhangzqit.yunnanshop.model.Forder;
import indi.zhangzqit.yunnanshop.model.SendData;
import indi.zhangzqit.yunnanshop.model.User;

public class PayAction extends BaseAction<Object> implements ParameterAware {

	private Map<String, String[]> parameters;

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

	// 重写getModel方法,根据请求的参数动态创建对象
	@Override
	public Object getModel() {
		if (parameters.get("pd_FrpId") != null) {
			// 此请求是发送到银行的请求,应该创建sendData
			model = new SendData();
		} else {
			model = new BackData();
		}
		return model;
	}

	public String goBank() {
		SendData sendData = (SendData) model;
		Forder oldForder = (Forder) session.get("oldForder");
		sendData.setP2_Order(oldForder.getId().toString());
		sendData.setP3_Amt(oldForder.getTotal().toString());
		// 获取手机号码与邮箱,支付成功后要使用
		User user = (User) session.get("user");
		sendData.setPa_MP(user.getPhone() + "," + user.getEmail());
		payService.saveDataToRequest(request, sendData);
		return "pay";
	}

	public String backShop() {
		BackData backData = (BackData) model;
		boolean isOk = payService.checkBackData(backData);
		if (isOk && backData.getR1_Code().equals("1")) {
			// 仅仅修改订单状态,其它字段不用更新
			forderService.updateStatus(
					Integer.parseInt(backData.getR6_Order()), 2);
			// 获取返回的扩展信息
			String r8_MP = backData.getR8_MP();
			sendUtil.sendEmail(backData.getR6_Order(), backData.getR3_Amt(),
					"soft03_test@sina.com");
			sendUtil.sendMessage(backData.getR6_Order(), backData.getR3_Amt(),
					"18027364651");
			request.put("info", backData.getR6_Order() + ",支付成功!");
		} else {
			request.put("info", "支付失败");
		}
		return "result";
	}
}
