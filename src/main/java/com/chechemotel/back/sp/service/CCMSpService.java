/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.sp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.chechemotel.back.sp.entity.CCMSp;
import com.chechemotel.back.sp.dao.CCMSpDao;

/**
 * 商品管理Service
 * @author 方乙百
 * @version 2016-10-20
 */
@Service
@Transactional(readOnly = true)
public class CCMSpService extends CrudService<CCMSpDao, CCMSp> {

	public CCMSp get(String id) {
		return super.get(id);
	}
	
	public List<CCMSp> findList(CCMSp cCMSp) {
		return super.findList(cCMSp);
	}
	
	public Page<CCMSp> findPage(Page<CCMSp> page, CCMSp cCMSp) {
		return super.findPage(page, cCMSp);
	}
	
	@Transactional(readOnly = false)
	public void save(CCMSp cCMSp) {
		super.save(cCMSp);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCMSp cCMSp) {
		super.delete(cCMSp);
	}
	
}