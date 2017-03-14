/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.orderstream.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单流水Entity
 * @author 方乙百
 * @version 2016-10-20
 */
public class CCMOrderStream extends DataEntity<CCMOrderStream> {
	
	private static final long serialVersionUID = 1L;
	private String orderCode;		// 订单号
	private Date orderTime;		// 下单时间
	private String spTypeCode;		// 商品类别
	private String spCode;		// 商品编码
	private String soName;		// 商品名称
	private String ddZj;		// 订单总价
	private String status;		// 状态
	private Date updateTime;		// 修改时间
	private String shopCode;		// 商户code
	private String currentTimeStamp;		// 录入时间戳
	private String userCode;		// 会员编码
	private String accountName;		// 帐户名
	
	public CCMOrderStream() {
		super();
	}

	public CCMOrderStream(String id){
		super(id);
	}

	@Length(min=1, max=40, message="订单号长度必须介于 1 和 40 之间")
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	@Length(min=1, max=20, message="商品类别长度必须介于 1 和 20 之间")
	public String getSpTypeCode() {
		return spTypeCode;
	}

	public void setSpTypeCode(String spTypeCode) {
		this.spTypeCode = spTypeCode;
	}
	
	@Length(min=1, max=20, message="商品编码长度必须介于 1 和 20 之间")
	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}
	
	@Length(min=1, max=20, message="商品名称长度必须介于 1 和 20 之间")
	public String getSoName() {
		return soName;
	}

	public void setSoName(String soName) {
		this.soName = soName;
	}
	
	public String getDdZj() {
		return ddZj;
	}

	public void setDdZj(String ddZj) {
		this.ddZj = ddZj;
	}
	
	@Length(min=1, max=1, message="状态长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=10, message="商户code长度必须介于 0 和 10 之间")
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	
	@Length(min=0, max=32, message="录入时间戳长度必须介于 0 和 32 之间")
	public String getCurrentTimeStamp() {
		return currentTimeStamp;
	}

	public void setCurrentTimeStamp(String currentTimeStamp) {
		this.currentTimeStamp = currentTimeStamp;
	}
	
	@Length(min=0, max=36, message="会员编码长度必须介于 0 和 36 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=0, max=50, message="帐户名长度必须介于 0 和 50 之间")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
}