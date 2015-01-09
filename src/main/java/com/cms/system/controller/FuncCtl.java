package com.cms.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.system.bean.Tree;
import com.cms.system.bean.TreeMenu;
import com.cms.system.service.FuncSrv;
import com.cms.system.util.BusiException;

@Controller
@RequestMapping("/funcCtl")
public class FuncCtl {
	
	@Autowired
	private FuncSrv funcSrv;
	
	@RequestMapping(value = "/index")
	public String login() {
		return "func/index";
	}
	@RequestMapping(value = "/getfuncJson" )
	public @ResponseBody String getFuncJson() throws BusiException {
		//取出所有菜单
		List<Map<String, Object>> list = funcSrv.findOper();
		TreeMenu treeMenu= new TreeMenu();
		Tree tree =treeMenu.getTreeJson(list);
		System.out.println(tree.toJSONString());
		return tree.toJSONString();
	}
}
