package com.cms.system.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.system.util.BusiException;
import com.cms.system.util.SqlUtil;

@Service
public class BaseService {
	private Logger logger = LoggerFactory.getLogger(BaseService.class);
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public int update(String sql, Object[] args) throws BusiException{
		int n = 0;
		String logmsg = "";
		try {
			logmsg +="准备执行数据库更新操作,sql={"+sql+"},参数={"+getArgsString(args)+"}";
			long l1 = System.currentTimeMillis();
			n =  jdbcTemplate.update(sql, args);
			l1 = System.currentTimeMillis() - l1;
			logmsg +=",执行时间{"+l1+"},更新记录数{"+n+"}";
			if (l1>1000L){
				logger.warn("查询数据库性能异常,耗费时间={},sql={},参数={}",sql,getArgsString(args));
				//EventLog log = new EventLog(-1,null,EventLog.LOGTYPE_PERF,Constant.SYSMACHINE,Long.toString(l1),sql+getArgsString(args));
				//ExceptionHandle.put(log);
			}
			
		} catch (DataAccessException de) {
			if (de instanceof DuplicateKeyException){
				n = -1;
				logger.warn("更新数据异常:主键冲突");
			}else{
				logger.warn("更新数据异常",de);
			}
			logger.warn("更新数据异常,sql={},参数={}", sql, getArgsString(args));
			logger.info(logmsg);
			throw new BusiException(BusiException.ERR_DBERROR, "更新数据异常", de);
		}
		logger.info(logmsg);
		return n;
	}

	/**
	 * 获取数据库记录集
	 * 
	 * @param sql sql语句
	 * @param args 参数
	 * @param pagesize 每页记录数
	 * @param page 当前页码
	 * @return
	 */
	public List<Map<String, Object>> query(String sql, Object[] args,
			int pagesize, int page) throws BusiException{
		logger.info("执行查询语句{},pagesize={},page={}", sql+" "+ pagesize, page);
		if (pagesize > 1000)
			throw new BusiException("600002", "每次查询最大返回记录数不能超过1000条");
		String sql1 = SqlUtil.getLimitRecord(sql, page, pagesize);
		try {
			long l1 = System.currentTimeMillis();
			List list =  jdbcTemplate.queryForList(sql1, args);
			l1 = System.currentTimeMillis() - l1;
			if (l1>1000L){
				logger.warn("查询数据库性能异常,耗费时间={},sql={},参数={}",l1+" "+sql,getArgsString(args));
				//EventLog log = new EventLog(-1,null,EventLog.LOGTYPE_PERF,Constant.SYSMACHINE,Long.toString(l1),sql+getArgsString(args));
				//ExceptionHandle.put(log);
			}
			return list;
		} catch (DataAccessException de) {
			logger.warn("查询数据异常,sql={},参数={}", sql, getArgsString(args));
			logger.warn("查询数据异常",de);
			throw new BusiException(BusiException.ERR_DBERROR, "查询数据异常", de);
		}
	}

	/**
	 * 查询结果集，默认最大返回1000条记录
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<Map<String, Object>> query(String sql, Object[] args) throws BusiException{
		return query(sql, args, 1000, 1);
	}

	/**
	 * 查询单个对象，返回map
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public Map<String, Object> queryForMap(String sql, Object[] args) throws BusiException{
		List<Map<String, Object>> list = query(sql, args);
		if (list == null || list.size() == 0)
			return null;
		
		return list.get(0);
	}

	public int queryForInt(String sql, Object[] args) {
		return jdbcTemplate.queryForInt(sql, args);
	}

	/**
	 * 获取sql的记录条数
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public int getRecordCount(String sql, Object[] args) {
		String sql1 = SqlUtil.getCountSql(sql);
		return queryForInt(sql1, args);
	}

	private String getArgsString(Object[] args) {
		StringBuffer sb = new StringBuffer("args=[");		
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {				
				sb.append(args[i] + ",");
			}
		}

		String s = sb.toString();
		if (s.endsWith(","))
			s = s.substring(0, s.length() - 1);
		return s + "]";

	}
	@Transactional(readOnly = false)
	public  <T> String updateExecute(String callString,CallableStatementCallback<T>  action){
		String message= (String) jdbcTemplate.execute(callString, action);
		return message;
	}
	
	public String  getSelectWhereSql(String sql ,Object object) throws BusiException {
		Class c = object.getClass();
		Method[] methods = c.getMethods();
		Field[] fields =c.getFields();
		List<String> columnList = new ArrayList<String>();
		List<Object> vList = new ArrayList<Object>();
		for (Method method : methods) {
			String mName = method.getName();
			if (mName.startsWith("get")&& !mName.startsWith("getClass")) {
				Object value;
				try {
					value = method.invoke(object, null);
					if (value!=null) {
						//if (value instanceof String) {
							//if (!value.equals("")) {
								String fieldName = mName.substring(3, mName.length());
								columnList.add(fieldName.toUpperCase());
								vList.add(value);
							//}
						//}
					}
				} catch (Exception e) {
					logger.warn("反射机制取"+c.getName()+"---->"+mName+"方法值时出错", e);
					e.printStackTrace();
				} 				
			}
		}
		return sql;
	}
}
