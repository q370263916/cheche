/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.recharge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.chechemotel.back.recharge.entity.CCMUserRecharge;
import com.chechemotel.back.recharge.dao.CCMUserRechargeDao;

/**
 * 会员充值记录Service
 * @author 方乙百
 * @version 2016-10-21
 */
@Service
@Transactional(readOnly = true)
public class CCMUserRechargeService extends CrudService<CCMUserRechargeDao, CCMUserRecharge> {

	public CCMUserRecharge get(String id) {
		return super.get(id);
	}
	
	public List<CCMUserRecharge> findList(CCMUserRecharge cCMUserRecharge) {
		return super.findList(cCMUserRecharge);
	}
	
	public Page<CCMUserRecharge> findPage(Page<CCMUserRecharge> page, CCMUserRecharge cCMUserRecharge) {
		return super.findPage(page, cCMUserRecharge);
	}
	
	@Transactional(readOnly = false)
	public void save(CCMUserRecharge cCMUserRecharge) {
		super.save(cCMUserRecharge);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCMUserRecharge cCMUserRecharge) {
		super.delete(cCMUserRecharge);
	}
	
}