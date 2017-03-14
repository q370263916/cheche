/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.shoptype.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.chechemotel.back.shoptype.entity.CCMShopType;
import com.chechemotel.back.shoptype.dao.CCMShopTypeDao;

/**
 * 商户类别Service
 * @author 方乙百
 * @version 2016-10-19
 */
@Service
@Transactional(readOnly = true)
public class CCMShopTypeService extends CrudService<CCMShopTypeDao, CCMShopType> {

	@Autowired
	private CCMShopTypeDao ccmShopTypeDao;

	public CCMShopType get(String id) {
		return super.get(id);
	}
	
	public List<CCMShopType> findList(CCMShopType cCMShopType) {
		return super.findList(cCMShopType);
	}
	
	public Page<CCMShopType> findPage(Page<CCMShopType> page, CCMShopType cCMShopType) {
		return super.findPage(page, cCMShopType);
	}
	
	@Transactional(readOnly = false)
	public void save(CCMShopType cCMShopType) {
		super.save(cCMShopType);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCMShopType cCMShopType) {
		super.delete(cCMShopType);
	}

	public boolean getShopTypeByCode(String code){
		boolean flag = false;
		Integer i =  ccmShopTypeDao.getShopTypeByCode(code);
		if (i==0){
			flag = true;
		}
		return flag;
	}

	public Map<String,String> getShopTypeByCheckbox(){
		List<CCMShopType> shopTypeByCheckbox = ccmShopTypeDao.getShopTypeByCheckbox();
		Map<String,String> map = new HashMap<String, String>();
		for (CCMShopType shopType : shopTypeByCheckbox){
			map.put(shopType.getTypeCode(),shopType.getTypeName());
		}
		return map;
	}
}