package com.cms.system.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cms.system.service.LoginSrv;
import com.cms.system.util.JsonResult;
import com.cms.system.util.MD5;
import com.cms.system.util.SqlUtil;

@Controller
@RequestMapping("/loginCtl")
// @SessionAttributes("formBean")
public class LoginCtl {

	@Resource
	private LoginSrv loginSrv;

	/*@Autowired
	public void setLoginSrv(LoginSrv loginSrv) {
		this.loginSrv = loginSrv;
	}*/

	@RequestMapping(value = "/index")
	public String login() {
		return "login/index";
	}

	@RequestMapping(value = "/login")
	public String processLogin(String username, String password, Model model,
			RedirectAttributes redirectAttrs) throws Exception {
		
		/*
		 * if (result.hasErrors()) { return null; }
		 */
		Map<String, Object> map = loginSrv.findOper(new Object[] { username,
				MD5.getMD5(password) });
		if (map==null) {
			model.addAttribute("message","您输入的有误请重新输入");
			return "login/index";
		}
		//判断是否管理员
		if(SqlUtil.getInt(map, "administrator")>0){
			//取出所有菜单
			
		}else{
		//判断权限
		//根据权限取当前用户对应的菜单
			
		}
		return "main";
	}
	
	/*@RequestMapping(value = "/login")
	public @ResponseBody JsonResult processLogin(String username, String password, Model model,
			RedirectAttributes redirectAttrs) throws Exception {
		JsonResult result = new JsonResult();
		
		
		 * if (result.hasErrors()) { return null; }
		 
		Map<String, Object> map = loginSrv.findOper(new Object[] { username,
				MD5.getMD5(password) });
		if (map==null) {
			result.setResult(1);
			result.setMessage("您输入的有误请重新输入！");
			return result;
		}
		return result;
	}*/
}
