/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.shoprole.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chechemotel.back.shopinfo.entity.CCMShopInfo;
import com.chechemotel.back.shopinfo.service.CCMShopInfoService;
import com.chechemotel.util.RsaCodingUtil;
import com.chechemotel.util.RsaReadUtil;
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
import com.chechemotel.back.shoprole.entity.CCMShopRole;
import com.chechemotel.back.shoprole.service.CCMShopRoleService;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * 商户权限管理Controller
 * @author 方乙百
 * @version 2016-10-20
 */
@Controller
@RequestMapping(value = "${adminPath}/shoprole/cCMShopRole")
public class CCMShopRoleController extends BaseController {

	@Autowired
	private CCMShopRoleService cCMShopRoleService;

	@Autowired
	private CCMShopInfoService ccmShopInfoService;

	@ModelAttribute
	public CCMShopRole get(@RequestParam(required=false) String id) {
		CCMShopRole entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCMShopRoleService.get(id);
		}
		if (entity == null){
			entity = new CCMShopRole();
		}
		return entity;
	}
	
	@RequiresPermissions("shoprole:cCMShopRole:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCMShopRole cCMShopRole, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCMShopRole> page = cCMShopRoleService.findPage(new Page<CCMShopRole>(request, response), cCMShopRole); 
		model.addAttribute("page", page);
		return "chechemotel/back/shoprole/cCMShopRoleList";
	}

	@RequiresPermissions("shoprole:cCMShopRole:view")
	@RequestMapping(value = "form")
	public String form(CCMShopRole cCMShopRole, Model model) {
		String password = cCMShopRole.getPassword();
		String pkStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALiFxn09f+UeK85Yo2f+tTPt+u8T" +
                "NdMhI88Vu3C88hwm413bgTBsMPmN466e8k3Cv02LjDRLfq7zZqKR0bnrcKQXBbmA47+LWpHeYiRi" +
                "uPlQq4K7XNBuwKkfiDD4pEQuFoKesuXcT1qNmQwD68V25E9wKi4uTim9AIMMmlzStDltAgMBAAEC" +
                "gYAvjrMAtmgPKj6KGUhMVlZngu+IzL+zobfz+JrtzJTt8ovpNdjPHSNmPk6vQ+IZbpcVqmDBXa/R" +
                "8YPDZKfULj1CbLeijnIbGj8+gjk4rztPEqtjut3HSgXvpsZJbFE5kITxQn005+Ud0wzOeNc9muvh" +
                "yx8a/iim0vQjX0X38FLhgQJBAPNp5R6zpbBr2gJU79QUDtBg+918ZlH3HjB2Kvdnyb7XO+YSSJgI" +
                "uZJl/z61jMKweEyd5VDR1OS3FQVn/TLpAo0CQQDCEFRj0JB9s+LlQV+nbKMd4E65mcE/Tym2Zy6Z" +
                "PbkMQXJozw+9Cd9oAorzDENPh2o2bxPI7KM82PPCJ/Fnw8phAkAuamX/acBRmO9UfH/TEBQXFjdQ" +
                "79TPEnFWSmI9FTR6SK6qW5guLZO9X+fBPHtf8Kiv63f75ldTWboPctnsaFRNAkBlHqcqf5HavjIb" +
                "bU4Pam8wcSH0Wia0+2Xr0qYYWW4j8bJQ5Jv5z7wmcTS/4oLsXM4+YPC5QHzqhs21PKTwgbfhAkEA" +
                "imBHe41dvGPWfLiktKcZjNgDoAidCDlT2KU183IgwB8S2AmCLcaHjqnPDXi8zQT4BFiFs/lO9gCL" +
                "nLxJTMR/Yw==";
		PrivateKey privateKey = RsaReadUtil.getPrivateKey(pkStr);
		String dp = RsaCodingUtil.decryptByPrivateKey(password,privateKey);
		cCMShopRole.setPassword(dp);
		model.addAttribute("cCMShopRole", cCMShopRole);
		model.addAttribute("shopInfoSelect", ccmShopInfoService.findList(new CCMShopInfo()));
		return "chechemotel/back/shoprole/cCMShopRoleForm";
	}

	@RequiresPermissions("shoprole:cCMShopRole:edit")
	@RequestMapping(value = "save")
	public String save(CCMShopRole cCMShopRole, Model model, RedirectAttributes redirectAttributes) {
		cCMShopRole.setType("1");
		cCMShopRole.setFlag("1");
		String password = cCMShopRole.getPassword();
		String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4hcZ9PX/lHivOWKNn/rUz7frvEzXTISPPFbtw" +
				"vPIcJuNd24EwbDD5jeOunvJNwr9Ni4w0S36u82aikdG563CkFwW5gOO/i1qR3mIkYrj5UKuCu1zQ" +
				"bsCpH4gw+KRELhaCnrLl3E9ajZkMA+vFduRPcCouLk4pvQCDDJpc0rQ5bQIDAQAB";
		PublicKey publicKey = RsaReadUtil.getPubKey(pubKey);
		String s = RsaCodingUtil.encryptByPublicKey(password, publicKey);
		cCMShopRole.setPassword(s);
		if (StringUtils.isNotBlank(cCMShopRole.getId())){
			cCMShopRole.setUpdateTime(new Date());
		}else{
			cCMShopRole.setCreateTime(new Date());
		}
		if (!beanValidator(model, cCMShopRole)){
			return form(cCMShopRole, model);
		}
		cCMShopRoleService.save(cCMShopRole);
		addMessage(redirectAttributes, "保存商户权限成功");
		return "redirect:"+Global.getAdminPath()+"/shoprole/cCMShopRole/?repage";
	}
	
	@RequiresPermissions("shoprole:cCMShopRole:edit")
	@RequestMapping(value = "delete")
	public String delete(CCMShopRole cCMShopRole, RedirectAttributes redirectAttributes) {
		cCMShopRoleService.delete(cCMShopRole);
		addMessage(redirectAttributes, "删除商户权限成功");
		return "redirect:"+Global.getAdminPath()+"/shoprole/cCMShopRole/?repage";
	}

}