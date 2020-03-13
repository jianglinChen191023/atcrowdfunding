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
			<li role="presentation" class="active"><a href="#"><span
					class="badge">1</span> 基本信息</a></li>
			<li role="presentation"><a href="#"><span class="badge">2</span>
					资质文件上传</a></li>
			<li role="presentation"><a href="#"><span class="badge">3</span>
					邮箱确认</a></li>
			<li role="presentation"><a href="#"><span class="badge">4</span>
					申请确认</a></li>
		</ul>

		<form role="form" style="margin-top: 20px;">
			<div class="form-group">
				<label for="realname">真实姓名</label> <input type="text"
					class="form-control" id="realname" placeholder="请输入真实名称" autocomplete="off">
				<p id="prealname" style="display: none"
					class="help-block label label-warning">真实姓名不能为空!</p>
			</div>
			<div class="form-group">
				<label for="cardnum">身份证号码</label> <input type="text"
					class="form-control" id="cardnum" maxlength="18" placeholder="请输入身份证号码" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" style="ime-mode:disabled" autocomplete="off">
				<p id="pcardnum" style="display: none"
					class="help-block label label-warning">身份证号码不能为空!</p>
			</div>
			<div class="form-group">
				<label for="tel">电话号码</label> <input type="text"
					class="form-control" id="tel" placeholder="请输入电话号码" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" style="ime-mode:disabled" autocomplete="off">
				<p id="ptel" style="display: none"
					class="help-block label label-warning">身份证号码不能为空!</p>
			</div>
			<button type="button" onclick="window.location.href='accttype.htm'"
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
					<div class="copyRight">Copyright ?2017-2017 atguigu.com 版权所有
					</div>
				</div>

			</div>
		</div>
	</div>
	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>	
	<script>
		var realname = $("#realname");
		var cardnum = $("#cardnum");
		var tel = $("#tel");

		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		});

		$("#realname").keyup(function() {
			doRealname();
		});

		$("#cardnum").keyup(function() {
			doCardnum();
		});

		$("#tel").keyup(function() {
			doTel();
		});
		
		$("#nextBtn").click(function(){
			if(doRealname() && doCardnum() && doTel()){
				next();
			}
		});
		
		function next() {
			$
					.ajax({
						type : "POST",
						url : "${APP_PATH}/member/updateBasicinfo.do",
						data : {
							realname : $("#realname").val(),
							cardnum : $("#cardnum").val(),
							tel : $("#tel").val()
						},
						success : function(result) {
							if (result.success) {
								window.location.href = "${APP_PATH}/member/uploadCert.htm";
							} else {
								layer.msg("基本信息更新失败", {
									time : 1000,
									icon : 5,
									shift : 6
								});
							}
						}
					});
		}

		var bln = false;
		//名称		realname
		function doRealname() {
			if (realname.val() == "") {
				$("#prealname").show();

				bln = false;
			} else {
				$("#prealname").hide();

				bln = true;
			}
			return bln;
		}

		//身份证		cardnum
		function doCardnum() {
			var bds = /(^\d{15}$)|(^\d{17}([0-9]|X|x)$)/;
			if (cardnum.val() == "") {
				$("#pcardnum").html("身份证号码不能为空!");
				$("#pcardnum").show();

				bln = false;
			} else if (!bds.test(cardnum.val())) {
				$("#pcardnum").html("身份证号码格式不正确!");
				$("#pcardnum").show();

				bln = false;
			} else {
				$("#pcardnum").hide();

				bln = true;
			}
			return bln;
		}

		//电话号码
		function doTel() {
			var bds = /^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9]))\d{8}$/;
			if (tel.val() == "") {
				$("#ptel").html("电话号码不能为空!");
				$("#ptel").show();

				bln = false;
			} else if (!bds.test(tel.val())) {
				$("#ptel").html("电话号码格式不正确!");
				$("#ptel").show(); 

				bln = false;
			} else {
				$("#ptel").hide();

				bln = true;
			}
			return bln;
		}
	</script>
</body>
</html>