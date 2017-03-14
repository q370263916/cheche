/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.order.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.Page2;
import com.thinkgem.jeesite.common.service.CrudService;
import com.chechemotel.back.order.entity.CCMOrder;
import com.chechemotel.back.orderstream.entity.CCMOrderStream;
import com.chechemotel.back.orderstream.service.CCMOrderStreamService;
import com.chechemotel.back.shopinfo.entity.CCMShopInfo;
import com.chechemotel.back.shopinfo.service.CCMShopInfoService;
import com.chechemotel.back.sp.entity.CCMSp;
import com.chechemotel.back.sp.service.CCMSpService;
import com.chechemotel.back.user.entity.CCMUser;
import com.chechemotel.back.user.service.CCMUserService;
import com.chechemotel.front.log.Log;
import com.chechemotel.front.log.LogUtils;
import com.chechemotel.front.vo.OrderParam;
import com.chechemotel.back.order.dao.CCMOrderDao;


/**
 * 订单Service
 * @author 方乙百
 * @version 2016-10-20
 */
@Service
@Transactional(readOnly = true)
public class CCMOrderService extends CrudService<CCMOrderDao, CCMOrder> {

	@Autowired
	private CCMSpService cCMSpService;
	@Autowired
	private CCMShopInfoService cCMShopInfoService;
	@Autowired
	private CCMOrderStreamService cCMOrderStreamService;
	@Autowired
	private Log orderLogService;
	@Autowired
	private CCMUserService cCMUserService;
	
	public CCMOrder get(String id) {
		return super.get(id);
	}
	
	public List<CCMOrder> findList(CCMOrder cCMOrder) {
		return super.findList(cCMOrder);
	}
	
