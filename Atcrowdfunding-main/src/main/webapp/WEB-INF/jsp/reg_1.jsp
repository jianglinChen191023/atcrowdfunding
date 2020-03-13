<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keys" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/login.css">
<style>
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" href="index.html" style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">

		<form id="regForm" class="form-signin" role="form">
			<h2 class="form-signin-heading">
				<i class="glyphicon glyphicon-log-in"></i> 用户注册
			</h2>
			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" id="tel"
					placeholder="请输入电话号码" style="margin-top: 10px;"> <span
					class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" id="pwd"
					placeholder="请输入登录密码" maxlength="6" style="margin-top: 10px;">
				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>

			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" id="code"
					placeholder="请输入验证码" style="margin-top: 10px;"> <span
					class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
			</div>

			<div class="checkbox">
				<label> 忘记密码 </label> <label style="float: right"> <a
					href="toLogin.htm">我有账号</a>
				</label>
			</div>
			<a class="btn btn-lg btn-success btn-block" href="member.html">
				注册</a>
		</form>
	</div>
	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script>
		$("#tel").keyup(function() {
			telfct($("#tel"));
		});

		$(function() {
			telfct($("#tel"));
		});

		// 		$("#regForm").click(
		// 				function() {
		// 					if (loginacctfct($("#loginacct")) && pwdfct($("#pwd"))
		// 							&& telfct($("#tel")) && codefct($("#code"))) {
		// 						regfct();
		// 					}
		// 				});

		// 		//准备一个全局变量 boolean 
		// 		var bln = false;
		// 		var v_code = "";

		// 		//注册 
		// 		function regfct() {

		// 		};

		// 		function loginacctfct(loginacct) {
		// 			//对于表单数据而言不能用null进行判断,如果文本框什么都不输入,获取的值是""
		// 			if (loginacct.val() == "") {
		// 				//alert("用户账号不能为空,请重新输入!");
		// 				layer.msg("用户账号不能为空,请重新输入!", {
		// 					time : 1000,
		// 					icon : 5,
		// 					shift : 7
		// 				}, function() {
		// 					loginacct.focus();
		// 				});
		// 				return false;
		// 			}else if(loginacct.val() < 6){
		// 				layer.msg("用户账号不能小于6位数!", {
		// 					time : 1000,
		// 					icon : 5,
		// 					shift : 7
		// 				}, function() {
		// 					loginacct.focus();
		// 				});
		// 			}
		// 		};

		// 		function pwdfct(pwd) {
		// 			if (pwd.val() == "") {
		// 				layer.msg("用户密码不能为空,请重新输入!", {
		// 					time : 1000,
		// 					icon : 5,
		// 					shift : 7
		// 				}, function() {
		// 					pwd.focus();
		// 				});

		// 				return false;
		// 			}else if(pwd.val() < 6){
		// 				layer.msg("用户密码不能小于6位数!", {
		// 					time : 1000,
		// 					icon : 5,
		// 					shift : 7
		// 				}, function() {
		// 					pwd.focus();
		// 				});
		// 			}
		// 		};

		//电话号码
		function telfct(tel) {
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
		};

		// 		function codefct(code) {
		// 			if(telfct($("#tel"))){
		// 				//发送验证码 

		// 			}
		// 		};
	</script>


</body>

</html>