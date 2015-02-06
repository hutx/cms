package com.cms.system.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.system.service.FuncSrv;
import com.cms.system.util.BusiException;
import com.cms.system.util.JsonResult;
import com.cms.system.util.Tree;
import com.cms.system.util.TreeMenu;

@Controller
@RequestMapping("/funcCtl")
public class FuncCtl {
	
	@Autowired
	private FuncSrv funcSrv;
	
	@RequestMapping(value = "/index")
	public String login() {
		return "func/index";
	}
	/**
	 * 生成菜单树
	 * @return
	 * @throws BusiException
	 * hutianxin 2015年1月20日 上午10:23:54
	 */
	@RequestMapping(value = "/getfuncJson" )
	public @ResponseBody String getFuncJson() throws BusiException {
		//取出所有菜单
		List<Map<String, Object>> list = funcSrv.findOper();
		TreeMenu treeMenu= new TreeMenu();
		Tree tree =treeMenu.getTreeJson(list);
		System.out.println(tree.toJSONString());
		return tree.toJSONString();
	}
	/**
	 * 添加菜单
	 * @param items
	 * @return
	 * @throws BusiException
	 * hutianxin 2015年1月20日 上午10:23:29
	 */
	@RequestMapping(value = "/add")
	public @ResponseBody JsonResult addFuncInfo(@RequestParam Map<String, Object> map) throws BusiException{
		JsonResult result =new JsonResult();
		result.setResult(0);
		String uuid = UUID.randomUUID().toString();
		map.put("id", uuid);
		int i =funcSrv.addFunc(map);
		if (i<1) {
			throw new BusiException("07001", "新增菜单失败！");
		}
		result.set("uuid", uuid);
		result.setMessage("操作成功！");
		return result;
	}
	/**
	 * 添加菜单
	 * @param items
	 * @return
	 * @throws BusiException
	 * hutianxin 2015年1月20日 上午10:23:29
	 */
	@RequestMapping(value = "/update")
	public @ResponseBody JsonResult updateFuncInfo(@RequestParam Map<String, Object> map) throws BusiException{
		JsonResult result =new JsonResult();
		result.setResult(0);
		int i =funcSrv.updateFunc(map);
		if (i<1) {
			throw new BusiException("07001", "修改菜单失败！");
		}
		result.setMessage("操作成功！");
		return result;
	}
	/**
	 * 删除菜单
	 * @param id
	 * @return
	 * @throws BusiException
	 * hutianxin 2015年1月20日 下午2:16:48
	 */
	@RequestMapping(value = "/del")
	public @ResponseBody JsonResult delFuncInfo(String id)throws BusiException{
		JsonResult result =new JsonResult();
		result.setResult(0);
		result.setResult(0);
		int i =funcSrv.deleteFunc(id);
		if (i<1) {
			throw new BusiException("07001", "删除菜单失败！");
		}
		result.setMessage("操作成功！");
		return result;
	}
}
