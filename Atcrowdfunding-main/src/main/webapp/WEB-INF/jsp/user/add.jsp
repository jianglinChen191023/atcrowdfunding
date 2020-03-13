<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/css/doc.min.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <%@ include file="/WEB-INF/jsp/common/top.jsp" %>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<%@ include file="/WEB-INF/jsp/common/menu.jsp" %>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				 <li><a href="${APP_PATH }/main.htm">首页</a></li>
				  <li><a href="${APP_PATH }/user/index.htm">数据列表</a></li>
				  <li class="active">新增</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="addForm" role="form">
				  <div class="form-group">
					<label for="floginacct">登陆账号</label>
					<input onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'') " type="text" class="form-control" lang="12"  id="floginacct" placeholder="请输入登陆账号" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" style="ime-mode:disabled;"  autocomplete="off" autofocus>
				  	<p id="pLoginacct_notnull" style="display:none"  class="help-block label label-warning">用户账号不能为空!</p>
				  	<p id="pLoginacct_6" style="display:none"  class="help-block label label-warning">用户账号不能小于6位数!</p>
				  	<p id="pLoginacct_doLoginacct" style="display:none"  class="help-block label label-warning">账号已存在</p>
				  	
				  </div>
				  <div class="form-group">
					<label for="fusername">用户名称</label>
					<input type="text" class="form-control" maxlength="20" id="fusername" placeholder="请输入用户名称"  onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" style="ime-mode:disabled" autocomplete="off">
				 	<p id="pUsername_notnull" style="display:none"  class="help-block label label-warning">用户名不能为空!</p>
				 	<p id="pUsername_3_20" style="display:none"  class="help-block label label-warning">用户名必须为 3-20 个字符!</p>
				  </div>
				  <div class="form-group">
					<label for="femail">邮箱地址</label>
					<input maxlength="30" onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'') " type="email" class="form-control" id="femail"  placeholder="请输入邮箱地址" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" style="ime-mode:disabled" autocomplete="off">
					<p id="pemail" style="display:none" class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
				  </div>
				  <button id="addBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
				  <button id="resetBen"  type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>测试标题1</h4>
				<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>测试标题2</h4>
				<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
    <script type="text/javascript">

            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
            });
            
            //重置
            $("#resetBen").click(function(){
            	 $("#addForm")[0].reset();//将JQuery对象转换为dom对象;JQuery对象中没有reset()函数
            });
            
			var floginacct = $("#floginacct");
			var fusername = $("#fusername");
			var femail = $("#femail");
            
            $("#floginacct").keyup(function(){
            	doLoginacct();
            });
            
            $("#femail").keyup(function(){
            	doEmail();
            });       

            $("#fusername").keyup(function(){
             	doUsername();
            });

           	$("#addBtn").click(function(){
            	if(doLoginacct() && doEmail() && doUsername()){
            		doAdd();
            	}
            });
            
            //保存
           function doAdd(){
           var loadingIndex = -1;
	           $.ajax({ 
		           	type : "POST",
		           	data : {
		           		"loginacct" : floginacct.val(),
		           		"username" : fusername.val(),
		           		"email" : femail.val()
		           	},		
		           	url : "${APP_PATH}/user/doAdd.do",
		           	beforeSend : function() {
		           		loadingIndex = layer.msg('处理中', {icon: 16});
		           		//一般做表单数据验证 
		           		return true;	
		           	},
		           	success : function(result){
		           		layer.close(loadingIndex);	
		           		if(result.success){
		           			window.location.href="${APP_PATH}/user/toIndex.htm";
		           		}else{
		           			layer.msg(result.message, {time:1000, icon:5, shift:7});
		           		}
		           		
		           	},
		           	error : function(){
		           		layer.msg("保存失败!", {time:1000, icon:5, shift:7});
		           	}
	           		
	            });
            } 
            
            
           var Bln = false;
            //账号 
			function doLoginacct(){
				
				$.ajax({
            		type : "POST", 
            		data : {
            			"loginacct" : floginacct.val()
            		},
            		url : "${APP_PATH}/user/doUser.do",
            		beforeSend : function(){
            			//一般用于校验 
            			//账号             	
                    	if(floginacct.val() == ""){
                    		//layer.msg("用户账号不能为空!", {time:1000, icon:5, shift:7});
                    		//$("#pLoginacct").html("用户账号不能为空!");
                    		$("#pLoginacct_notnull").show();
                    		$("#pLoginacct_doLoginacct").hide();
                    		$("#pLoginacct_6").hide();

                    		return false;
                    	}else if(floginacct.val().length < 6){
                    		//layer.msg("用户账号不能小于6位数!", {time:1000, icon:5, shift:7});
                    		//$("#pLoginacct").html("用户账号不能小于6位数!");
                    		$("#pLoginacct_6").show();
                    		$("#pLoginacct_notnull").hide();
                    		$("#pLoginacct_doLoginacct").hide();

                   			return false;
                    	}else{
//                     		$("#pLoginacct").html("showAddBtn");
        					$("#pLoginacct_notnull").hide();
                    		$("#pLoginacct_6").hide();
                   			return true;
                    	}
 
            		},
            		success : function(result){
            			if(result.success){
            				//layer.msg("账号已存在!", {time:1000, icon:5, shift:7});
            				//$("#pLoginacct").html("账号已存在!");
            				$("#pLoginacct_doLoginacct").show();
            				bln = false;
            			}else{
							if(result.message != null){
								layer.msg(result.message, {time:1000, icon:5, shift:7});
							}
							$("#pLoginacct_doLoginacct").hide();
							bln = true;
            			}
            			return bln;
            		},
            		error : function(){
            			layer.msg("校验账号失败!", {time:1000, icon:5, shift:7});
            			bln = false;
            		} 
            	});
				return bln;
			}	
			
        	//邮箱
        	function doEmail(){
        		
//         		alert($("#femail").val().substring(0));
//         		alert($("#femail").val().lastIndexOf(".")+1);
//         		alert($("#femail").val().substring($("#femail").val().lastIndexOf(".")+1));
//        		alert($("#femail").val().substring($("#femail").val().lastIndexOf(".")+1) == "com");
            	var bds = /^[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]\.[a-zA-Z][a-zA-Z\.]*[a-zA-Z]$/;	
        		if(!bds.test(femail.val())){
     				//layer.msg("邮箱格式不正确!", {time:1000, icon:5, shift:7});
     				$("#pemail").html("请输入合法的邮箱地址, 格式为： xxxx@xxxx.com");
     				$("#pemail").show();
     				
     				bln = false;
				}else{
					if($("#femail").val().toLowerCase().substring($("#femail").val().lastIndexOf(".")+1) != "com"){
						$("#pemail").html("邮箱后缀必须为com!");
	        			$("#pemail").show();
						
						bln = false;
					}else{
						$("#pemail").hide();
						bln = true;
					}
        			
				}
        		return bln;
        	}
        	

        	//账号名称       
        	function doUsername(){
        		 	
            	if(fusername.val() == ""){
            		//layer.msg("用户名称不能为空!", {time:1000, icon:5, shift:7});
            		//$("#pusername").html("用户名称不能为空!");
            		$("#pUsername_notnull").show();
            		("#pUsername_3_20").hide();
            		
            		bln = false;
            	}else if(fusername.val().length <3 || fusername.val().length >20){
            		//layer.msg("用户名必须为 3-20 个字符!", {time:1000, icon:5, shift:7});
            		//$("#pusername").html("用户名必须为 3-20 个字符!");
            		$("#pUsername_3_20").show();
            		$("#pUsername_notnull").hide();
            		
            		bln = false;
            	}else{
            		$("#pUsername_3_20").hide();
        			$("#pUsername_notnull").hide();
        			bln = true;
            	}
            		
        		return bln;
			}	
			
