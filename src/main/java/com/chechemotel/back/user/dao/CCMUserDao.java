/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.user.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.chechemotel.back.user.entity.CCMUser;

/**
 * 会员管理DAO接口
 * @author 方乙百
 * @version 2016-10-24
 */
@MyBatisDao
public interface CCMUserDao extends CrudDao<CCMUser> {
	
}