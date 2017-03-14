/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.sptype.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.chechemotel.back.shopinfo.entity.CCMShopInfo;
import com.chechemotel.back.shopinfo.service.CCMShopInfoService;
import com.chechemotel.back.sptype.entity.CCMSpType;
import com.chechemotel.back.sptype.service.CCMSpTypeService;
import com.chechemotel.util.StringUtil;

/**
 * 商品类型Controller
 * @author songkailiang
 * @version 2016-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/sptype/cCMSpType")
public class CCMSpTypeController extends BaseController {

	@Autowired
	private CCMSpTypeService cCMSpTypeService;
	@Autowired
	private CCMShopInfoService cCMShopInfoService;
	
	@ModelAttribute()
	public CCMSpType get(@RequestParam(required=false) String id) {
		CCMSpType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCMSpTypeService.get(id);
		}
		if (entity == null){
			entity = new CCMSpType();
		}
		return entity;
	}
	
	@RequiresPermissions("sptype:cCMSpType:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCMSpType cCMSpType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCMSpType> page = cCMSpTypeService.findPage(new Page<CCMSpType>(request, response), cCMSpType); 
		model.addAttribute("page", page);
		return "chechemotel/back/sptype/cCMSpTypeList";
	}

	@RequiresPermissions("sptype:cCMSpType:view")
	@RequestMapping(value = "form")
	public String form(CCMSpType cCMSpType, Model model) {
		List<CCMSpType> levelLists=new ArrayList<CCMSpType>();
		List<CCMSpType> levelList = cCMSpTypeService.findLevelOneListFormDb();
		if(levelList!=null&&levelList.size()>0)
		{
			model.addAttribute("levelList", levelList);
		}
		else
		{
			model.addAttribute("levelList", levelLists);
		}
		List<CCMSpType> levelList2 = cCMSpTypeService.findLevelTwoListFormDb();
		if(levelList2!=null&&levelList2.size()>0)
		{
			model.addAttribute("levelList2", levelList2);
		}
		else
		{
			model.addAttribute("levelList2", levelLists);
		}
		CCMShopInfo cCMShopInfo=new CCMShopInfo();
		List<CCMShopInfo> cCMShopList = cCMShopInfoService.findList(cCMShopInfo);
		model.addAttribute("cCMShopList", cCMShopList);
		model.addAttribute("cCMSpType", cCMSpType);
		
		return "chechemotel/back/sptype/cCMSpTypeForm";
	}

	@RequiresPermissions("sptype:cCMSpType:edit")
	@RequestMapping(value = "save")
	public String save(CCMSpType cCMSpType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCMSpType)){
			return form(cCMSpType, model);
		}
		
		if(cCMSpType.getId()!=null&&!cCMSpType.getId().equals(""))
		{
			cCMSpType.setUpdateTime(new Date());
		}
		else
		{
			cCMSpType.setCreateTime(new Date());
			cCMSpType.setUpdateTime(new Date());
			if(cCMSpType.getShopName()!=null&&!cCMSpType.getShopName().equals(""))
			{
				CCMShopInfo cCMShopInfo=new  CCMShopInfo();
				cCMShopInfo.setShopName(cCMSpType.getShopName());
				List<CCMShopInfo> shopList = cCMShopInfoService.findList(cCMShopInfo);
				if(shopList!=null&&shopList.size()>0)
				{
					CCMShopInfo cCMShopInfos = shopList.get(0);
					cCMSpType.setShopCode(cCMShopInfos.getShopCode());
					cCMSpType.setSpTypeCode(cCMShopInfos.getShopCode()+StringUtil.generateShortUuid());
				}
			}
			else
			{
				addMessage(redirectAttributes, "请先添加商户");
				return "redirect:"+Global.getAdminPath()+"/sptype/cCMSpType/?repage";
				//return "chechemotel/back/sptype/cCMSpTypeForm";
			}
		}
		if(cCMSpType.getSpTypeLevel()!=null&&cCMSpType.getSpTypeLevel().equals("2"))
		{
			if(cCMSpType.getSpTypeParent1()!=null&&!cCMSpType.getSpTypeParent1().equals(""))
			{
				cCMSpType.setSpTypeParent(cCMSpType.getSpTypeParent1());
			}
			else
			{
				addMessage(redirectAttributes, "请先添加一级商品");
				return "redirect:"+Global.getAdminPath()+"/sptype/cCMSpType/?repage";
				//return "chechemotel/back/sptype/cCMSpTypeForm";
			}
		}
		
		if(cCMSpType.getSpTypeLevel()!=null&&cCMSpType.getSpTypeLevel().equals("3"))
		{
			if(cCMSpType.getSpTypeParent2()!=null&&!cCMSpType.getSpTypeParent2().equals(""))
			{
				cCMSpType.setSpTypeParent(cCMSpType.getSpTypeParent2());
			}
			else
			{
				addMessage(redirectAttributes, "请先添加二级商品");
				return "redirect:"+Global.getAdminPath()+"/sptype/cCMSpType/?repage";
				//return "chechemotel/back/sptype/cCMSpTypeForm";
			}
		}
		
		
		cCMSpTypeService.save(cCMSpType);
		addMessage(redirectAttributes, "保存商品类型成功");
		return "redirect:"+Global.getAdminPath()+"/sptype/cCMSpType/?repage";
	}
	
	@RequiresPermissions("sptype:cCMSpType:edit")
	@RequestMapping(value = "delete")
	public String delete(CCMSpType cCMSpType, RedirectAttributes redirectAttributes) {
		CCMSpType cs = cCMSpTypeService.get(cCMSpType.getId());
		if(cs.getSpTypeLevel()!=null&&(cs.getSpTypeLevel().equals("1")||cs.getSpTypeLevel().equals("2")))
		{
			CCMSpType csp=new CCMSpType();
			csp.setSpTypeCode(cs.getSpTypeCode());
			List<CCMSpType> list = cCMSpTypeService.findList(csp);
			if(list!=null&&list.size()>0)
			{
				addMessage(redirectAttributes, "请先删除该商品对应的下级商品");
				return "redirect:"+Global.getAdminPath()+"/sptype/cCMSpType/?repage";
			}
		}
		cCMSpTypeService.delete(cCMSpType);
		addMessage(redirectAttributes, "删除商品类型成功");
		return "redirect:"+Global.getAdminPath()+"/sptype/cCMSpType/?repage";
	}

}