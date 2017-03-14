package com.chechemotel.back.userOrderLog.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.chechemotel.back.user.entity.CCMUser;
import com.chechemotel.back.user.service.CCMUserService;
import com.chechemotel.logs.service.MongoLogServiceI;
import com.chechemotel.util.QRCode;
import com.chechemotel.util.StringUtil;

/**
 * 会员管理Controller
 * @author skl
 * @version 2016-10-25
 */
@Controller
@RequestMapping(value = "${adminPath}/user/userOrderLog")
public class UserOrderLogController extends BaseController {

	@Autowired
	private CCMUserService cCMUserService;
	@Autowired
	private MongoLogServiceI logService;
	@Autowired
	private CCMOrderService cCMOrderService;
	
	//商户流水日志
		@RequestMapping("userOrderLogList")
		public String userOrderLogList(CCMOrder cCMOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
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
			List<Map<String, Object>> rsList = logService.findPageUserOrderLog(null, whereMap, page.getPageNo(), page.getPageSize());
			int leng = logService.findPageUserOrderLogCount(null, whereMap, page.getPageNo(), page.getPageSize());
			Page2<Object> page2 = cCMOrderService.findPageByList(page, rsList);
			page2.setCount((long)leng);
			model.addAttribute("page", page2);
			return "chechemotel/back/user/userOrderLogList";
		}
	

}