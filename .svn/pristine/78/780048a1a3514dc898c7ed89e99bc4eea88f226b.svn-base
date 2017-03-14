/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.shopinfo.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商户信息管理Entity
 * @author 方乙百
 * @version 2016-10-19
 */
public class CCMShopInfo extends DataEntity<CCMShopInfo> {
	
	private static final long serialVersionUID = 1L;
	private String shopCode;		// 公司编码
	private String shopName;		// 公司名称
	private String shopAddress;		// 详细地址
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	private String bussinessLicence;		// 营业执照编号
	private String taxRegistrationCertificate;		// 税务登记证号
	private String name;		// 联系人姓名
	private String tel;		// 联系人电话
	private String content;		// 备注
	private String shopType;		// 类别编码
	private String money;		// 商户余额
	private String bussinessLicencePic;		// 营业执照图片字段
	private String spImg; //大图标
	private String spIcon; //小图标

	public CCMShopInfo() {
		super();
	}

	public CCMShopInfo(String id){
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
	
	@Length(min=0, max=120, message="详细地址长度必须介于 0 和 120 之间")
	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
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
	
	@Length(min=0, max=40, message="营业执照编号长度必须介于 0 和 40 之间")
	public String getBussinessLicence() {
		return bussinessLicence;
	}

	public void setBussinessLicence(String bussinessLicence) {
		this.bussinessLicence = bussinessLicence;
	}
	
	@Length(min=0, max=40, message="税务登记证号长度必须介于 0 和 40 之间")
	public String getTaxRegistrationCertificate() {
		return taxRegistrationCertificate;
	}

	public void setTaxRegistrationCertificate(String taxRegistrationCertificate) {
		this.taxRegistrationCertificate = taxRegistrationCertificate;
	}
	
	@Length(min=0, max=10, message="联系人姓名长度必须介于 0 和 10 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=16, message="联系人电话长度必须介于 0 和 16 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=120, message="备注长度必须介于 0 和 120 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=5, message="类别编码长度必须介于 0 和 5 之间")
	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	@Length(min=0, max=128, message="营业执照图片字段长度必须介于 0 和 128 之间")
	public String getBussinessLicencePic() {
		return bussinessLicencePic;
	}

	public void setBussinessLicencePic(String bussinessLicencePic) {
		this.bussinessLicencePic = bussinessLicencePic;
	}

	@Length(min=0, max=300, message="大图标字段长度必须介于 0 和 300 之间")
	public String getSpImg() {
		return spImg;
	}

	public void setSpImg(String spImg) {
		this.spImg = spImg;
	}

	@Length(min=0, max=300, message="小图标字段长度必须介于 0 和 300 之间")
	public String getSpIcon() {
		return spIcon;
	}

	public void setSpIcon(String spIcon) {
		this.spIcon = spIcon;
	}
}