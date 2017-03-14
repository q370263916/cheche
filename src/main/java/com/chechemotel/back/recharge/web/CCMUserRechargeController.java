/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.recharge.web;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.chechemotel.back.recharge.entity.CCMUserRecharge;
import com.chechemotel.back.recharge.service.CCMUserRechargeService;

/**
 * 会员充值记录Controller
 * @author 方乙百
 * @version 2016-10-21
 */
@Controller
@RequestMapping(value = "${adminPath}/recharge/cCMUserRecharge")
public class CCMUserRechargeController extends BaseController {

	@Autowired
	private CCMUserRechargeService cCMUserRechargeService;
	
	@ModelAttribute
	public CCMUserRecharge get(@RequestParam(required=false) String id) {
		CCMUserRecharge entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCMUserRechargeService.get(id);
		}
		if (entity == null){
			entity = new CCMUserRecharge();
		}
		return entity;
	}
	
	@RequiresPermissions("recharge:cCMUserRecharge:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCMUserRecharge cCMUserRecharge, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCMUserRecharge> page = cCMUserRechargeService.findPage(new Page<CCMUserRecharge>(request, response), cCMUserRecharge); 
		model.addAttribute("page", page);
		return "chechemotel/back/recharge/cCMUserRechargeList";
	}
}