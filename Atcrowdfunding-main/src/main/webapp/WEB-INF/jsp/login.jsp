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
#toReg {
	margin-top: -20px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" href="/index.htm" style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">

		<form id="loginForm" action="${APP_PATH }/doLogin.do" method="POST"
			class="form-signin" role="form">
			${exception.message }
			<h2 class="form-signin-heading">
				<i class="glyphicon glyphicon-log-in"></i> 用户登录
			</h2>
			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" id="floginacct"
					name="loginacct" value="superadmin" placeholder="请输入登录账号"
					onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"
					onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'') "
					style="ime-mode: disabled"> <span
					class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="password" class="form-control"
					onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"
					id="fuserpswd" name="userpswd" value="123" placeholder="请输入登录密码"
					style="margin-top: 10px;"> <span
					class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<select class="form-control" id="ftype" name="type">
					<option value="member" selected="selected">会员</option>
					<option value="user">管理</option>
				</select>
			</div>
			<div class="checkbox">
				<label> <input id="checkbox" type="checkbox"
					value="remember-me"> 自动登录
				</label> <br>
				<!-- <label>
            忘记密码
          </label> -->
				<label id="toReg" style="float: right"> <a href="${APP_PATH }/reg.htm">我要注册</a></label>
			</div>

			<a class="btn btn-lg btn-success btn-block" onclick="dologin()">
				登录</a>
		</form>
	</div>
	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>

	<script>
		
		
		function dologin() {
			var floginacct = $("#floginacct");
			var fuserpswd = $("#fuserpswd");
			var ftype = $("#ftype");

			//对于表单数据而言不能用null进行判断,如果文本框什么都不输入,获取的值是""
			if (floginacct.val() == "") {
				//alert("用户账号不能为空,请重新输入!");
				layer.msg("用户账号不能为空,请重新输入!", {
					time : 1000,
					icon : 5,
					shift : 7
				}, function() {
					floginacct.focus();
				});
				return false;
			}
			if (fuserpswd.val() == "") {
				alert("密码不能为空,请重新输入!");
				fuserpswd.focus();
				return false;
			}

			var loadingIndex = -1;
			var flag = $("#checkbox")[0].checked; //是否选中[记住我 ]
			$
					.ajax({
						type : "POST",
						data : {
							"loginacct" : floginacct.val(),
							"userpswd" : fuserpswd.val(),
							"type" : ftype.val(),
							"rememberme" : flag ? "1" : "0"
						},
						url : "${APP_PATH}/doLogin.do",
						beforeSend : function() {
							//一般做表单数据验证 
							loadingIndex = layer.msg('处理中', {
								icon : 16
							});
							return true;
						},
						success : function(result) { // {"success":true}	 或  {"success":false,"message":"登录失败!"}
							layer.close(loadingIndex);
							if (result.success) {
								if ("member" == result.data) {
									if (result.message != null) {
										layer.msg(result.message, {
											time : 1000,
											icon : 5,
											shift : 7
										});
									} else {
										//跳转会员页面
										window.location.href = "${APP_PATH }/member.htm";
									}
								} else if ("user" == result.data) {
									if (result.message != null) {
										layer.msg(result.message, {
											time : 1000,
											icon : 5,
											shift : 7
										});
									} else {
										//跳转管理页面
										window.location.href = "${APP_PATH }/main.htm";
									}
								} else {
									layer.msg("登录类型不合法!", {
										time : 1000,
										icon : 5,
										shift : 7
									});
								}

							} else {
								layer.msg(result.message, {
									time : 1000,
									icon : 5,
									shift : 7
								});
							}
						},
						error : function() {
							layer.msg("登录失败1!", {
								time : 1000,
								icon : 5,
								shift : 7
							});
						}
					});

			/* $("#loginForm").submit();
			 var type = $(":selected").val();
			if ( type == "user" ) {
			    window.location.href = "main.html";
			} else {
			    window.location.href = "index.html";
			} */
		}
	</script>
</body>
</html>