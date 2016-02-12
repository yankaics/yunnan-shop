package indi.zhangzqit.yunnanshop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import indi.zhangzqit.yunnanshop.model.Privilege;
import indi.zhangzqit.yunnanshop.service.PrivilegeService;

@Service("privilegeService")
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege> implements
		PrivilegeService {

	@Override
	public List<Privilege> queryForTree() {
		return privilegeDao.queryForTree();
	}

}
