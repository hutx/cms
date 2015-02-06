<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cms.system.util.UserContext"%>
<%@page import="com.cms.system.util.Tree"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CMS</title>
<script type="text/javascript" src="/resources/ext-5.1/shared/include-ext.js" ></script>
<script type="text/javascript" src="/resources/ext-5.1/shared/options-toolbar.js" ></script>
<script type="text/javascript" src="/pages/sys/main/app.js"></script>
<script type="text/javascript">

function showTree(){
	//生成菜单
	var menu_panel= Ext.getCmp('menu_panel');
	var tab_panel = Ext.getCmp('contentTab');
	<%
		UserContext userContext=(UserContext)session.getAttribute("userContext");
		Tree tree = (Tree)userContext.getAttribute("tree");
	if(tree !=null){
	for(Tree node : tree.getChildren()){
	%>
		var tab,url,nodeId ;
		//创建menu_panel
		menu_panel.add(new Ext.tree.Panel({
			id:'<%=node.getId()%>',
			title:'<%=node.getText()%>',
			rootVisible: false,
			store:new Ext.data.TreeStore({
				root:{
					expanded:true,
					children:<%=node.toJSONString()%>
				}
			}),
		   listeners:{
			   'rowclick':function(t,record,tr,rowIndex,e ,eOpts){
				   if(record.get('leaf')){//判断是否叶子节点
					   nodeId = record.get("id");
					   url =record.get("url");
					   tab = tab_panel.getComponent(nodeId);
				   	   if(!tab){
				   		    var newTab = {
				   				id:nodeId,
				   				title:record.get('text'),
				   				//autoScroll:true,
				   				closable:true,
                                layout:'fit',
                                html:'<iframe id="innerFrame_"'+nodeId +'scrolling="auto" frameborder="0" width="100%" height="100%" src='+url+'></iframe>'
					   		}
					   		tab_panel.add(newTab).show();
					   }else{
						   tab_panel.setActiveTab(tab);
					   }
				   }
			   }			
		   }
		}));
	<%
	}
}
%>
}
</script>
</head>
<body>

</body>
</html>