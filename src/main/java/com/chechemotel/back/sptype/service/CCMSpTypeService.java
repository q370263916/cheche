/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.sptype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.gen.dao.GenTableDao;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.chechemotel.back.sptype.entity.CCMSpType;
import com.chechemotel.back.sptype.dao.CCMSpTypeDao;

/**
 * 商品类型Service
 * @author songkailiang
 * @version 2016-10-19
 */
@Service
@Transactional(readOnly = true)
public class CCMSpTypeService extends CrudService<CCMSpTypeDao, CCMSpType> {
	@Autowired
	private CCMSpTypeDao cCMSpTypeDao;
	public CCMSpType get(String id) {
		return super.get(id);
	}
	
	public List<CCMSpType> findList(CCMSpType cCMSpType) {
		return super.findList(cCMSpType);
	}
	
	public Page<CCMSpType> findPage(Page<CCMSpType> page, CCMSpType cCMSpType) {
		return super.findPage(page, cCMSpType);
	}
	
	@Transactional(readOnly = false)
	public void save(CCMSpType cCMSpType) {
		super.save(cCMSpType);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCMSpType cCMSpType) {
		super.delete(cCMSpType);
	}
	
	/**
	 * 获取1级菜单列表
	 * @return
	 */
	public List<CCMSpType> findLevelOneListFormDb(){
		return cCMSpTypeDao.findLevelOne();
	}
	
	/**
	 * 获取1级菜单列表
	 * @return
	 */
	public List<CCMSpType> findLevelTwoListFormDb(){
		return cCMSpTypeDao.findLevelTwo();
	}


	/**
	 * 通过code查询 下拉框用
	 * @param code
	 * @return
	 */
	public List<CCMSpType> getSpTypeByCode(String code){
		return cCMSpTypeDao.getSpTypeByCode(code);
	}

	
}