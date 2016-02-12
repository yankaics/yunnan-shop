package indi.zhangzqit.yunnanshop.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import indi.zhangzqit.yunnanshop.model.Category;
import indi.zhangzqit.yunnanshop.model.Product;
import indi.zhangzqit.yunnanshop.service.CategoryService;
import indi.zhangzqit.yunnanshop.service.ProductService;

@Component("productTimerTask")
public class ProductTimerTask extends TimerTask {

	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;

	private ServletContext application;

	public void setApplication(ServletContext application) {
		this.application = application;
	}

	@Override
	public void run() {
		System.out.println("***********run()************");

		List<List<Product>> bigList = new ArrayList<List<Product>>();
		// ��ѯ�ȵ����
		for (Category temp : categoryService.queryByHot(true)) {
			// ͨ���ȵ�����ѯ�Ƽ���Ʒ
			bigList.add(productService.queryByCid(temp.getId()));
		}
		// �洢��application���ö�����
		application.setAttribute("bigList", bigList);
	}
}
