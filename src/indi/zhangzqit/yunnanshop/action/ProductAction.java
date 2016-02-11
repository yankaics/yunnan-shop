package indi.zhangzqit.yunnanshop.action;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import indi.zhangzqit.yunnanshop.model.PageModel;
import indi.zhangzqit.yunnanshop.model.Product;
import indi.zhangzqit.yunnanshop.model.Image;

public class ProductAction extends BaseAction<Product> {

	private Image image;

	private String ids;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String get() {
		request.put("product", productService.get(model.getId()));
		return "detail";
	}

	public void save() throws Exception {
		String pic = fileUploadUtil
				.upload(image.getFile(), image.getFilename());
		model.setPic(pic);
		productService.save(model);
	}

	public String queryJoinCategory() {
		System.out.println(model.getName() + "," + page + "," + rows);
		// root÷–≈‰÷√ pageModel
		pageModel = new PageModel<Product>();
		pageModel.setTotal(productService.count(model.getName()));
		pageModel.setRows(productService.queryJoinCategory(model.getName(),
				page, rows));
		return "pageModel";
	}
}
