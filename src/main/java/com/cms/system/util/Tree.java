package com.cms.system.util;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.minidev.json.JSONArray;

/**
 * ext树菜单
 * 
 */
@XmlRootElement
public class Tree implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String text;
	private String iconCls;
	private boolean expanded;
	private boolean leaf;
	private String url;
	private int isUsable;
	private String desc;
	private int order;
	private List<Tree> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public int getIsUsable() {
		return isUsable;
	}

	public void setIsUsable(int isUsable) {
		this.isUsable = isUsable;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public List<Tree> getChildren() {
		return children;
	}	

	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	
	public String toJSONString() {		
		return JSONArray.toJSONString(this.getChildren());
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
