/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.shopinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.chechemotel.back.shopinfo.entity.CCMShopInfo;
import com.chechemotel.back.shopinfo.dao.CCMShopInfoDao;

/**
 * 商户信息管理Service
 * @author 方乙百
 * @version 2016-10-19
 */
@Service
@Transactional(readOnly = true)
public class CCMShopInfoService extends CrudService<CCMShopInfoDao, CCMShopInfo> {

	public CCMShopInfo get(String id) {
		return super.get(id);
	}
	
	public List<CCMShopInfo> findList(CCMShopInfo cCMShopInfo) {
		return super.findList(cCMShopInfo);
	}
	
	public Page<CCMShopInfo> findPage(Page<CCMShopInfo> page, CCMShopInfo cCMShopInfo) {
		return super.findPage(page, cCMShopInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(CCMShopInfo cCMShopInfo) {
		super.save(cCMShopInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCMShopInfo cCMShopInfo) {
		super.delete(cCMShopInfo);
	}
	
}