//             $("#addBtn").click(function(){
            	
//             	var floginacct = $("#floginacct");
//             	var fusername = $("#fusername");
//             	var femail = $("#femail");
//             	var pLoginacct = $("#pLoginacct");
//             	var pUsername = $("#pUsername");
//             	var pemail = $("#pemail");
//             	var bds = /^[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]\.[a-zA-Z][a-zA-Z\.]*[a-zA-Z]$/;
//             	var bdsLogin = /^[1-9]d*$/;
            	
//             	function check(){
//             			doLoginacct();
//             			doUsername();
//             			doEmail();
//             			doAdd();
//             	}
            	
//             	check();
            	
				
				
// 				function doLoginacct(){
// 					//账号             	
//                 	if(floginacct.val() == ""){
//                 		layer.msg("用户账号不能为空!", {time:1000, icon:5, shift:7});
                		
//                 		return false;
//                 	}
    				
//                 	if(floginacct.val().length < 6){
//                 		layer.msg("用户账号不能小于6位数!", {time:1000, icon:5, shift:7});
                		
//                 		return false;
//                 	}
					
// 					$.ajax({
// 	            		type : "POST", 
// 	            		data : {
// 	            			"loginacct" : floginacct.val()
// 	            		},
// 	            		url : "${APP_PATH}/user/doUser.do",
// 	            		beforeSend : function(){
	            			
