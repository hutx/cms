package com.cms.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 菜单类1.1
 * 
 */
public class TreeMenu {

	private List<Map<String, Object>> list;
	private Map<String, Object> root;

	/**
	 * 组合json
	 * 
	 * @param list
	 * @param node
	 */
	private Tree getNodeJson(List<Map<String, Object>> list, Map<String, Object> node) {
		Tree tree = new Tree();
		tree.setId(SqlUtil.getString(node, "id"));
		tree.setText(SqlUtil.getString(node, "name"));
		tree.setIsUsable(SqlUtil.getInt(node, "fornavigation"));
		tree.setDesc(SqlUtil.getString(node, "desc_"));
		tree.setOrder(SqlUtil.getInt(node, "order_"));
		tree.setChildren(new ArrayList<Tree>());
		if (list == null) {
			// 防止没有权限菜单时
			return tree;
		}
		if (hasChild(list, node)) {
			List<Tree> lt = new ArrayList<Tree>();
			tree.setUrl("");
			
			//tree.setExpanded(node.getExpanded() == 1 ? true : false);
			List<Map<String, Object>> childList = getChildList(list, node);
			tree.setLeaf(childList.size() > 0 ?  false: true);
			Iterator<Map<String, Object>> it = childList.iterator();
			while (it.hasNext()) {
				Map<String, Object> modules = (Map<String, Object>) it.next();
				// 递归
				lt.add(getNodeJson(list, modules));
			}
			tree.setChildren(lt);
			// } else if ((node.getParentId() == root.getModuleId()) ||
			// node.getModuleUrl() == null) {
			// // 防止是主菜单,或者主菜单里面的url为空，但是下面没有子菜单的时候
			// tree.setUrl("");
			// tree.setLeaf(node.getLeaf() == 1 ? true : false);
			// tree.setExpanded(node.getExpanded() == 1 ? true : false);
		} else {
			tree.setUrl(SqlUtil.getString(node, "url"));
			tree.setLeaf( true);
			//tree.setExpanded(node.getExpanded() == 1 ? true : false);
		}

		return tree;
	}

	/**
	 * 判断是否有子节点
	 */
	private boolean hasChild(List<Map<String, Object>> list, Map<String, Object> node) {
		return getChildList(list, node).size() > 0 ? true : false;
	}

	/**
	 * 得到子节点列表
	 */
	private List<Map<String, Object>> getChildList(List<Map<String, Object>> list, Map<String, Object> modules) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		Iterator<Map<String, Object>> it = list.iterator();
		while (it.hasNext()) {
			Map<String, Object> temp = (Map<String, Object>) it.next();
			if (SqlUtil.getString(temp, "parentid").equals( SqlUtil.getString(modules, "id"))) {
				li.add(temp);
			}
		}
		return li;
	}

	public Tree getTreeJson(List<Map<String, Object>> list) {
		this.list=list;
		// 父菜单的id为0
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("id", "");
		this.root=map;
		return this.getNodeJson(this.list, this.root);
	}
}
