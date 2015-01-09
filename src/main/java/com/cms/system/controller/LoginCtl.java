package com.cms.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cms.system.bean.Tree;
import com.cms.system.bean.TreeMenu;
import com.cms.system.service.FuncSrv;
import com.cms.system.service.LoginSrv;
import com.cms.system.util.JsonResult;
import com.cms.system.util.SqlUtil;
import com.cms.system.util.UserContext;

@Controller
@RequestMapping("/loginCtl")
// @SessionAttributes("formBean")
public class LoginCtl {

	@Resource
	private LoginSrv loginSrv;
	
	@Autowired
	private FuncSrv funcSrv;

	/*@Autowired
	public void setLoginSrv(LoginSrv loginSrv) {
		this.loginSrv = loginSrv;
	}*/

	@RequestMapping(value = "/index")
	public String login() {
		return "login/index";
	}

	@RequestMapping(value = "/login")
	public @ResponseBody JsonResult processLogin(String username, String password, Model model,
			RedirectAttributes redirectAttrs ,HttpServletRequest request, HttpServletResponse response) throws Exception {
		JsonResult result = new JsonResult();
		/*
		 * if (result.hasErrors()) { return null; }
		 */
		Map<String, Object> map = loginSrv.findOper(new Object[] { username,
				DigestUtils.md5DigestAsHex(password.getBytes()) });
		if (map==null) {
			//model.addAttribute("message","您输入的有误请重新输入");
			result.setResult(1);
			result.setMessage("您输入的有误请重新输入！");
		}
		UserContext userContext = new UserContext();
		//判断是否管理员
		if(SqlUtil.getInt(map, "administrator")>0){
			//取出所有菜单
			List<Map<String, Object>> list = funcSrv.findOper();
			TreeMenu treeMenu= new TreeMenu();
			Tree tree =treeMenu.getTreeJson(list);
			userContext.setAttribute("tree", tree);
			//System.out.println(tree.toJSONString());
		}else{
		//判断权限
		//根据权限取当前用户对应的菜单
			
		}
		userContext.setAttribute("map", map);
		HttpSession session = request.getSession(true);
		session.setAttribute("userContext", userContext);
		return result;
	}
	@RequestMapping(value="/loginSuccess", method = RequestMethod.GET)
	public String loginSuccess(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
