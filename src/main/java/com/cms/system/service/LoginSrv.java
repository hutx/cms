package com.cms.system.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.system.util.BusiException;

@Service
@Transactional(readOnly = true)
public class LoginSrv extends BaseService{
	
	private Logger logger = LoggerFactory.getLogger(LoginSrv.class);
	
	public Map<String, Object> findOper(Object [] args) throws BusiException{
		String sql =" select * from sys_oper t where t.username=? and t.password=?";
		logger.warn("查询系统操作人员表");
		return this.queryForMap(sql, args);
	}
}
