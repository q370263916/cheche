/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.recharge.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.chechemotel.back.recharge.entity.CCMUserRecharge;

/**
 * 会员充值记录DAO接口
 * @author 方乙百
 * @version 2016-10-21
 */
@MyBatisDao
public interface CCMUserRechargeDao extends CrudDao<CCMUserRecharge> {
	
}