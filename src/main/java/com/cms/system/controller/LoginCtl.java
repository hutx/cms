package com.cms.system.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cms.system.service.LoginSrv;
import com.cms.system.util.MD5;

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
			redirectAttrs.addAttribute("message", "您输入的有误请重新输入！");
			return "login";
		}
		return "form";
	}
}
