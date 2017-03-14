/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.shoptype.web;

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
import com.chechemotel.back.shoptype.entity.CCMShopType;
import com.chechemotel.back.shoptype.service.CCMShopTypeService;

import java.util.Date;

/**
 * 商户类别Controller
 * @author 方乙百
 * @version 2016-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/shoptype/cCMShopType")
public class CCMShopTypeController extends BaseController {

	@Autowired
	private CCMShopTypeService cCMShopTypeService;
	
	//@ModelAttribute("cCMShopType")
	@ModelAttribute
	public CCMShopType get(@RequestParam(required=false) String id) {
		CCMShopType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCMShopTypeService.get(id);
		}
		if (entity == null){
			entity = new CCMShopType();
		}
		return entity;
	}
	
	@RequiresPermissions("shoptype:cCMShopType:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCMShopType cCMShopType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCMShopType> page = cCMShopTypeService.findPage(new Page<CCMShopType>(request, response), cCMShopType); 
		model.addAttribute("page", page);
		return "chechemotel/back/shoptype/cCMShopTypeList";
	}

	@RequiresPermissions("shoptype:cCMShopType:view")
	@RequestMapping(value = "form")
	public String form(CCMShopType cCMShopType, Model model) {
		model.addAttribute("cCMShopType", cCMShopType);
		return "chechemotel/back/shoptype/cCMShopTypeForm";
	}

	@RequiresPermissions("shoptype:cCMShopType:edit")
	@RequestMapping(value = "save")
	public String save(CCMShopType cCMShopType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCMShopType)){
			return form(cCMShopType, model);
		}
		if (StringUtils.isNotBlank(cCMShopType.getId())){
			cCMShopType.setUpdateTime(new Date());
		}else{
			cCMShopType.setCreateTime(new Date());
		}
		boolean b = cCMShopTypeService.getShopTypeByCode(cCMShopType.getTypeCode());
		if (!b){
			addMessage(redirectAttributes, "类别编码重复，请重新输入");
			return "redirect:"+Global.getAdminPath()+"/shoptype/cCMShopType/form";
			//return "chechemotel/back/shoptype/cCMShopTypeForm";
		}
		cCMShopTypeService.save(cCMShopType);
		addMessage(redirectAttributes, "保存商户类别成功");
		return "redirect:"+Global.getAdminPath()+"/shoptype/cCMShopType/?repage";
	}
	
	@RequiresPermissions("shoptype:cCMShopType:edit")
	@RequestMapping(value = "delete")
	public String delete(CCMShopType cCMShopType, RedirectAttributes redirectAttributes) {
		cCMShopTypeService.delete(cCMShopType);
		addMessage(redirectAttributes, "删除商户类别成功");
		return "redirect:"+Global.getAdminPath()+"/shoptype/cCMShopType/?repage";
	}

}