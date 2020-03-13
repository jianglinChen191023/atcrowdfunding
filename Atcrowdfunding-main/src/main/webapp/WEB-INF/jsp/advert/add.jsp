<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/main.css">
<link rel="stylesheet" href="${APP_PATH }/css/doc.min.css">
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}
</style>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="user.html">众筹平台
						- 用户维护</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<%@ include file="/WEB-INF/jsp/common/top.jsp"%>
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
					<ul style="padding-left: 0px;" class="list-group">
						<!-- 树节点 -->
						<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
					</ul>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="${APP_PATH }/main.htm">首页</a></li>
					<li><a href="${APP_PATH }/advert/index.htm">数据列表</a></li>
					<li class="active">添加</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						表单数据
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<form id="addForm" action="" method="post"
							enctype="multipart/form-data">

							<div class="form-group">
								<label for="fname"></label> <input type="text"
									class="form-control" maxlength="20" id="name" name="name"
									placeholder="请输入广告名称"
									onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"
									autocomplete="off">
								<!-- 				 	<p id="pName_doName" style="display:none"  class="help-block label label-warning">广告名称已存在!</p> -->
								<!-- 				 	<p id="pName_notNull" style="display:none"  class="help-block label label-warning">广告名称不能为空!</p> -->
							</div>

							<div class="form-group">
								<label for="furl"></label> <input type="text"
									class="form-control" maxlength="20" id="url" name="url"
									placeholder="请输入广告地址"
									onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"
									style="ime-mode: disabled" autocomplete="off">
							</div>

							<div class="form-group">
								<label for="advpic"></label> <input type="file"
									class="form-control" maxlength="20" name="advpic" id="advpic"
									placeholder="请输入广告图片">
							</div>

							<button id="saveBtn" type="button" class="btn btn-success">
								<i class="glyphicon glyphicon-edit"></i> 添加
							</button>
							<button id="resetBtn" type="button" class="btn btn-danger">
								<i class="glyphicon glyphicon-refresh"></i> 重置
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
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
	<script src="${APP_PATH }/jquery/jquery-form/jquery-form.min.js"></script>

	<script type="text/javascript">
		$(function() {
			$(".list-group-item").click(function() {
				if ($(this).find("ul")) {
					$(this).toggleClass("tree-closed");
					if ($(this).hasClass("tree-closed")) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});
			//doName();
		});

		//var fname = $("#fname");

		$("#saveBtn").click(function() {
			doAdd();
			//             	$("#addForm").attr("action","${APP_PATH}/advert/doAdd.do");
			//             	$("#addForm").submit();
		});

		//添加 
		function doAdd() {
			var loadingIndex = -1;
			var options = {
				url : "${APP_PATH}/advert/doAdd.do",
				beforeSubmit : function() {
					loadingIndex = layer.msg('数据正在上传中', {
						icon : 6
					});
					return true;
				},
				success : function(result) {
					layer.close(loadingIndex);
					if (result.success) {
						layer.msg("广告数据上传成功", {
							time : 1000,
							icon : 6,
						});
						window.location.href = "${APP_PATH}/advert/index.htm";
					} else {
						layer.msg(result.message, {
							time : 1000,
							icon : 6,
							shift : 6
						});
					}
				},
				error : function() {
					layer.msg("上传失败!", {
						time : 1000,
						icon : 5,
						shift : 6
					});
				}
			};
			$("#addForm").ajaxSubmit(options); //异步提交 
			return;
		}

		/*
		$("#fname").keyup(function(){
		    doName();
		});
		
		var bln = false;
		//广告名称 
		function doName(){
			
			$.ajax({
				type : "POST", 
				data : {
					"name" : fname.val()
				},
				url : "${APP_PATH}/advert/doAdvert.do",
				beforeSend : function(){
					//一般用于校验 
					//广告名称          	
		        	if(fname.val() == ""){
		        		$("#pName_notnull").show();

		        		return false;
		        	}else{
						$("#pName_notnull").hide();
		        		$("#pName_doName").hide();
		       			return true;
		        	}

				},
				success : function(result){
					if(result.success){

						$("#pName_doName").show();
						bln = false;
					}else{
						if(result.message != null){
							layer.msg(result.message, {time:1000, icon:5, shift:7});
						}
						$("#pName_doName").hide();
						bln = true;
					}
					return bln;
				},
				error : function(){
					layer.msg("校验名称失败!", {time:1000, icon:5, shift:7});
					bln = false;
				} 
			});
			return bln;
		}	
		 */
		//重置
		$("#resetBtn").click(function() {
			$("#addForm")[0].reset();//将JQuery对象转换为dom对象;JQuery对象中没有reset()函数
			// doName();
		});
	</script>
</body>
</html>
