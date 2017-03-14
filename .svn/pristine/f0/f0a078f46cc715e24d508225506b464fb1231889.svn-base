/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.shoprole.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.chechemotel.back.shoprole.entity.CCMShopRole;
import com.chechemotel.back.shoprole.dao.CCMShopRoleDao;

/**
 * 商户权限管理Service
 * @author 方乙百
 * @version 2016-10-20
 */
@Service
@Transactional(readOnly = true)
public class CCMShopRoleService extends CrudService<CCMShopRoleDao, CCMShopRole> {

	public CCMShopRole get(String id) {
		return super.get(id);
	}
	
	public List<CCMShopRole> findList(CCMShopRole cCMShopRole) {
		return super.findList(cCMShopRole);
	}
	
	public Page<CCMShopRole> findPage(Page<CCMShopRole> page, CCMShopRole cCMShopRole) {
		return super.findPage(page, cCMShopRole);
	}
	
	@Transactional(readOnly = false)
	public void save(CCMShopRole cCMShopRole) {
		super.save(cCMShopRole);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCMShopRole cCMShopRole) {
		super.delete(cCMShopRole);
	}
	
}