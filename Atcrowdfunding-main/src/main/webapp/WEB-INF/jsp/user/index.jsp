<%@page import="com.vaadin.terminal.gwt.client.ui.Table"%>
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
<link rel="stylesheet" href="${APP_PATH }/css/pagination.css">

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
</style>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#"> 众筹平台
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
					<!-- 树节点 -->
					<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>

				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">按账号查询</div>
									<input id="queryText" class="form-control has-success"
										type="text" placeholder="请输入查询条件">
								</div>
							</div>
							<button id="queryBtn" type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button type="button" id="deleteBatchBtn" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button type="button" class="btn btn-primary"
							style="float: right;"
							onclick="window.location.href='${APP_PATH}/user/toAdd.htm'">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input id="allCheckbox" type="checkbox"></th>
										<th>账号</th>
										<th>名称</th>
										<th>邮箱地址</th>
										<th width="110">操作</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<div id="Pagination" class="pagination">
												<!-- 这里显示分页 -->
											</div>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/script/docs.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script type="text/javascript"
		src="${APP_PATH }/jquery/pagination/jquery.pagination.js"></script>

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
			queryPageUser(0);
			showMenu("${APP_PATH}");
		});

		$("tbody .btn-success").click(function() {
			window.location.href = "assignRole.htm";
		});
		$("tbody .btn-primary").click(function() {
			window.location.href = "edit.htm";
		});

		function pageChange(pageno) {
			window.location.href = "${APP_PATH}/user/index.do?pageno=" + pageno;
		}

		var condition = false;
		var jsonObj = {
			"pageno" : 1,
			"pagesize" : 10
		};

		//分页查询 
		var loadingIndex = -1;
		function queryPageUser(pageIndex) {
			$("#allCheckbox").attr("checked", false);
			jsonObj.pageno = pageIndex + 1;
			$
					.ajax({
						type : "POST",
						data : jsonObj,
						dataType : "json",
						url : "${APP_PATH}/user/doIndex.do",
						beforeSend : function() {
							loadingIndex = layer.load(2, {
								time : 10 * 1000
							});
							return true;
						},
						success : function(result) {
							layer.close(loadingIndex);
							if (result.success) {
								var page = result.page;
								var data = page.datas;

								var content = '';

								$
										.each(
												data,
												function(i, n) {
													content += '<tr>';
													content += '<td>' + (i + 1)
															+ '</td>';
													content += '<td><input type="checkbox" id="'+n.id+'"></td>';
													content += ' <td>'
															+ n.loginacct
															+ '</td>';
													content += '<td>'
															+ n.username
															+ '</td>';
													content += '<td>' + n.email
															+ '</td>';
													content += '<td>';
													content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/user/assignRole.htm?id='
															+ (n.id)
															+ '\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
													content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/user/toUpdate.htm?id='
															+ (n.id)
															+ '\'" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
													content += '	    <button type="button" onclick="deleteUser('
															+ n.id
															+ ',\''
															+ n.loginacct
															+ '\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
													content += '</td>';
													content += '</tr>';
												});

								$("tbody").html(content);

								//创建分页 
								//此demo通过Ajax加载分页元素
								$("#Pagination").pagination(page.totalsize, {
									num_edge_entries : 2, //边缘页数
									num_display_entries : 4, //主体页数
									callback : queryPageUser,//查询当前页的数据 
									items_per_page : page.pagesize, //每页显示?项 
									current_page : (page.pageno - 1),//当前页,索引从0开始 
									prev_text : "上一页",
									next_text : "下一页"
								});

							} else {
								layer.msg(result.message, {
									time : 1000,
									icon : 5,
									shift : 6
								});
							}
						},
						error : function() {
							layer.msg("加载数据失败!", {
								time : 1000,
								icon : 5,
								shift : 6
							});
						}

					});

		}

		//删除 
		function deleteUser(id, loginacct) {

			layer.confirm("确认要删除这个[" + loginacct + "]用户吗?", {
				icon : 3,
				title : '提示'
			}, function(cindex) {
				$.ajax({
					type : "POST",
					data : {
						"id" : id
					},
					url : "${APP_PATH}/user/doDelete.do",
					beforeSend : function() {

						return true;
					},
					success : function(result) {
						if (result.success) {
							layer.msg("删除成功!", {
								time : 1000,
								icon : 6,
								shift : 6
							});
							queryPageUser(1);
						} else {
							layer.msg("删除失败!", {
								time : 1000,
								icon : 5,
								shift : 6
							});
						}
					},
					error : function() {
						layer.msg("删除异常!", {
							time : 1000,
							icon : 5,
							shift : 6
						});
					}
				});
			}, function(cindex) {
				layer.close(cindex);
			});

		}

		//模糊查询 
		$("#queryBtn").click(function() {
			var queryText = $("#queryText").val();
			jsonObj.queryText = queryText;
			queryPageUser(0);
		});

		//复选框 : 全选 
		$("#allCheckbox").click(
				function() {
					var checkedStatus = this.checked;
					//alert(checkedStatus);
					$("tbody tr td input[type='checkbox']").prop("checked",
							checkedStatus);
					//alert(checkedBoxAll.length);

				});

		//批量删除 
		$("#deleteBatchBtn").click(function() {
			var selectCheckbox = $("tbody tr td input:checked");

			if (selectCheckbox.length == 0) {
				layer.msg("至少选择一个用户进行删除,请选择用户!", {
					time : 1000,
					icon : 5,
					shift : 6
				});
				return false;
			}

			//             	var idStr = "";

			// 				$.each(selectCheckbox,function(i,n){
			// 					if(i != 0){
			// 						idStr += "&";
			// 					} 
			// 					idStr +="id="+n.id;

			// 				});

			var jsonObject = {};
			$.each(selectCheckbox, function(i, n) {
				jsonObject["datas[" + i + "].id"] = n.id;

			});

			var index = -1;
			layer.confirm("确认要删除这些用户吗?", {
				icon : 3,
				title : '提示'
			}, function(cindex) {
				layer.close(cindex);
				$.ajax({
					type : "POST",
					//     	            	data : idStr,	//url?id=1&id=2&id=3 
					data : jsonObject,
					url : "${APP_PATH}/user/doDeleteBatch.do",
					beforeSend : function() {
						index = layer.load(2, {
							time : 10 * 1000
						});

						return true;
					},
					success : function(result) {
						layer.close(index);
						if (result.success) {
							layer.msg("删除成功!", {
								time : 1000,
								icon : 6,
								shift : 6
							});
							queryPageUser(1);
						} else {
							layer.msg("删除失败!", {
								time : 1000,
								icon : 5,
								shift : 6
							});
						}
					},
					error : function() {
						layer.msg("删除异常!", {
							time : 1000,
							icon : 5,
							shift : 6
						});
					}
				});
			}, function(cindex) {
				layer.close(cindex);
			});

		});
	</script>
	<script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
</body>
</html>
