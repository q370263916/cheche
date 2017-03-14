/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.shoprole.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商户权限管理Entity
 * @author 方乙百
 * @version 2016-10-20
 */
public class CCMShopRole extends DataEntity<CCMShopRole> {
	
	private static final long serialVersionUID = 1L;
	private String shopCode;		// 公司编码
	private String shopName;		// 公司名称
	private String username;		// 第三方用户名
	private String password;		// 第三方用户密码
	private String realname;		// 真实姓名
	private String tel;		// 电话号
	private String flag;		// 状态
	private String type;		// 状态
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	private String shopType;		// 类别编码
	
	public CCMShopRole() {
		super();
	}

	public CCMShopRole(String id){
		super(id);
	}

	@Length(min=1, max=6, message="公司编码长度必须介于 1 和 6 之间")
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	
	@Length(min=1, max=30, message="公司名称长度必须介于 1 和 30 之间")
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	@Length(min=1, max=32, message="第三方用户名长度必须介于 1 和 32 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=1, max=500, message="第三方用户密码长度必须介于 1 和 500 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=1, max=30, message="真实姓名长度必须介于 1 和 30 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=16, message="电话号长度必须介于 0 和 16 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=1, max=1, message="状态长度必须介于 1 和 1 之间")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Length(min=1, max=1, message="状态长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=5, message="类别编码长度必须介于 0 和 5 之间")
	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	
}