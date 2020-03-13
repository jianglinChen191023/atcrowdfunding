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
<link rel="stylesheet"
	href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
<style>
#footer {
	padding: 15px 0;
	background: #fff;
	border-top: 1px solid #ddd;
	text-align: center;
}
</style>
</head>
<body>
	<div class="navbar-wrapper">
		<div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand" href="index.html" style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse"
						style="float: right;">
						<%@ include file="/WEB-INF/jsp/common/memberTop.jsp"%>
					</div>
				</div>
			</nav>

		</div>
	</div>

	<div class="container theme-showcase" role="main">
		<div class="page-header">
			<h1>实名认证 - 申请</h1>
		</div>

		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation"><a href="#"><span class="badge">1</span>
					基本信息</a></li>
			<li role="presentation"><a href="#"><span class="badge">2</span>
					资质文件上传</a></li>
			<li role="presentation" class="active"><a href="#"><span
					class="badge">3</span> 邮箱确认</a></li>
			<li role="presentation"><a href="#"><span class="badge">4</span>
					申请确认</a></li>
		</ul>

		<form role="form" style="margin-top: 20px;">
			<div class="form-group">
				<label for="memberEmail">邮箱地址</label> <input type="text"
					class="form-control" id="memberEmail" value="${loginMember.email }"
					onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"
					placeholder="请输入用于接收验证码的邮箱地址">
				<p id="pemail" style="display: none"
					class="help-block label label-warning">请输入合法的邮箱地址, 格式为：
					xxxx@xxxx.com</p>
			</div>
			<button type="button"
				onclick="window.location.href='${APP_PATH }/member/uploadCert.htm'"
				class="btn btn-default">上一步</button>
			<button type="button" id="nextBtn" class="btn btn-success">下一步</button>
		</form>
		<hr>
	</div>
	<!-- /container -->
	<div class="container" style="margin-top: 20px;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div id="footer">
					<div class="footerNav">
						<a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a
							rel="nofollow" href="http://www.atguigu.com">服务条款</a> | <a
							rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a
							rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a
							rel="nofollow" href="http://www.atguigu.com">联系我们</a>
					</div>
					<div class="copyRight">Copyright ?2017-2017atguigu.com 版权所有</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>	
	<script>
		doEmail($("#memberEmail"), $("#pemail"));

		$("#memberEmail").keyup(function() {
			doEmail($("#memberEmail"), $("#pemail"));
		});

		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		});

		$("#nextBtn").click(
				function() {
					if (doEmail($("#memberEmail"), $("#pemail"))) {
						next("${APP_PATH}/member/startProcess.do",
								"${APP_PATH}/member/apply.htm");
					}
				});

		function next(controllerRequestMapping, href) {
			$.ajax({
				type : "POST",
				url : controllerRequestMapping,
				data : {
					"email" : $("#memberEmail").val()
				},
				success : function(result) {
					if (result.success) {
						window.location.href = href;
					} else {
						layer.msg("发送邮件失败!", {
							time : 1000,
							icon : 5,
							shift : 6
						});
					}
				}
			});
		}

		var bln = false;
		//邮箱
		function doEmail(femail, pemail) {
			var bds = /^[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]\.[a-zA-Z][a-zA-Z\.]*[a-zA-Z]$/;
			if (!bds.test(femail.val())) {
				pemail.html("请输入合法的邮箱地址, 格式为： xxxx@xxxx.com");
				pemail.show();

				bln = false;
			} else {
				if (femail.val().toLowerCase().substring(
						femail.val().lastIndexOf(".") + 1) != "com") {
					pemail.html("邮箱后缀必须为com!");
					pemail.show();

					bln = false;
				} else {
					pemail.hide();
					bln = true;
				}

			}
			return bln;
		}
	</script>
</body>
</html>