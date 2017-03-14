/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.chechemotel.back.user.web;

import java.util.Date;
import java.util.List;
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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.chechemotel.back.user.entity.CCMUser;
import com.chechemotel.back.user.service.CCMUserService;
import com.chechemotel.util.QRCode;
import com.chechemotel.util.StringUtil;

/**
 * 会员管理Controller
 * @author 方乙百
 * @version 2016-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/user/cCMUser")
public class CCMUserController extends BaseController {

	@Autowired
	private CCMUserService cCMUserService;
	
	@ModelAttribute
	public CCMUser get(@RequestParam(required=false) String id) {
		CCMUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCMUserService.get(id);
		}
		if (entity == null){
			entity = new CCMUser();
		}
		return entity;
	}
	
	@RequiresPermissions("user:cCMUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCMUser cCMUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCMUser> page = cCMUserService.findPage(new Page<CCMUser>(request, response), cCMUser); 
		model.addAttribute("page", page);
		return "chechemotel/back/user/cCMUserList";
	}

	@RequiresPermissions("user:cCMUser:view")
	@RequestMapping(value = "form")
	public String form(CCMUser cCMUser, Model model) {
		model.addAttribute("cCMUser", cCMUser);
		return "chechemotel/back/user/cCMUserForm";
	}

	@RequiresPermissions("user:cCMUser:edit")
	@RequestMapping(value = "save")
	public String save(CCMUser cCMUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCMUser)){
			return form(cCMUser, model);
		}
		if(cCMUser.getId()!=null&&!cCMUser.getId().equals(""))
		{
			cCMUser.setUpdateTime(new Date());
		}
		else
		{
			cCMUser.setCreateTime(new Date());
			cCMUser.setUpdateTime(new Date());
			cCMUser.setStatus("1");
			cCMUser.setCanTxZje("0");//可提现金额
			cCMUser.setMoney("0");//可用余额
			cCMUser.setJifen("0");//积分
			cCMUser.setRedBegZje("0");//红包
			cCMUser.setZje("0");//消费总金额
			//生成会员编码
			cCMUser.setUserCode(UUID.randomUUID().toString());
			//生成推荐码
			String tjm = StringUtil.createYQM();
			//判断推荐码是否已经存在
			CCMUser user=new CCMUser();
			user.setTjm(tjm);
			List<CCMUser> userList = cCMUserService.findList(user);
			if(userList==null||userList.size()==0)
			{
				cCMUser.setTjm(tjm);
			}
		
			while(userList != null&&userList.size()>0){
			tjm = StringUtil.createYQM();
			user.setTjm(tjm);
		   userList = cCMUserService.findList(user);
			if(userList == null){
				cCMUser.setTjm(tjm);
				break;
			}
			}
			//会员生成二维码
			try {
				String codeUrl = QRCode.encode(tjm);
				cCMUser.setQrCode(codeUrl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		cCMUserService.save(cCMUser);
		addMessage(redirectAttributes, "保存会员管理成功");
		return "redirect:"+Global.getAdminPath()+"/user/cCMUser/?repage";
	}
	
	@RequiresPermissions("user:cCMUser:edit")
	@RequestMapping(value = "delete")
	public String delete(CCMUser cCMUser, RedirectAttributes redirectAttributes) {
		cCMUserService.delete(cCMUser);
		addMessage(redirectAttributes, "删除会员管理成功");
		return "redirect:"+Global.getAdminPath()+"/user/cCMUser/?repage";
	}

}