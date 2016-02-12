package indi.zhangzqit.yunnanshop.service;

import java.util.Map;

import indi.zhangzqit.yunnanshop.model.BackData;
import indi.zhangzqit.yunnanshop.model.SendData;

public interface PayService {

	public abstract Map<String, Object> saveDataToRequest(
			Map<String, Object> request, SendData sendData);

	public boolean checkBackData(BackData backData);

}