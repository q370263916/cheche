/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.sptype.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品类型Entity
 * @author songkailiang
 * @version 2016-10-19
 */
public class CCMSpType extends DataEntity<CCMSpType> {
	
	private static final long serialVersionUID = 1L;
	private String spTypeName;		// 商品类别名称
	private String spTypeCode;		// 商品类别编码
	private String spTypeLevel;		// 商品类别级别
	private String spTypeParent;		// 上级类别代码
	private String shopCode;		// 商户编号
	private String shopName;		// 商户名称
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	private String mark;		// 更改标志位
	private String spTypeParent1;//临时字段1
	private String spTypeParent2;//临时字段2
	private String spImg; //大图标
	private String spIcon; //小图标

	public CCMSpType() {
		super();
	}

	public CCMSpType(String id){
		super(id);
	}

	@Length(min=1, max=10, message="商品类别名称长度必须介于 1 和 10 之间")
	public String getSpTypeName() {
		return spTypeName;
	}

	public void setSpTypeName(String spTypeName) {
		this.spTypeName = spTypeName;
	}
	
	@Length(min=1, max=15, message="商品类别编码长度必须介于 1 和 15 之间")
	public String getSpTypeCode() {
		return spTypeCode;
	}

	public void setSpTypeCode(String spTypeCode) {
		this.spTypeCode = spTypeCode;
	}
	
	@Length(min=1, max=1, message="商品类别级别长度必须介于 1 和 1 之间")
	public String getSpTypeLevel() {
		return spTypeLevel;
	}

	public void setSpTypeLevel(String spTypeLevel) {
		this.spTypeLevel = spTypeLevel;
	}
	
	@Length(min=1, max=15, message="上级类别代码长度必须介于 1 和 15 之间")
	public String getSpTypeParent() {
		return spTypeParent;
	}

	public void setSpTypeParent(String spTypeParent) {
		this.spTypeParent = spTypeParent;
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
	
	@Length(min=0, max=10, message="更改标志位长度必须介于 0 和 10 之间")
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getSpTypeParent1() {
		return spTypeParent1;
	}

	public void setSpTypeParent1(String spTypeParent1) {
		this.spTypeParent1 = spTypeParent1;
	}

	public String getSpTypeParent2() {
		return spTypeParent2;
	}

	public void setSpTypeParent2(String spTypeParent2) {
		this.spTypeParent2 = spTypeParent2;
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