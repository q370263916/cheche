/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.sp.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 商品管理Entity
 * @author 方乙百
 * @version 2016-10-20
 */
public class CCMSp extends DataEntity<CCMSp> {
	
	private static final long serialVersionUID = 1L;
	private String spCode;		// 商品编码
	private String spName;		// 商品名称
	private String spTypeCode;		// 商品类别id
	private String zhekou;		// 折扣
	private String kuCun;		// 库存数量
	private String price;		// 价格
	private String shopCode;		// 公司编码
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	private String spImg;		// 商品的图标
	private String jifen;		// 消费后的积分
	private String redbagVal;		// 红包分值
	private String extend1;		// 扩展字段1
	private String extend2;		// 扩展字段2
	private String extend3;		// 扩展字段3
	private String shopName;		// 商户名称
	private String spIcon; //小图标
	
	public CCMSp() {
		super();
	}

	public CCMSp(String id){
		super(id);
	}

	@Length(min=1, max=20, message="商品编码长度必须介于 1 和 20 之间")
	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}
	
	@Length(min=1, max=30, message="商品名称长度必须介于 1 和 30 之间")
	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	@NotNull(message="xxx不能为空！")
	@Length(min=1, max=20, message="商品类别id长度必须介于 1 和 20 之间")
	public String getSpTypeCode() {
		return spTypeCode;
	}

	public void setSpTypeCode(String spTypeCode) {
		this.spTypeCode = spTypeCode;
	}
	
	@Length(min=0, max=5, message="折扣长度必须介于 0 和 5 之间")
	public String getZhekou() {
		return zhekou;
	}

	public void setZhekou(String zhekou) {
		this.zhekou = zhekou;
	}
	
	@Length(min=1, max=11, message="库存数量长度必须介于 1 和 11 之间")
	public String getKuCun() {
		return kuCun;
	}

	public void setKuCun(String kuCun) {
		this.kuCun = kuCun;
	}
	
	@Length(min=1, max=20, message="价格长度必须介于 1 和 20 之间")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=10, message="公司编码长度必须介于 0 和 10 之间")
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
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
	
	@Length(min=0, max=100, message="商品的图标长度必须介于 0 和 100 之间")
	public String getSpImg() {
		return spImg;
	}

	public void setSpImg(String spImg) {
		this.spImg = spImg;
	}
	
	@Length(min=0, max=10, message="消费后的积分长度必须介于 0 和 10 之间")
	public String getJifen() {
		return jifen;
	}

	public void setJifen(String jifen) {
		this.jifen = jifen;
	}
	
	@Length(min=0, max=10, message="红包分值长度必须介于 0 和 10 之间")
	public String getRedbagVal() {
		return redbagVal;
	}

	public void setRedbagVal(String redbagVal) {
		this.redbagVal = redbagVal;
	}
	
	@Length(min=0, max=64, message="扩展字段1长度必须介于 0 和 64 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=64, message="扩展字段2长度必须介于 0 和 64 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	@Length(min=0, max=64, message="扩展字段3长度必须介于 0 和 64 之间")
	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}
	
	@Length(min=0, max=30, message="商户名称长度必须介于 0 和 30 之间")
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Length(min=0, max=300, message="小图标字段长度必须介于 0 和 300 之间")
	public String getSpIcon() {
		return spIcon;
	}

	public void setSpIcon(String spIcon) {
		this.spIcon = spIcon;
	}
	
}