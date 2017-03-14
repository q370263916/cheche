/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.user.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会员管理Entity
 * @author 方乙百
 * @version 2016-10-24
 */
public class CCMUser extends DataEntity<CCMUser> {
	
	private static final long serialVersionUID = 1L;
	private String userCode;		// 会员编码
	private String accountName;		// 帐户名
	private String phone;		// 手机号
	private String realName;		// 真实姓名
	private String money;		// 账户余额
	private String qrCode;		// 用户二维码路径
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	private String card;		// 身份证号
	private String jifen;		// 积分
	private String zje;		// 消费总额
	private String sex;		// 性别
	private String headImg;		// 头像路径
	private String redBegZje;		// 红包总额
	private String canTxZje;		// 可提现红包总额
	private String regBagCount;		// 红包总数
	private String status;		// 会员状态
	private String tjm;		// 推荐码
	
	public CCMUser() {
		super();
	}

	public CCMUser(String id){
		super(id);
	}

	@Length(min=1, max=36, message="会员编码长度必须介于 1 和 36 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=1, max=50, message="帐户名长度必须介于 1 和 50 之间")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@Length(min=0, max=16, message="手机号长度必须介于 0 和 16 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=40, message="真实姓名长度必须介于 0 和 40 之间")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	@Length(min=0, max=120, message="用户二维码路径长度必须介于 0 和 120 之间")
	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
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
	
	@Length(min=0, max=20, message="身份证号长度必须介于 0 和 20 之间")
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
	
	@Length(min=0, max=11, message="积分长度必须介于 0 和 11 之间")
	public String getJifen() {
		return jifen;
	}

	public void setJifen(String jifen) {
		this.jifen = jifen;
	}
	
	public String getZje() {
		return zje;
	}

	public void setZje(String zje) {
		this.zje = zje;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=100, message="头像路径长度必须介于 0 和 100 之间")
	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	public String getRedBegZje() {
		return redBegZje;
	}

	public void setRedBegZje(String redBegZje) {
		this.redBegZje = redBegZje;
	}
	
	public String getCanTxZje() {
		return canTxZje;
	}

	public void setCanTxZje(String canTxZje) {
		this.canTxZje = canTxZje;
	}
	
	@Length(min=0, max=11, message="红包总数长度必须介于 0 和 11 之间")
	public String getRegBagCount() {
		return regBagCount;
	}

	public void setRegBagCount(String regBagCount) {
		this.regBagCount = regBagCount;
	}
	
	@Length(min=0, max=1, message="会员状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=10, message="推荐码长度必须介于 0 和 10 之间")
	public String getTjm() {
		return tjm;
	}

	public void setTjm(String tjm) {
		this.tjm = tjm;
	}
	
}