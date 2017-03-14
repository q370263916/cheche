package com.chechemotel.front.interfaces;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.SortedMap;



import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.chechemotel.back.order.service.CCMOrderService;
import com.chechemotel.back.shopinfo.entity.CCMShopInfo;
import com.chechemotel.back.shopinfo.service.CCMShopInfoService;
import com.chechemotel.back.sp.entity.CCMSp;
import com.chechemotel.back.sp.service.CCMSpService;
import com.chechemotel.back.sptype.entity.CCMSpType;
import com.chechemotel.back.sptype.service.CCMSpTypeService;
import com.chechemotel.front.vo.OrderParam;
import com.chechemotel.front.vo.SptypeParam;
import com.chechemotel.util.StringUtil;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;





/**   
 * @Title: IndexController
 * @Description: 前段请求借口类
 * @author onlineGenerator
 * @date 2015-05-14 20:51:38
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/interfaceController")
public class InterfaceController extends BaseController {

	@Autowired
	private CCMOrderService cCMOrderService;
	@Autowired
	private CCMShopInfoService cCMShopInfoService;
	@Autowired
	private CCMSpTypeService cCMSpTypeService;
	@Autowired
	private CCMSpService cCMSpService;
	/**
	 * 
	* @Title: sendResultForJson
	* @Description: 数据封装为Json返回
	* @author zhangwei
	* @date 2015-6-4 上午10:33:16
	* @return void    返回类型
	* @throws
	 */
	protected void sendResultForJson(HttpServletResponse response, Object object) {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(JsonMapper.toJsonString(object));
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.debug("IO异常 |" + e.getMessage());
		}
	}

	
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: saveOrder
	* @Description: 订单生成接口
	* @author skl
	* @date 2016-10-20
	* @return ModelAndView    返回类型
	* @throws
	 */
	@RequestMapping(params = "saveOrder")
	public void saveOrder(OrderParam orderParam,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		String regCode="0";
		String regMsg="成功";
		cCMOrderService.saveOrder(orderParam, map, session);
//		map.put("regCode", regCode);
//		map.put("regMsg", regMsg);
		//map.put("orderList", list);
		sendResultForJson(response, map);
	}
	
	/*
	 * 
	* @Title: checkLogin
	* @Description: 验证登陆接口
	* @author skl
	* @date 2016-10-26
	 * */
	public void checkLogin(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		//String callback = request.getParameter("callback");
		Map<String, Object> map = new HashMap<String, Object>();
		String loginStatus = "0";
		map.put("regCode", "0");
		map.put("regMsg", "成功");
		Subject subject = SecurityUtils.getSubject();
		Session userSession = subject.getSession(false);
		if(null != userSession){
			loginStatus = "1";//已登录
		}else{
			loginStatus="0";//未登录
		}
		map.put("loginStatus", loginStatus);
		sendResultForJson(response, map);
	}
	
	/**
	 * 注销
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "cancel")
	public void cancel(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regCode", "0");
		map.put("regMsg", "成功");
		Subject subject = SecurityUtils.getSubject();
		Session userSession = subject.getSession(false);
		userSession.removeAttribute("user");
		sendResultForJson(response, map);
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: spTypeControl
	* @Description: 商品类型管理
	* @author skl
	* @date 2016-10-27
	* @return json    返回类型
	* @throws
	 */
	@RequestMapping(params = "spTypeControl")
	public void spTypeControl(SptypeParam sptypeParam,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		String regCode="0";
		String regMsg="成功";
		CCMSpType spType=new CCMSpType();
		//根据是否有id判断是增加还是修改
		if(sptypeParam.getId()!=null&&!sptypeParam.getId().equals(""))
		{
			//如果是修改则验证商品类别是否存在
			CCMSpType spTypeCheck=new CCMSpType();
			CCMSpType spType2 = cCMSpTypeService.get(sptypeParam.getId());
			if(spType2!=null)
			{
				spType=spType2;
				spType.setUpdateTime(new Date());
			}
			else
			{
				map.put("regCode", "-1");
				map.put("regMsg", "商品类别不存在");
				sendResultForJson(response, map);
			}
		}
		else
		{
			spType.setCreateTime(new Date());
			spType.setUpdateTime(new Date());
		}
		
		//判断级别是否为空
	 if(sptypeParam.getSpTypeName()!=null&&!sptypeParam.getSpTypeName().equals(""))
	 {
		 if(sptypeParam.getSpTypeLevel()!=null&&!sptypeParam.getSpTypeLevel().equals(""))
			{
				//如果不是1级菜单需要传递上级类别code
				if(!sptypeParam.getSpTypeLevel().equals("1"))
				{
					if(sptypeParam.getSpTypeParent()!=null&&!sptypeParam.getSpTypeParent().equals(""))
					{
						//判断上级类别是否存在
						CCMSpType spTypeQuery=new CCMSpType();
						{
							spTypeQuery.setSpTypeCode(sptypeParam.getSpTypeParent());
							List<CCMSpType> parent = cCMSpTypeService.findList(spTypeQuery);
							if(parent!=null&&parent.size()>0)
							{
								spType.setSpTypeParent(sptypeParam.getSpTypeParent());
							}
							else
							{
								regCode="-1";
								regMsg="上级类别无效";
							}
						}
					}
					else
					{
						regCode="-1";
						regMsg="上级菜单为空";
					}
				}
				spType.setSpTypeLevel(sptypeParam.getSpTypeLevel());
				//判断商户号是否为空
				if(sptypeParam.getShopCode()!=null&&!sptypeParam.getShopCode().equals(""))
				{
					//判断商户号是否存在
					CCMShopInfo cCMShopInfo=new  CCMShopInfo();
					cCMShopInfo.setShopCode(sptypeParam.getShopCode());
					List<CCMShopInfo> shopList = cCMShopInfoService.findList(cCMShopInfo);
					if(shopList!=null&&shopList.size()>0)
					{
						//保存商户号和商户名
						CCMShopInfo shop = shopList.get(0);
						spType.setShopCode(shop.getShopCode());
						spType.setShopName(shop.getShopName());
						if(spType.getId()==null||spType.getId().equals(""))
						{
							//生成商户类别编码（商户号+8位UUId）
							spType.setSpTypeCode(shop.getShopCode()+StringUtil.generateShortUuid());
						}
						spType.setSpTypeName(sptypeParam.getSpTypeName());
						cCMSpTypeService.save(spType);
					}
					else
					{
						regCode="-1";
						regMsg="未查询到商户";
					}
				}
				else
				{
					regCode="-1";
					regMsg="商户号为空";
				}
			}
			else
			{
				regCode="-1";
				regMsg="商品类别级别为空";
			}
	 }
	 else
	 {
		 regCode="-1";
		 regMsg="商品类别名称为空";
	 }
		map.put("regCode", regCode);
		map.put("regMsg", regMsg);
		sendResultForJson(response, map);
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: spTypeDelete
	* @Description: 商品类型删除
	* @author skl
	* @date 2016-10-27
	* @return json    返回类型
	* @throws
	 */
	@RequestMapping(params = "spTypeDelete")
	public void spTypeDelete(SptypeParam sptypeParam,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		String regCode="0";
		String regMsg="成功";
		if(sptypeParam.getId()!=null&&!sptypeParam.getId().equals(""))
		{
			CCMSpType spType = cCMSpTypeService.get(sptypeParam.getId());
			if(spType!=null)
			{
				//删除商品类别的同时，删除该商品类别对应的所有商品
				CCMSp sp=new CCMSp();
				sp.setSpTypeCode(spType.getSpTypeCode());
				List<CCMSp> spList = cCMSpService.findList(sp);
				if(spList!=null&&spList.size()>0)
				{
					for(int i=0;i<spList.size();i++)
					{
						cCMSpService.delete(spList.get(i));
					}
				}
				cCMSpTypeService.delete(spType);
			}
			else
			{
				regCode="-1";
				 regMsg="未查询到商品类别信息";
			}
		}
		else
		{
			 regCode="-1";
			 regMsg="商品类别id为空";
		}
		map.put("regCode", regCode);
		map.put("regMsg", regMsg);
		sendResultForJson(response, map);
	}
		
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: spControl
	* @Description: 商品管理
	* @author skl
	* @date 2016-10-27
	* @return json    返回类型
	* @throws
	 */
	@RequestMapping(params = "spControl")
	public void spControl(SptypeParam sptypeParam,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		
	}
}
