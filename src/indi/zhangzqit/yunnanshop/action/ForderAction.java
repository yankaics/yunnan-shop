package indi.zhangzqit.yunnanshop.action;

import indi.zhangzqit.yunnanshop.model.Forder;
import indi.zhangzqit.yunnanshop.model.Status;
import indi.zhangzqit.yunnanshop.model.User;

public class ForderAction extends BaseAction<Forder> {

	public String save() {
		Forder forder = (Forder) session.get("forder");

		forder.setAddress(model.getAddress());
		forder.setPhone(model.getPhone());
		forder.setName(model.getName());
		forder.setRemark(model.getRemark());
		forder.setPost(model.getPost());
		forder.setUser((User) session.get("user"));
		forder.setStatus(new Status(1));
		forderService.save(forder);
		session.put("oldForder", session.get("forder"));
		// ���ﳵ�����, ԭ��session�еĹ��ﳵҪ���
		session.put("forder", new Forder());
		return "bank";
	}
}
