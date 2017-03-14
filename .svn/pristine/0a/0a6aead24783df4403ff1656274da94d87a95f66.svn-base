/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.orderstream.web;

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
import com.chechemotel.back.orderstream.entity.CCMOrderStream;
import com.chechemotel.back.orderstream.service.CCMOrderStreamService;

/**
 * 订单流水Controller
 * @author 方乙百
 * @version 2016-10-20
 */
@Controller
@RequestMapping(value = "${adminPath}/orderstream/cCMOrderStream")
public class CCMOrderStreamController extends BaseController {

	@Autowired
	private CCMOrderStreamService cCMOrderStreamService;
	
	@ModelAttribute
	public CCMOrderStream get(@RequestParam(required=false) String id) {
		CCMOrderStream entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCMOrderStreamService.get(id);
		}
		if (entity == null){
			entity = new CCMOrderStream();
		}
		return entity;
	}
	
	@RequiresPermissions("orderstream:cCMOrderStream:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCMOrderStream cCMOrderStream, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCMOrderStream> page = cCMOrderStreamService.findPage(new Page<CCMOrderStream>(request, response), cCMOrderStream); 
		model.addAttribute("page", page);
		return "chechemotel/back/orderstream/cCMOrderStreamList";
	}

}