// 	            			return true ;
// 	            		},
// 	            		success : function(result){
	            			
// 	            			if(result.success){
// 	            				//layer.msg("账号已存在!", {time:1000, icon:5, shift:7});
// 	            				$("#pLoginacct").show();
// 	            			}else{
// 								if(result.message != null){
// 									layer.msg(result.message, {time:1000, icon:5, shift:7});
// 								}
// 								$("#pLoginacct").hide();
// 	            			}
// 	            		},
// 	            		error : function(){
// 	            			layer.msg("校验账号失败!", {time:1000, icon:5, shift:7});
// 	            		} 
// 	            	});
// 				}	 
				
// 				//账号名称       
//             	function doUsername(){
            		 	
//                 	if(fusername.val() == ""){
//                 		layer.msg("用户名称不能为空!", {time:1000, icon:5, shift:7});
                		
//                 		return false;
//                 	}
                	
//                 	if(fusername.val().length <3 || fusername.val().length >20){
//                 		layer.msg("用户名必须为 3-20 个字符!", {time:1000, icon:5, shift:7});
                		
//                 		return false;
//                 	}
// 					$.ajax({
// 	            		type : "POST", 
// 	            		data : {
// 	            			"username" : fusername.val()
// 	            		},
// 	            		url : "${APP_PATH}/user/doUser.do",
// 	            		beforeSend : function(){
	            			
// 	            			return true ;
// 	            		},
// 	            		success : function(result){
	            			
// 	            			if(result.success){
// 	            				//layer.msg("名称已存在!", {time:1000, icon:5, shift:7});
// 	            				$("#pUsername").show();
// 	            				stopUsername = "false" ;
// 	            			}else{
// 								if(result.message != null){
// 									layer.msg(result.message, {time:1000, icon:5, shift:7});
// 									stopUsername = "false" ;
// 								}
// 								$("#pUsername").hide();
// 	            			}
// 	            		},
// 	            		error : function(){
// 	            			layer.msg("校验名称失败!", {time:1000, icon:5, shift:7});
// 	            		} 
// 	            	});
// 				}	

            	
				
//             	//邮箱 
//             	function doEmail(){
//             		if(!bds.test(femail.val())){
//          				layer.msg("邮箱格式不正确!", {time:1000, icon:5, shift:7});
//          				$("#pemail").show();
         				
// 						return false;
// 					}else{
// 						$("#pemail").hide();
// 					}
//             	}
         		
				
            	
            	
//             	//保存
//             	function doAdd(){
//             		var loadingIndex = -1;
//                 	$.ajax({ 
//                 		type : "POST",
//                 		data : {
//                 			"loginacct" : floginacct.val(),
//                 			"username" : fusername.val(),
//                 			"email" : femail.val()
//                 		},		
//                 		url : "${APP_PATH}/user/doAdd.do",
//                 		beforeSend : function() {
//                 			//一般做表单数据验证 
//                 			loadingIndex = layer.msg('处理中', {icon: 16});
//                 			return true;
//                 		},
//                 		success : function(result){
//                 			layer.close(loadingIndex);	
//                 			if(result.success){
//                 				window.location.href="${APP_PATH}/user/toIndex.htm";
//                 			}else{
//                 				layer.msg(result.message, {time:1000, icon:5, shift:7});
//                 			}
                			
//                 		},
//                 		error : function(){
//                 			layer.msg("保存失败!", {time:1000, icon:5, shift:7});
//                 		}
                			
//                 	});
//             	}
            	
//             	alert(pLoginacct.display);
//             });

            
        </script>
  </body>
</html>
    