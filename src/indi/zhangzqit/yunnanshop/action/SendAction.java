package indi.zhangzqit.yunnanshop.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ��Action��������ת������,������ҵ���߼�
 */

@Controller("sendAction")
public class SendAction extends ActionSupport {

	public String execute() {
		return "send";
	}
}
