package com.cms.system.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.system.util.BusiException;
import com.cms.system.util.Pager;
/**
 * 
 * @author Administrator
 * hutianxin 2015年1月29日
 *
 */
@Service
@Transactional(readOnly = true)
public class OperSrv extends BaseService{
	
	private Logger logger = LoggerFactory.getLogger(OperSrv.class);

	public Pager findOperWhereByInfoPage(
			Map<String, Object> map,int pagenum) throws BusiException {
		Pager page = new Pager();
		String sql ="select *from sys_oper t  ";
		//Map<String, Object> items=this.getTable(map, "where");
		//List<String> column = (List<String>)items.get("column");
		//List<Object> parame = (List<Object>)items.get("parame");
		page.setTotalPages(this.getRecordCount(sql, null));
		List<Map<String, Object>> oper =this.query(sql, null, page.getPageSize(), pagenum);
		page.setPageElements(oper);
		return page ;
		
	}

	public int addOper(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
		
	}

	public int updateOper(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
		
	}

	public int deleteOper(String username) {
		// TODO Auto-generated method stub
		return 0;
		
	}

}
