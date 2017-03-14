/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.shoptype.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.chechemotel.back.shoptype.entity.CCMShopType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商户类别DAO接口
 * @author 方乙百
 * @version 2016-10-19
 */
@MyBatisDao
public interface CCMShopTypeDao extends CrudDao<CCMShopType> {

    public Integer getShopTypeByCode(@Param("code")String code);

    public List<CCMShopType> getShopTypeByCheckbox();
}