/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.shopinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chechemotel.back.shoptype.entity.CCMShopType;
import com.chechemotel.back.shoptype.service.CCMShopTypeService;
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
import com.chechemotel.back.shopinfo.entity.CCMShopInfo;
import com.chechemotel.back.shopinfo.service.CCMShopInfoService;

import java.util.*;

/**
 * 商户信息管理Controller
 * @author 方乙百
 * @version 2016-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/shopinfo/cCMShopInfo")
public class CCMShopInfoController extends BaseController {

	@Autowired
	private CCMShopInfoService cCMShopInfoService;

	@Autowired
	private CCMShopTypeService ccmShopTypeService;
	
	@ModelAttribute
	public CCMShopInfo get(@RequestParam(required=false) String id) {
		CCMShopInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCMShopInfoService.get(id);
		}
		if (entity == null){
			entity = new CCMShopInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("shopinfo:cCMShopInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCMShopInfo cCMShopInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCMShopInfo> page = cCMShopInfoService.findPage(new Page<CCMShopInfo>(request, response), cCMShopInfo); 
		model.addAttribute("page", page);
		return "chechemotel/back/shopinfo/cCMShopInfoList";
	}

	@RequiresPermissions("shopinfo:cCMShopInfo:view")
	@RequestMapping(value = "form")
	public String form(CCMShopInfo cCMShopInfo, Model model) {
		model.addAttribute("cCMShopInfo", cCMShopInfo);
		Map<String,String> map = ccmShopTypeService.getShopTypeByCheckbox();
		model.addAttribute("shopTypeList", ccmShopTypeService.findList(new CCMShopType()));
		model.addAttribute("shopTypeMap", map);
		String shopType = cCMShopInfo.getShopType();
		if (StringUtils.isNotBlank(shopType)){
			String[] split = shopType.split(",");
			List<String> list = new ArrayList<String>();
			for (String str :split){
				list.add(str);
			}
			model.addAttribute("shopTypeList2", list);
		}

		return "chechemotel/back/shopinfo/cCMShopInfoForm";
	}

	@RequiresPermissions("shopinfo:cCMShopInfo:edit")
	@RequestMapping(value = "save")
	public String save(CCMShopInfo cCMShopInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCMShopInfo)){
			return form(cCMShopInfo, model);
		}
		if (StringUtils.isNotBlank(cCMShopInfo.getId())){
			cCMShopInfo.setUpdateTime(new Date());
		}else{
			cCMShopInfo.setCreateTime(new Date());
		}
		cCMShopInfoService.save(cCMShopInfo);
		addMessage(redirectAttributes, "保存商户信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/shopinfo/cCMShopInfo/?repage";
	}
	
	@RequiresPermissions("shopinfo:cCMShopInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CCMShopInfo cCMShopInfo, RedirectAttributes redirectAttributes) {
		cCMShopInfoService.delete(cCMShopInfo);
		addMessage(redirectAttributes, "删除商户信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/shopinfo/cCMShopInfo/?repage";
	}

}