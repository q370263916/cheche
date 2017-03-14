/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.orderstream.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.chechemotel.back.orderstream.entity.CCMOrderStream;
import com.chechemotel.back.orderstream.dao.CCMOrderStreamDao;

/**
 * 订单流水Service
 * @author 方乙百
 * @version 2016-10-20
 */
@Service
@Transactional(readOnly = true)
public class CCMOrderStreamService extends CrudService<CCMOrderStreamDao, CCMOrderStream> {

	public CCMOrderStream get(String id) {
		return super.get(id);
	}
	
	public List<CCMOrderStream> findList(CCMOrderStream cCMOrderStream) {
		return super.findList(cCMOrderStream);
	}
	
	public Page<CCMOrderStream> findPage(Page<CCMOrderStream> page, CCMOrderStream cCMOrderStream) {
		return super.findPage(page, cCMOrderStream);
	}
	
	@Transactional(readOnly = false)
	public void save(CCMOrderStream cCMOrderStream) {
		super.save(cCMOrderStream);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCMOrderStream cCMOrderStream) {
		super.delete(cCMOrderStream);
	}
	
}