	public Page<CCMOrder> findPage(Page<CCMOrder> page, CCMOrder cCMOrder) {
		return super.findPage(page, cCMOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(CCMOrder cCMOrder) {
		super.save(cCMOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCMOrder cCMOrder) {
		super.delete(cCMOrder);
	}
	
	 @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void saveOrder(OrderParam orderParam,Map<String, Object> map,HttpSession session)throws UnsupportedEncodingException {
	    //判断参数校验
		//总价
		if(orderParam.getDdZj()!=null&&!orderParam.getDdZj().equals(""))
		{
			if(orderParam.getSpCode()!=null&&!orderParam.getSpCode().equals(""))
			{
				if(orderParam.getShopCode()!=null&&!orderParam.getShopCode().equals(""))
				{
					if(orderParam.getSpName()!=null&&!orderParam.getSpName().equals(""))
					{
						if(orderParam.getUserCode()!=null&&!orderParam.getUserCode().equals(""))
						{
							Double price=0.00;
							Double ssZj=0.00;
							String[] spCode = orderParam.getSpCode().split(",");
							for(int i=0;i<spCode.length;i++)
							{
								CCMSp cs=new CCMSp();
								cs.setSpCode(spCode[i]);
								List<CCMSp> spList = cCMSpService.findList(cs);
								if(spList!=null&&spList.size()>0)
								{
									price=price+new Double(spList.get(0).getPrice());
									ssZj=ssZj+new Double(spList.get(0).getPrice()) * new Double("0."+spList.get(0).getZhekou());
								}
							}
							if(price!=new Double(orderParam.getDdZj()))
							{
								map.put("regCode", "-1");
								map.put("regMsg", "价格计算有误");
							}
							else
							{
								//查找用户信息判断用户是否存在
								CCMUser user=new CCMUser();
								user.setUserCode(orderParam.getUserCode());
								List<CCMUser> userList = cCMUserService.findList(user);
								if(userList!=null&&userList.size()>0)
								{
									CCMUser coutomer = userList.get(0);
									//判断用户余额是否充足
									if(Double.parseDouble(coutomer.getMoney())>0)
									{
										//订单生成
										CCMOrder co =new CCMOrder();
										co.setUserCode(orderParam.getUserCode());
										co.setDdZj(orderParam.getDdZj());
										co.setSsZj(ssZj+"");
										co.setOrderCode(UUID.randomUUID().toString());
										co.setOrderTime(new Date());
										co.setUpdateTime(new Date());
										co.setStatus("4");
										co.setAccountName("");//用户名
										co.setSpTypeCode(orderParam.getSpTypeCode());
										co.setShopCode(orderParam.getShopCode());
										co.setSpName(orderParam.getSpName());
										co.setSpCode(orderParam.getSpCode());
										super.save(co);//保存订单表
										//查询商户信息，修改商户账户余额;
										CCMShopInfo ccshop=new CCMShopInfo();
										ccshop.setShopCode(co.getShopCode());
										List<CCMShopInfo> shopList = cCMShopInfoService.findList(ccshop);
										if(shopList!=null&&shopList.size()>0)
										{
											CCMShopInfo shop = shopList.get(0);
											Double money = new Double(shop.getMoney());
											money=money+new Double(co.getSsZj());
											shop.setMoney(money+"");
											cCMShopInfoService.save(shop);
										}
										//修改用户账户余额
										Double customermoney = new Double(coutomer.getMoney());
										customermoney=customermoney-new Double(co.getSsZj());
										coutomer.setMoney(customermoney.toString());
										cCMUserService.save(coutomer);
										//插入订单流水表
										for(int i=0;i<spCode.length;i++)
										{
											CCMSp cs=new CCMSp();
											cs.setSpCode(spCode[i]);
											List<CCMSp> spList = cCMSpService.findList(cs);
											CCMSp spp = spList.get(0);
											CCMOrderStream ccos=new CCMOrderStream();
											ccos.setAccountName(co.getAccountName());
											ccos.setShopCode(co.getShopCode());
											ccos.setDdZj((new Double(spp.getPrice()) * new Double("0."+spp.getZhekou()))+"");
											ccos.setOrderCode(co.getOrderCode());
											ccos.setUserCode(co.getUserCode());
											ccos.setOrderTime(new Date());
											ccos.setUpdateTime(new Date());
											ccos.setSpCode(spp.getSpCode());
											ccos.setSoName(spp.getSpName());
											ccos.setSpTypeCode(spp.getSpTypeCode());
											ccos.setStatus(co.getStatus());
											cCMOrderStreamService.save(ccos);
										}
									//插入订单流水表（mongo）
										Map<String,Object> convertMap = LogUtils.Bean2Map(co);
										orderLogService.insertOrderCodeLogMap(convertMap, 20013);
									//插入用户消费流水日志(mongo)
										Map<String,Object> convertMap2 = new HashMap<String,Object>();
										convertMap2.put("orderCode", co.getOrderCode());//订单号
										convertMap2.put("orderTime", co.getOrderTime());//下单日期
										convertMap2.put("shopCode", co.getShopCode());//商户号
										convertMap2.put("sszj", co.getSsZj());//订单金额
										convertMap2.put("userCode", co.getUserCode());//用户code
										convertMap2.put("userName", co.getAccountName());//用户名
										orderLogService.insertUserPayLogMap(convertMap2,20013);
										map.put("regCode", "0");
										map.put("regMsg", "订单生成成功");
										map.put("orderCode", co.getOrderCode());
										map.put("ssZj",co.getSsZj());
										map.put("shopCode", co.getShopCode());
									}
									else
									{
										map.put("regCode", "-1");
										map.put("regMsg", "用户余额不足");
									}
									
								}
								
							}
						}
						else
						{
							map.put("regCode", "-1");
							map.put("regMsg", "消费者信息为空");
						}
					}
					else
					{
						map.put("regCode", "-1");
						map.put("regMsg", "商品名为空");
					}
				}
				else
				{
					map.put("regCode", "-1");
					map.put("regMsg", "商户为空");
				}
			}
			else
			{
				map.put("regCode", "-1");
				map.put("regMsg", "总价为空");
			}
			
		}
		else
		{
			map.put("regCode", "-1");
			map.put("regMsg", "总价为空");
		}
	}
	
	
	public Page2<Object> findPageByList(Page2<Object> page, List<Map<String, Object>>  cCMOrder) {
		return super.findPageByList(page, cCMOrder);
	}
	
}