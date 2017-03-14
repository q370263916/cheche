/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.recharge.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会员充值记录Entity
 * @author 方乙百
 * @version 2016-10-21
 */
public class CCMUserRecharge extends DataEntity<CCMUserRecharge> {
	
	private static final long serialVersionUID = 1L;
	private String userCode;		// 会员编码
	private Date inMoneyTime;		// 资金收入时间
	private String inMoney;		// 收入金额
	private String inMoneyType;		// 充值支付方式
	private String inBankOrdernum;		// 充值凭证号
	private Date updateTime;		// 修改时间
	
	public CCMUserRecharge() {
		super();
	}

	public CCMUserRecharge(String id){
		super(id);
	}

	@Length(min=1, max=36, message="会员编码长度必须介于 1 和 36 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInMoneyTime() {
		return inMoneyTime;
	}

	public void setInMoneyTime(Date inMoneyTime) {
		this.inMoneyTime = inMoneyTime;
	}
	
	public String getInMoney() {
		return inMoney;
	}

	public void setInMoney(String inMoney) {
		this.inMoney = inMoney;
	}
	
	@Length(min=0, max=1, message="充值支付方式长度必须介于 0 和 1 之间")
	public String getInMoneyType() {
		return inMoneyType;
	}

	public void setInMoneyType(String inMoneyType) {
		this.inMoneyType = inMoneyType;
	}
	
	@Length(min=0, max=40, message="充值凭证号长度必须介于 0 和 40 之间")
	public String getInBankOrdernum() {
		return inBankOrdernum;
	}

	public void setInBankOrdernum(String inBankOrdernum) {
		this.inBankOrdernum = inBankOrdernum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}