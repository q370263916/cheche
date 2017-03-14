/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.shoptype.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商户类别Entity
 * @author 方乙百
 * @version 2016-10-19
 */
public class CCMShopType extends DataEntity<CCMShopType> {
	
	private static final long serialVersionUID = 1L;
	private String typeCode;		// 类别编码
	private String typeName;		// 类别名称
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	private String spImg; //大图标
	private String spIcon; //小图标
	
	public CCMShopType() {
		super();
	}

	public CCMShopType(String id){
		super(id);
	}

	@Length(min=0, max=5, message="类别编码长度必须介于 0 和 5 之间")
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	@Length(min=0, max=10, message="类别名称长度必须介于 0 和 10 之间")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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