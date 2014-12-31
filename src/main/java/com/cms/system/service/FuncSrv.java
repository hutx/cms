package com.cms.system.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.system.util.BusiException;

@Service
@Transactional(readOnly = true)
public class FuncSrv extends BaseService{
	
private Logger logger = LoggerFactory.getLogger(FuncSrv.class);
	
	public List<Map<String, Object>> findOper() throws BusiException{
		String sql =" select * from sys_menu_url t where 1=1";
		logger.warn("查询功能菜单");
		return this.query(sql, null);
	}
}
