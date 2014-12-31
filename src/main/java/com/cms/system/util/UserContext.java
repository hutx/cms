
package com.cms.system.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * 本类是用户标识的一个缺省实现。 
 */
public class UserContext {
    
	public Integer seq=null;
    /**
     * 上下文参数
     */
    public String context = null;
    
    /**
     * 原上下文参数
     */
    public String oldContext = null;
    
    /**
     * 客户登录代码
     */
    private String loginID = null;
    
    /**
     * 客户登录密码
     */
    private String password = null;   
    /**
     * 用户代码
     */
    private String operID;
    
    /**
     * 用户名称
     */
    private String operName;
    
    /**
     * 机构代码
     */
    private String orgID;
    
    /**
     * 机构名称
     */
    private String orgName;
    
    /**
     * 角色
     */
    private String[][] role;
    
    /**
     * 部门代码
     */
    private String deptID;
    
    /**
     * 部门名称
     */
    private String deptName;
    
   
    
    /**
     * 登录客户代码
     */
    private String custLoginID;
    /**
     * 登录客户状态
     */
    private boolean custLoginStatus;
    /**
     * 客户经理主机登录状态
     */
    private boolean hostLoginStatus;  
    /**
     * 操作员类型，判断该用户是企业客户还是个人客户
     */
    private String operType;
	/**
	 * 登录类型类型，
	 */
	private String loginType;
	/**
	 * 登录密码
	 */
	private String pwd;    

    private HashMap map = new HashMap();

    /*功能的操作权限Map<String url,String[]>*/
    private Map funcOperMap = new HashMap();
    
    /*菜单权限*/

    
    //当前用户所拥有的角色
	private List operRoleList;
	
	
    //当前用户所关联的部门
	private List deptList;
	
    //当前用户所关联的部门的字串表达
	private String relatedDepts;	
	
	/**
	 * Sets the 上下文参数.
	 * @param context The context to set
	 */
	public void setContext(String context) {
        this.oldContext = this.context;
		this.context = context;
	}
    
    /**
     * 还原上下文参数
     */
    public void restoreContext() {
        this.context = this.oldContext;
        this.oldContext = null;
    }

    /**
     * set  value.
     */
    public void setAttribute(String name,Object obj) {
        map.put(name,obj);
    }
    
    /**
     * @see 
     */
    public Object getAttribute(String name) {
        return map.get(name);
    }
    
    /**
     * @return String
     */
    public String getContext() {
        return context;
    }

    /**
     * @return String
     */
    public String getOldContext() {
        return oldContext;
    }

    /**
     * @return int
     */
    public String getOperID() {
        return this.operID;
    }

    /**
     * @return String
     */
    public String getOperName() {
        return this.operName;
    }

    /**
     * @return String
     */
    public String getOrgID() {
        return this.orgID;
    }

    /**
     * @return String
     */
    public String getOrgName() {
        return this.orgName;
    }

    /**
     * Sets the oldContext.
     * @param oldContext The oldContext to set
     */
    public void setOldContext(String oldContext) {
        this.oldContext = oldContext;
    }

    /**
     * Sets the operID.
     * @param operID The operID to set
     */
    public void setOperID(String operID) {
        this.operID = operID;
    }

    /**
     * Sets the operName.
     * @param operName The operName to set
     */
    public void setOperName(String operName) {
        this.operName = operName;
    }

    /**
     * Sets the orgID.
     * @param orgID The orgID to set
     */
    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    /**
     * Sets the orgName.
     * @param orgName The orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return String
     */
    public String getDeptID() {
        return deptID;
    }

    /**
     * @return String
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @return String
     */
    public String[][] getRole() {
        return role;
    }

    /**
     * Sets the deptID.
     * @param deptID The deptID to set
     */
    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    /**
     * Sets the deptName.
     * @param deptName The deptName to set
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * Sets the roleID.
     * @param roleID The roleID to set
     */
    public void setRole(String[][] role) {
        this.role = role;
    }

	/**
	 * 返回当前登录的客户代码
	 * @return Returns the custLoginID.
	 */
	public String getCustLoginID() {
		return custLoginID;
	}

	/**
	 * 设置登录客户代码时，同时将状态设为true
	 * @param custLoginID The custLoginID to set.
	 */
	public void setCustLoginID(String custLoginID) {
		this.custLoginID = custLoginID;
		setCustLoginStatus( true );
	}
	
	

	/**
	 * 返回客户登录状态
	 * @return Returns the custLoginStatus.
	 */
	public boolean isCustLogin() {
		return custLoginStatus;
	}

	/**
	 * 设置登录状态为false时，同时将登录客户ID设置为null
	 * @param custLoginStatus The custLoginStatus to set.
	 */
	public void setCustLoginStatus(boolean custLoginStatus) {
		this.custLoginStatus = custLoginStatus;
		if( !custLoginStatus ) {
			custLoginID = null;
		}
	}

	/**
	 * @return Returns the hostLoginStatus.
	 */
	public boolean isHostLoginStatus() {
		return hostLoginStatus;
	}

	/**
	 * @param hostLoginStatus The hostLoginStatus to set.
	 */
	public void setHostLoginStatus(boolean hostLoginStatus) {
		this.hostLoginStatus = hostLoginStatus;
	}

	/**
	 * @return Returns the operType.
	 */
	public String getOperType() {
		return operType;
	}

	/**
	 * @param operType The operType to set.
	 */
	public void setOperType(String operType) {
		this.operType = operType;
	}

	/**
	 * @return 返回登录代码
	 */
	public String getLoginID() {
		return loginID;
	}

	/**
	 * @param 设置登录代码
	 */
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	/**
	 * @return 返回登录类型
	 */
	public String getLoginType() {
		return loginType;
	}

	/**
	 * @param 设置登录类型
	 */
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	/**
	 * @return 返回登录密码
	 */
	public String getPwd() {
		return this.pwd;
	}

	/**
	 * @param 设置登录密码
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Map getFuncOperMap() {
		return funcOperMap;
	}

	public void setFuncOperMap(Map funcOperMap) {
		this.funcOperMap = funcOperMap;
	}

	

	public List getOperRoleList() {
		return operRoleList;
	}

	public void setOperRoleList(List operRoleList) {
		this.operRoleList = operRoleList;
	}

	public List getDeptList() {
		return deptList;
	}

	public void setDeptList(List deptList) {
		this.deptList = deptList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	
		
	
}