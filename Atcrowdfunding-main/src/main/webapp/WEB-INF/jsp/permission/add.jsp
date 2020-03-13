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
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 许可维护</a></div>
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
				  <li><a href="${APP_PATH }/permission/index.htm">权限菜单列表</a></li>
				  <li class="active">新增</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="addForm" role="form">
				  <div class="form-group">
					<label for="fname">许可名称</label>
					<input type="text" class="form-control" maxlength="20" id="fname" placeholder="请输入许可名称"  onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" autocomplete="off">
				 	<p id="pName_notnull" style="display:none"  class="help-block label label-warning">许可名不能为空!</p>
				 	<p id="pName_doPermission" style="display:none"  class="help-block label label-warning">许可名已存在!</p>
				  </div>
				  <div class="form-group">
					<label for="furl">许可RUL</label>
					<input type="text" class="form-control" maxlength="20" id="furl" placeholder="请输入URL" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" style="ime-mode:disabled" autocomplete="off">
				 	<p id="pUrl_notnull" style="display:none"  class="help-block label label-warning">RUL不能为空!</p>
				 	<p id="pUrl_doPermission" style="display:none"  class="help-block label label-warning">URL已存在!</p>
				  </div>
<!-- 				  <select name="" id=""> -->
<!-- 				  	<option></option> -->
				  	
<!-- 				  </select> -->

				  <button id="addBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
				  <button id=resetBtn  type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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
            $("#resetBtn").click(function(){
            	 $("#addForm")[0].reset();//将JQuery对象转换为dom对象;JQuery对象中没有reset()函数
            });
            
			var fname = $("#fname");
			var furl = $("#furl"); 
            
            $("#fname").keyup(function(){
            	doName();
            });
            
            $("#furl").keyup(function(){
            	doUrl();
            });       

           	$("#addBtn").click(function(){
            	if(doName() && doUrl()){
            		doAdd();
            	}
            });
            
            //保存
           function doAdd(){
           var loadingIndex = -1;
	           $.ajax({ 
		           	type : "POST",
		           	data : {
		           		"name" : fname.val(),
		           		"url" : furl.val(),
		           		"pid" : "${param.id}"
		           	},		
		           	url : "${APP_PATH}/permission/doAdd.do",
		           	beforeSend : function() {
		           		loadingIndex = layer.msg('处理中', {icon: 16});
		           		//一般做表单数据验证 
		           		return true;	
		           	},
		           	success : function(result){
		           		layer.close(loadingIndex);	
		           		if(result.success){
		           			window.location.href="${APP_PATH}/permission/toIndex.htm";
		           		}else{
		           			layer.msg(result.message, {time:1000, icon:5, shift:6});
		           		}
		           		
		           	},
		           	error : function(){
		           		layer.msg("保存失败!", {time:1000, icon:5, shift:7});
		           	}
	           		
	            });
            } 
            
            
            var bln = false;
            //许可名
			function doName(){
				
				$.ajax({
            		type : "POST", 
            		data : {
            			"name" : fname.val()
            		},
            		url : "${APP_PATH}/permission/doPermission.do",
            		beforeSend : function(){
            			//一般用于校验 
            			//许可名         	
                    	if(fname.val() == ""){
                    		$("#pName_notnull").show();
                    		$("#pName_doPermission").hide();

                    		return false;
                    	}else{
                    		$("#pName_notnull").hide();
                    		$("#pName_doPermission").hide();
                   			return true;
                    	}
 
            		},
            		success : function(result){
            			if(result.success){
            				$("#pName_doPermission").show();
            				bln = false;
            			}else{
							if(result.message != null){
								layer.msg(result.message, {time:1000, icon:5, shift:7});
							}
							$("#pName_doPermission").hide();
							bln = true;
            			}
            			return bln;
            		},
            		error : function(){
            			layer.msg("校验许可名失败!", {time:1000, icon:5, shift:7});
            			bln = false;
            		} 
            	});
				return bln;
			}	
			
			//URL
			function doUrl(){
				
				$.ajax({
            		type : "POST", 
            		data : {
            			"url" : furl.val()
            		},
            		url : "${APP_PATH}/permission/doPermission.do",
            		beforeSend : function(){
            			//一般用于校验 
            			//URL            	
                    	if(furl.val() == ""){
                    		$("#pUrl_notnull").show();
                    		$("#pUrl_doPermission").hide();

                    		return false;
                    	}else{
                    		$("#pUrl_notnull").hide();
                    		$("#pUrl_doPermission").hide();
                   			return true;
                    	}
 
            		},
            		success : function(result){
            			if(result.success){
                    		$("#pUrl_doPermission").show();
            				bln = false;
            			}else{
							if(result.message != null){
								layer.msg(result.message, {time:1000, icon:5, shift:7});
							}
							$("#pUrl_doPermission").hide();
							bln = true;
            			}
            			return bln;
            		},
            		error : function(){
            			layer.msg("校验URL失败!", {time:1000, icon:5, shift:7});
            			bln = false;
            		} 
            	});
				return bln;
			}	


            
        </script>
  </body>
</html>
    