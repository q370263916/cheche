/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.chechemotel.back.user.entity.CCMUser;
import com.chechemotel.back.user.dao.CCMUserDao;

/**
 * 会员管理Service
 * @author 方乙百
 * @version 2016-10-24
 */
@Service
@Transactional(readOnly = true)
public class CCMUserService extends CrudService<CCMUserDao, CCMUser> {

	public CCMUser get(String id) {
		return super.get(id);
	}
	
	public List<CCMUser> findList(CCMUser cCMUser) {
		return super.findList(cCMUser);
	}
	
	public Page<CCMUser> findPage(Page<CCMUser> page, CCMUser cCMUser) {
		return super.findPage(page, cCMUser);
	}
	
	@Transactional(readOnly = false)
	public void save(CCMUser cCMUser) {
		super.save(cCMUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCMUser cCMUser) {
		super.delete(cCMUser);
	}
	
}