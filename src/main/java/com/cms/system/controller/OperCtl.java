package com.cms.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.system.service.OperSrv;
import com.cms.system.util.BusiException;
import com.cms.system.util.JsonResult;

@Controller
@RequestMapping("/operCtl")
public class OperCtl {
	
	@Autowired
	private OperSrv operSrv;
	
	@RequestMapping(value = "/index")
	public String operIndex() {
		return "oper/index";
	}
	/**
	 * 按条件查询用户信息
	 * @param map
	 * @return
	 * @throws BusiException
	 * hutianxin 2015年1月29日 下午3:00:40
	 */
	@RequestMapping(value = "/findOperWhereByInfo")
	public @ResponseBody JsonResult findOperWhereByInfo(@RequestParam Map<String, Object> map) throws BusiException {
		JsonResult result =new JsonResult();
		result.setResult(0);
		List<Map<String, Object>> operList = operSrv.findOperWhereByInfoPage(map);
		result.set("root", operList);
		return result;
	}
	
	/**
	 * 添加用户信息
	 * @param map
	 * @return
	 * @throws BusiException
	 * hutianxin 2015年1月29日 下午3:30:51
	 */
	@RequestMapping(value = "/add")
	public @ResponseBody JsonResult addOperInfo(@RequestParam Map<String, Object> map) throws BusiException {
		JsonResult result =new JsonResult();
		result.setResult(0);
		int i =operSrv.addOper(map);
		if (i<1) {
			throw new BusiException("08002", "新增用户信息失败！");
		}
		result.setMessage("操作成功！");
		return result;
	}
	
	/**
	 * 修改用户信息
	 * @param map
	 * @return
	 * @throws BusiException
	 * hutianxin 2015年1月29日 下午3:37:48
	 */
	@RequestMapping(value = "/update")
	public @ResponseBody JsonResult updateOperInfo(@RequestParam Map<String, Object> map) throws BusiException {
		JsonResult result =new JsonResult();
		result.setResult(0);
		int i =operSrv.updateOper(map);
		if (i<1) {
			throw new BusiException("08003", "修改用户信息失败！");
		}
		result.setMessage("操作成功！");
		return result;
	}
	/**
	 * 删除用户信息
	 * @param username
	 * @return
	 * @throws BusiException
	 * hutianxin 2015年1月29日 下午3:42:18
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody JsonResult deleteOperInfo(String username) throws BusiException {
		JsonResult result =new JsonResult();
		result.setResult(0);
		int i =operSrv.deleteOper(username);
		if (i<1) {
			throw new BusiException("08003", "删除用户信息失败！");
		}
		result.setMessage("操作成功！");
		return result;
	}
	
}
