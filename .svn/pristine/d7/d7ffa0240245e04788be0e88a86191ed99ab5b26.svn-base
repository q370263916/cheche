/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.order.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.Page2;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.chechemotel.back.order.entity.CCMOrder;
import com.chechemotel.back.order.service.CCMOrderService;
import com.chechemotel.logs.service.MongoLogServiceI;

/**
 * 订单Controller
 * @author 方乙百
 * @version 2016-10-20
 */
@Controller
@RequestMapping(value = "${adminPath}/order/cCMOrder")
public class CCMOrderController extends BaseController {

	@Autowired
	private CCMOrderService cCMOrderService;
	@Autowired
	private MongoLogServiceI logService;
	
	@ModelAttribute
	public CCMOrder get(@RequestParam(required=false) String id) {
		CCMOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCMOrderService.get(id);
		}
		if (entity == null){
			entity = new CCMOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("order:cCMOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCMOrder cCMOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCMOrder> page = cCMOrderService.findPage(new Page<CCMOrder>(request, response), cCMOrder); 
		model.addAttribute("page", page);
		return "chechemotel/back/order/cCMOrderList";
	}
	
	//商户流水日志
	@RequestMapping("orderLogList")
	public String orderLogList(CCMOrder cCMOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page2<Object> page=new Page2<Object>(request, response);
		Map<String, Object> whereMap = new HashMap<String, Object>();
		if(cCMOrder.getOrderCode()!=null&&!cCMOrder.getOrderCode().equals(""))
		{
			whereMap.put("orderCode", cCMOrder.getOrderCode());
		}
		if(cCMOrder.getShopCode()!=null&&!cCMOrder.getShopCode().equals(""))
		{
			whereMap.put("shopCode", cCMOrder.getShopCode());
		}
		List<Map<String, Object>> rsList = logService.findPageOrderCodeLog(null, whereMap, page.getPageNo(), page.getPageSize());
		int leng = logService.findPageOrderCodeLogCount(null, whereMap, page.getPageNo(), page.getPageSize());
		Page2<Object> page2 = cCMOrderService.findPageByList(page, rsList);
		page2.setCount((long)leng);
		List<Map<String, Object>> list = page2.getList();
		model.addAttribute("page", page2);
		return "chechemotel/back/order/cCMOrderLogList";
	}

}