/*
 * File: app/view/login_panel.js
 *
 * This file was generated by Sencha Architect version 3.1.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 5.0.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 5.0.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('Login.view.login_panel', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.login_panel',

    requires: [
        'Login.view.login_panelViewModel',
        'Login.view.login_panelViewController',
        'Ext.form.Panel',
        'Ext.form.field.Text',
        'Ext.button.Button'
    ],

    controller: 'login_panel',
    viewModel: {
        type: 'login_panel'
    },

    layout: {
        type: 'hbox',
        align: 'middle',
        pack: 'center'
    },
    items: [
        {
            xtype: 'form',
            frame: true,
            width: 400,
            //standardSubmit:true,
            bodyPadding: 20,
            title: '登录',           
            items: [
                {
                    xtype: 'textfield',
                    anchor: '100%',
                    fieldLabel: '用户名称',
                    labelAlign: 'right',
                    msgTarget: 'title',
                    name: 'username',
                    allowBlank: false,
                    blankText: '请输入您的用户名称',
                    regexText: '请输入您的用户名称'
                },
                {
                    xtype: 'textfield',
                    anchor: '100%',
                    fieldLabel: '密码',
                    labelAlign: 'right',
                    msgTarget: 'title',
                    name: 'password',
                    inputType: 'password',
                    allowBlank: false,
                    blankText: '请输入您的密码'
                },
                {
                    xtype: 'container',
                    margin: '10 0 10 0',
                    layout: {
                        type: 'hbox',
                        align: 'middle',
                        pack: 'center'
                    },
                    items: [
                        {
                            xtype: 'button',
                            formBind: true,
                            margin:'0 15 0 0',
                            scale: 'medium',
                            text: '登录',
                            listeners: {
                                click: 'onLoginButtonClick'
                            }
                        }
                    ]
                }
            ]
        }
    ]

});