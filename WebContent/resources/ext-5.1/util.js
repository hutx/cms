var CmsAjax = function(url, params, fn) {
	Ext.Viewport.setMasked({
		xtype : 'loadmask',
		message : ''
	});
	Ext.Ajax.request({
		url : url,
		method : 'POST',
		params : params,
		success : function(response) {
			Ext.Viewport.setMasked(false);
			var obj = Ext.decode(response.responseText);			
			fn(obj);			
		},
		failure : function(response, opts) {
			Ext.Viewport.setMasked(false);
			Ext.Msg.alert("提示信息", "通讯异常，请重试");
			return;
		}
	});
};
