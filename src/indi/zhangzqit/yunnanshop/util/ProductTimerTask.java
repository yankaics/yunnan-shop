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
		// 查询热点类别
		for (Category temp : categoryService.queryByHot(true)) {
			// 通过热点类别查询推荐商品
			bigList.add(productService.queryByCid(temp.getId()));
		}
		// 存储到application内置对象中
		application.setAttribute("bigList", bigList);
	}
}
