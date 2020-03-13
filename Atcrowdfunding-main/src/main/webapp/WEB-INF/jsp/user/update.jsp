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
					<!-- 树节点 -->
					<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="${APP_PATH }/main.htm">首页</a></li>
				  <li><a href="${APP_PATH }/user/index.htm">数据列表</a></li>
				  <li class="active">修改</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据 <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="updateForm" role="form">
				  <div class="form-group">
					<label for="exampleInputPassword1">登陆账号</label>
				 	<input disabled="true" onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'') " type="text" class="form-control" id="floginacct" value="${user.loginacct }" placeholder="请输入登陆账号" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" style="ime-mode:disabled;"  autocomplete="off" autofocus>
<!-- 				  	<p id="pLoginacct_notnull" style="display:none"  class="help-block label label-warning">用户账号不能为空!</p> -->
<!-- 				  	<p id="pLoginacct_6" style="display:none"  class="help-block label label-warning">用户账号不能小于6位数!</p> -->
<!-- 				  	<p id="pLoginacct_doLoginacct" style="display:none"  class="help-block label label-warning">账号已存在</p> -->
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">用户名称</label>
				  	<input type="text" class="form-control" maxlength="20" id="fusername" placeholder="请输入用户名称" value="${user.username }" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" style="ime-mode:disabled" autocomplete="off">
				 	<p id="pUsername_notnull" style="display:none"  class="help-block label label-warning">用户名不能为空!</p>
				 	<p id="pUsername_3_20" style="display:none"  class="help-block label label-warning">用户名必须为 3-20 个字符!</p>
				  </div>
				  <div class="form-group">
					<label for="exampleInputEmail1">邮箱地址</label>
					<input maxlength="30" onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'') " value="${user.email }" type="email" class="form-control" id="femail"  placeholder="请输入邮箱地址" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" style="ime-mode:disabled" autocomplete="off">
					<p id="pemail" style="display:none" class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
				  </div>
				  <button id="updateBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>
				  <button id="resetBtn" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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
			    doUsername();
           	 	doEmail();
            });
            
// 			var floginacct = $("#floginacct");
			var fusername = $("#fusername");
			var femail = $("#femail");
            
//             $("#floginacct").keyup(function(){
//             	doLoginacct();
//             });
            
            $("#femail").keyup(function(){
            	doEmail();
            });       

            $("#fusername").keyup(function(){
             	doUsername();
            });

           	$("#updateBtn").click(function(){
            	if(doEmail() && doUsername()){
            		doUpdate();
            	}
            });
            
            //修改 
           function doUpdate(){
           	   var loadingIndex = -1;
	           $.ajax({ 
		           	type : "POST",
		           	data : {
// 		           		"loginacct" : floginacct.val(),
		           		"username" : fusername.val(),
		           		"email" : femail.val(),
		           		"id" : "${user.id}"
		           	},		
		           	url : "${APP_PATH}/user/doUpdate.do",
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
		           		layer.msg("修改失败!", {time:1000, icon:5, shift:7});
		           	}
	           		
	            });
            } 
            
            
           var Bln = false;
//             //账号 
// 			function doLoginacct(){
				
// 				$.ajax({
//             		type : "POST", 
//             		data : {
//             			"loginacct" : floginacct.val()
//             		},
//             		url : "${APP_PATH}/user/doUser.do",
//             		beforeSend : function(){
//             			//一般用于校验 
//             			//账号             	
//                     	if(floginacct.val() == ""){
//                     		//layer.msg("用户账号不能为空!", {time:1000, icon:5, shift:7});
//                     		//$("#pLoginacct").html("用户账号不能为空!");
//                     		$("#pLoginacct_notnull").show();
//                     		$("#pLoginacct_doLoginacct").hide();
//                     		$("#pLoginacct_6").hide();

//                     		return false;
//                     	}else if(floginacct.val().length < 6){
//                     		//layer.msg("用户账号不能小于6位数!", {time:1000, icon:5, shift:7});
//                     		//$("#pLoginacct").html("用户账号不能小于6位数!");
//                     		$("#pLoginacct_6").show();
//                     		$("#pLoginacct_notnull").hide();
//                     		$("#pLoginacct_doLoginacct").hide();

//                    			return false;
//                     	}else{
// //                     		$("#pLoginacct").html("showAddBtn");
//         					$("#pLoginacct_notnull").hide();
//                     		$("#pLoginacct_6").hide();
//                    			return true;
//                     	}
 
//             		},
//             		success : function(result){
//             			if(result.success){
//             				//layer.msg("账号已存在!", {time:1000, icon:5, shift:7});
//             				//$("#pLoginacct").html("账号已存在!");
//             				$("#pLoginacct_doLoginacct").show();
//             				bln = false;
//             			}else{
// 							if(result.message != null){
// 								layer.msg(result.message, {time:1000, icon:5, shift:7});
// 							}
// 							$("#pLoginacct_doLoginacct").hide();
// 							bln = true;
//             			}
//             			return bln;
//             		},
//             		error : function(){
//             			layer.msg("校验账号失败!", {time:1000, icon:5, shift:7});
//             			bln = false;
//             		} 
//             	});
// 				return bln;
// 			}	
			
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
        	
            //重置
            $("#resetBtn").click(function(){
            	 $("#updateForm")[0].reset();//将JQuery对象转换为dom对象;JQuery对象中没有reset()函数
            	 doUsername();
            	 doEmail();
            });
        </script>
  </body>
</html>
