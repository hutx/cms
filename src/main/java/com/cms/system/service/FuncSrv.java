package com.cms.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.system.util.BusiException;

@Service
@Transactional(readOnly = true)
public class FuncSrv extends BaseService {

	private Logger logger = LoggerFactory.getLogger(FuncSrv.class);

	public List<Map<String, Object>> findOper() throws BusiException {
		String sql = " select * from sys_menu_url t where 1=1";
		logger.warn("查询功能菜单");
		return this.query(sql, null);
	}
	/**
	 * 添加功能菜单
	 * @param items
	 * @return
	 * @throws BusiException
	 * hutianxin 2015年1月20日 上午10:49:14
	 */
	public int addFunc(Map<String, Object> items) throws BusiException {
		int i = 0;
		String sql = " insert into sys_menu_url (id ,companyid ,desc_ ,fornavigation ,icon ,name ,order_ ,parentid ,url) values (?,?,?,?,?,?,?,?,?)";
		List<Object> param =new ArrayList<Object>();
		param.add(items.get("id"));
		param.add("cms");
		param.add(items.get("desc"));
		param.add(items.get("fornavigation"));
		param.add(items.get("icon"));
		param.add(items.get("name"));
		param.add(items.get("order"));
		param.add(items.get("parentid"));
		param.add(items.get("url"));
		i = this.update(sql, param.toArray());
		logger.warn("添加功能菜单");
		return i;
	}
	/**
	 * 修改功能 菜单
	 * @param items
	 * @return
	 * @throws BusiException
	 * hutianxin 2015年1月20日 上午11:04:22
	 */
	public int updateFunc(Map<String, Object> items) throws BusiException {
		int i = 0;
		String sql = " update sys_menu_url t set desc_=? ,fornavigation=? ,icon = ?,name =? ,order_=? ,parentid=? ,url=? where t.id =?";
		List<Object> param =new ArrayList<Object>();		
		param.add(items.get("desc"));
		param.add(items.get("fornavigation"));
		param.add(items.get("icon"));
		param.add(items.get("name"));
		param.add(items.get("order"));
		param.add(items.get("parentid"));
		param.add(items.get("url"));
		param.add(items.get("id"));
		i = this.update(sql, param.toArray());
		logger.warn("修改功能菜单");
		return i;
		
	}
	/**
	 * 根据id删除功能菜单 及子菜单
	 * @param id
	 * @return
	 * @throws BusiException
	 * hutianxin 2015年1月20日 下午2:08:45
	 */
	public int deleteFunc(String id) throws BusiException {
		int i = 0;
		String sql = " delete from sys_menu_url  where id=? or parentid=?";
		i=this.update(sql, new Object[]{id,id});
		logger.warn("删除功能菜单");
		return i;
	}
}
