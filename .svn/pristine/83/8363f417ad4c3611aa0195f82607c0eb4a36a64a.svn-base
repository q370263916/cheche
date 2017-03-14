package com.chechemotel.back.sms.dao;

import com.chechemotel.back.order.entity.CCMOrder;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 方乙百 on 2016/10/24.
 */
@MyBatisDao
public interface SMSDao {

    /**
     * 通过用户名查询手机号
     * @param name
     * @return
     */
    public String getSysUserPhoneByName(@Param("name") String name);

}