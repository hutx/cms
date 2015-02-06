package com.cms.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.system.form.OperBean;
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

	public Pager findOperWhereByInfoPage(OperBean operBean,int pagenum) throws BusiException {
		
		String sql ="select * from sys_oper t  ";
		String condition="";
		String username =operBean.getUsername();
		List<Object> paramList=new ArrayList<Object>();
		if (username!=null && !username.equals("")) {
			condition +="username=?";
			paramList.add(username);
		}
		if (paramList.size()>0) {
			sql+=" where "+condition;
		}
		//Map<String, Object> items=this.getTable(map, "where");
		//List<String> column = (List<String>)items.get("column");
		//List<Object> parame = (List<Object>)items.get("parame");	
		int count =this.getRecordCount(sql, paramList.toArray());
		Pager page = new Pager(pagenum,0,count);
		List<Map<String, Object>> operList =this.query(sql, paramList.toArray(), page.getPageSize(), pagenum);
		page.setDataRoot(operList);
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
