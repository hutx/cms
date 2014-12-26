<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <meta http-equiv="X-UA-Compatible"">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title id="page-title">Pandora</title>


    <!-- GC -->

    <script type="text/javascript" src="${ctx}/resources/ext-5.1/shared/include-ext.js"></script>
    <script type="text/javascript" src="${ctx}/resources/ext-5.1/shared/options-toolbar.js"></script>
   <script type="text/javascript">
        function hasOption (name) {
            return window.location.search.indexOf(name) >= 0;
        }

        Ext.Loader.setConfig({enabled: true});
        Ext.Loader.setPath('Ext.ux', '../ux');
        Ext.require([
            'Ext.grid.*',
            'Ext.data.*',
            'Ext.util.*',
            'Ext.Action',
            'Ext.tab.*',
            'Ext.button.*',
            'Ext.form.*',
            'Ext.layout.container.Card',
            'Ext.layout.container.Border',
            'Ext.ux.ajax.SimManager',
            'Ext.ux.PreviewPlugin'
        ]);
        Ext.onReady(function(){
            if (hasOption('simjax')) {
                initAjaxSim();
            }

            var app = new FeedViewer.App();
        });
    </script>

    <script type="text/javascript" src="viewer/FeedPost.js"></script>
    <script type="text/javascript" src="viewer/FeedDetail.js"></script>
    <script type="text/javascript" src="viewer/FeedGrid.js"></script>
    <script type="text/javascript" src="viewer/FeedInfo.js"></script>
    <script type="text/javascript" src="viewer/FeedPanel.js"></script>
    <script type="text/javascript" src="viewer/FeedViewer.js"></script>
    <script type="text/javascript" src="viewer/FeedWindow.js"></script>
    <script type="text/javascript" src="viewer/Sim.js"></script>
</head>
<body>
</body>
</html>
    