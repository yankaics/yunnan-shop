package indi.zhangzqit.yunnanshop.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import indi.zhangzqit.yunnanshop.model.Forder;

public class ForderSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// ��һ���ʵ�ʱ�򴴽����ﳵ,���Ҵ洢��session��
		System.out.println("**********sessionCreated************");
		event.getSession().setAttribute("forder", new Forder());
	}

	@Override
	// ��session���ٵ�ʱ��ִ��(����ʵ��"����½ʱ��")
	public void sessionDestroyed(HttpSessionEvent event) {
	}
}
