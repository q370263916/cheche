/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.sptype.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;

import java.util.List;

import com.chechemotel.back.sptype.entity.CCMSpType;
import org.apache.ibatis.annotations.Param;

/**
 * 商品类型DAO接口
 * @author songkailiang
 * @version 2016-10-19
 */
@MyBatisDao
public interface CCMSpTypeDao extends CrudDao<CCMSpType> {
	
	/**
	 * 查询表列表
	 * @param genTable
	 * @return
	 */
	List<CCMSpType> findLevelOne();
	List<CCMSpType> findLevelTwo();

	public List<CCMSpType> getSpTypeByCode(@Param("shopCode") String code);
	
}