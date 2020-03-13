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
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}

table tbody tr:nth-child(odd) {
	background: #F4F4F4;
}

table tbody td:nth-child(even) {
	color: #C00;
}

input[type=checkbox] {
	width: 18px;
	height: 18px;
}
</style>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台
						- 分类管理</a>
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
					<!-- 树节点 -->
					<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据矩阵
						</h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th>名称</th>
										<th>商业公司</th>
										<th>个体工商户</th>
										<th>个人经营</th>
										<th>政府及非营利组织</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${allCert }" var="cert">
										<tr>
											<td>${cert.name }</td>
											<td><input type="checkbox" certid="${cert.id }"
												accttype="0"></td>
											<td><input type="checkbox" certid="${cert.id }"
												accttype="1"></td>
											<td><input type="checkbox" certid="${cert.id }"
												accttype="2"></td>
											<td><input type="checkbox" certid="${cert.id }"
												accttype="3"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
	
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
			showMenu("${APP_PATH}");

			var certAccttypeList = "${certAccttypeList}";

			/*
			$.each(certAccttypeList, function(i, n) {
				$(":checkbox[certid='"+n.certid+"'][accttype='"+n.accttype+"']")[0].checked = true;
			});
			 */

			<c:forEach items="${certAccttypeList }" var="cert">
			$(":checkbox[certid='${cert.certid }'][accttype='${cert.accttype }']")[0].checked = true;
			</c:forEach>
			/*
			$(":checkbox[certid='6'][accttype='2']")[0].checked = true;
			
			$(":checkbox[certid='7'][accttype='2']")[0].checked = true;
			 */
		});

		$(":checkbox").click(function() {
			var flg = this.checked;
			//通过this.certid能否获取自定义的属性值
			var certid = $(this).attr("certid");
			var accttype = $(this).attr("accttype");

			if (flg) {
				addordlt(certid, accttype, true);
			} else {
				addordlt(certid, accttype, false);
			}
		});

		//勾选或者取消勾选 
		function addordlt(certid, accttype, addordlt) {
			var loadingIndex = -1;
			$.ajax({
				type : "POST",
				data : {
					"certid" : certid,
					"accttype" : accttype
				},
				url : addordlt?"${APP_PATH}/certtype/inertAcctTypeCert.do":"${APP_PATH}/certtype/deleteAcctTypeCert.do",
				success : function(result) {
					layer.close(loadingIndex);
					if (result.success) {
						
					} else {
						if(addordlt){
							layer.msg("勾选失败!", {
								time : 1000,
								icon : 5,
								shift : 7
							});
						}else{
							layer.msg("取消勾选失败!", {
								time : 1000,
								icon : 5,
								shift : 7
							});
						}
						
					}

				},
				error : function() {
					layer.msg("异常!", {
						time : 1000,
						icon : 5,
						shift : 7
					});
				}

			});
		}
	</script>
	<script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
</body>
</html>
