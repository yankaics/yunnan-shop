package indi.zhangzqit.yunnanshop.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import indi.zhangzqit.yunnanshop.service.FileUploadUtil;
import indi.zhangzqit.yunnanshop.util.ProductTimerTask;

public class InitDataServletContextListener implements ServletContextListener {

	private ApplicationContext context;

	private FileUploadUtil fileUploadUtil;

	private ProductTimerTask productTimerTask;

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(event.getServletContext());
		fileUploadUtil = (FileUploadUtil) context.getBean("fileUploadUtil");
		// �Զ����߳�ʵ����,����������ҳ������
		productTimerTask = (ProductTimerTask) context
				.getBean("productTimerTask");
		productTimerTask.setApplication(event.getServletContext());
		// ͨ����ʱ��,�����ҳ���ݵ�ͬ������
		new Timer(true).schedule(productTimerTask, 0, 1000 * 60 * 60);
		// ��������logo, �洢��application��,��֧��ҳ��ʹ��
		String[] bankName = fileUploadUtil.getFileNameByPath(event
				.getServletContext().getRealPath("/images/bank/"));
		event.getServletContext().setAttribute("bankName", bankName);
	}
}
