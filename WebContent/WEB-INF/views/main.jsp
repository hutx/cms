<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cms.system.util.UserContext"%>
<%@page import="com.cms.system.bean.Tree"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CMS</title>
<script type="text/javascript" src="/resources/ext-5.1/shared/include-ext.js" ></script>
<script type="text/javascript" src="/resources/ext-5.1/shared/options-toolbar.js" ></script>
<script type="text/javascript" src="/pages/sys/main/app.js"></script>
<script type="text/javascript">
	//生成菜单
	var menu_panel= Ext.getCmp('menu_panel');
	<%
		Tree tree = (Tree)session.getAttribute("tree");
		if(tree !=null){
			for(Tree node : tree.getChildren()){
			%>
				//创建menu_panel
				var menu = new Ext.tree.Panel({
					id:'<%=node.getId()%>',
					title:'<%=node.getText()%>',
				})
			<%
			}
		}
	%>
</script>
</head>
<body>

</body>
</html>