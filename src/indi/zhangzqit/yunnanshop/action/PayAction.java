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

	// ��дgetModel����,��������Ĳ�����̬��������
	@Override
	public Object getModel() {
		if (parameters.get("pd_FrpId") != null) {
			// �������Ƿ��͵����е�����,Ӧ�ô���sendData
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
		// ��ȡ�ֻ�����������,֧���ɹ���Ҫʹ��
		User user = (User) session.get("user");
		sendData.setPa_MP(user.getPhone() + "," + user.getEmail());
		payService.saveDataToRequest(request, sendData);
		return "pay";
	}

	public String backShop() {
		BackData backData = (BackData) model;
		boolean isOk = payService.checkBackData(backData);
		if (isOk && backData.getR1_Code().equals("1")) {
			// �����޸Ķ���״̬,�����ֶβ��ø���
			forderService.updateStatus(
					Integer.parseInt(backData.getR6_Order()), 2);
			// ��ȡ���ص���չ��Ϣ
			String r8_MP = backData.getR8_MP();
			sendUtil.sendEmail(backData.getR6_Order(), backData.getR3_Amt(),
					"soft03_test@sina.com");
			sendUtil.sendMessage(backData.getR6_Order(), backData.getR3_Amt(),
					"18027364651");
			request.put("info", backData.getR6_Order() + ",֧���ɹ�!");
		} else {
			request.put("info", "֧��ʧ��");
		}
		return "result";
	}
}
