package com.chechemotel.back.sms.service;

import com.chechemotel.back.sms.dao.SMSDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fangyibai on 2016/10/24.
 */
@Service
@Transactional(readOnly = true)
public class SMSService {

    @Autowired
    private SMSDao smsDao;

    public String getSysUserPhoneByName(String name){
        return smsDao.getSysUserPhoneByName(name);
    }
}
