package com.cms.system.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * web层返回的json对象
 * @author shujq
 *
 */
public class JsonResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4757288033112816628L;
	
	
	private int result = 0;//处理成功返回0，其他错误
	private int action;//下一步处理类型0 停留在当前页面 1 跳转到指定页面
	private String message;//如果message不为空，则页面弹出消息框
	private boolean success =true;

	/**
	 * 如果message不为空，弹出消息框的类型 
	 * alert:直接调用
	 * model:遮窗弹出
	 */
	private String msgmode = "alert";
	private String errmsg;
	
	private String url;
	
	private List<Map<String,Object>> root =new ArrayList<Map<String,Object>>();
	
	

	private Map<String,Object> data = new HashMap<String,Object>();
	
	public void set(String key,Object value){
		data.put(key, value);
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	
	public List<Map<String, Object>> getRoot() {
		return root;
	}

	public void setRoot(List<Map<String, Object>> root) {
		this.root = root;
	}	
	

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		if (result ==0) {
			this.success=true;
		}else {
			this.success=false;
		}
		this.result = result;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMsgmode() {
		return msgmode;
	}

	public void setMsgmode(String msgmode) {
		this.msgmode = msgmode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		this.action = 1;
	}
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	
	

}
