<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- taglib uri prefix-->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户注册</title>

<meta charset="utf-8">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="js/bootstrap.min.js"></script>
<style>
* {
	margin: 0px;
	padding: 0px;
}

font {
	color: red;
	font-size: 12px;
}

#div-div1 {
	/* text-align: center; /*��div�ڲ����־���*/ */
	/* background-color: #fff;  */
	border-radius: 20px;
	width: 380px;
	height: 380px;
	margin: auto;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	/* border:1px white hidden; */
	/* background-color: red;  */
}

#sub {
	text-align: center; /*��div�ڲ����־���*/
	width: 300px;
	height: 50px;
	margin-top: 10px;
	margin-left: 33px;
	/* cursor:pointer; */
	/* box-shadow: 1px 1px 4px #fff;	��Ӱ */
	outline: none; /*ȥ��ѡ�е���ʽ*/
	border-radius: 4px; /*Բ��*/
	border: none; /*�߿�*/
	text-indent: 6px; /*�ı�����*/
	color: #fff;
	font-size: 15px;
	cursor: pointer;
}

#sub:active {
	box-shadow: none;
	font-size: 12px;
}

#getcode:active {
	box-shadow: none;
	font-size: 12px;
	/* background-color:RGBA(60,181,112,1);  */
	/* background-color: RGBA(44,44,44,0.4); */
	/* background-color: RGBA(204,232,207,0.5); */
	background-color: RGBA(60, 181, 112, 0.5);
}

#getcode {
	/* background-color: RGBA(44,44,44,1); */
	background-color: RGBA(60, 181, 112, 0.5);
	width: 139px;
	height: 51px;
	position: absolute;
	top: 170;
	left: 194;
	/* background-color: RGB(256,256,256); */
	outline: none; /*ȥ��ѡ�е���ʽ*/
	border-radius: 4px; /*Բ��*/
	border: none; /*�߿�*/
	text-indent: 6px; /*�ı�����*/
	/* box-shadow: 1px 1px 4px #fff;	��Ӱ */
	cursor: pointer;
}
/* .allowed{
			cursor: not-allowed;
		}
		.pointer{
			cursor:pointer;
		} */
#div-div2 {
	width: 300px;
	height: 200px;
	/* border:1px white hidden	; */
	margin-top: 70px;
	margin-left: 32px;
	/* background-color: RGBA(0,0,0,0.1); */
	border-radius: 14px; /*Բ��*/
}

h2 {
	position: absolute;
	top: 0px;
	left: 30px;
}

#yyzh {
	font-size: 15px;
	color: RGB(179, 179, 179);
	position: absolute;
	top: 30px;
	left: 215px;
}

#a {
	font-size: 15px;
	color: RGB(0, 174, 102);
	position: absolute;
	top: 30px;
	left: 285px;
	text-decoration: none; /* ȥ��Ĭ���»���  */
}

#a:hover {
	text-decoration: underline;
}

input[type=text] {
	/* 			border: none; �߿�
			border-radius: 4px ;Բ�� */
	width: 300px;
	height: 50px;
	background-color: RGB(204, 232, 207);
}

input[type=password] {
	/* border: none; �߿� */
	/* 			border-radius: 4px ;Բ�� */
	width: 300px;
	height: 50px;
	background-color: RGB(204, 232, 207);
	border: 1px red hidden;
}

.font-font1 {
	position: absolute;
	top: 88px;
	left: 250px;
}

.font-font2 {
	position: absolute;
	top: 138px;
	left: 250px;
}

.font-font3 {
	position: absolute;
	top: 188px;
	left: 130px;
}

.font-font4 {
	position: absolute;
	top: 238px;
	left: 235px;
}

#code {
	width: 162px;
}

.hs {
	color: #c0c0c0;
}
</style>

</head>
<body>
	<div id="div-div1">

		<h2 class="form-signin-heading">注册账号</h2>
		<font id="yyzh">已有账号?</font> <a id="a" href="login.jsp">去登录</a>

		<form class="form-signin" action="" method="post" id="form1">
			<div id="div-div2">
				<input type="text" maxlength="12" class="form-control"
					name="users.name" placeholder="设置账号" required autocomplete="off" /><font
					class="font-font1"></font> <input type="text" maxlength="11"
					class="form-control" name="users.telephone" id="users.telephone"
					placeholder="请输入手机号码" required autocomplete="off" /><font
					class="font-font2"></font> <input type="text" name="code"
					class="form-control" id="code" maxlength="6" placeholder="请输入验证码"
					required autocomplete="off" /><font class="font-font3"></font> <input
					type="button" class="form-control" value="获取验证码" id="getcode"
					disabled="disabled" /> <input type="password" class="form-control"
					name="users.password" maxlength="16" id="users.password"
					placeholder="设置密码" required autocomplete="off" /><font
					class="font-font4"></font>
			</div>

			<br />
			<input type="button" class="btn btn-lg btn-primary btn-block"
				name="sub" id="sub" value="注册" /> <br />
			<!-- disabled="disabled" -->
		</form>
	</div>





</body>
<!-- JS -->
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/reg.js"></script>
<script type="text/javascript">
	
</script>
<%-- <script type="text/javascript">
		$(document).ready ( function(){	
			//保存验证码
			var $code = null;
			/* BUG处理 */
			var $phonebug = null;
			
			//设置昵称
			function addName(){
				var name = $("[name=users.name]").val().replace(/\s+/g,"");
				/* alert(name);  */
				/*Ajax验证 账号是否存在*/

				$.getJSON("users_selectName",{"users.name":name},function(data){
					$(data).each(function(i,obj){
						
						if(obj==99){
							$("[name=users.name]").next().html("请设置昵称");
							$("#sub").attr("disabled","disabled");
						}
						else if(obj==2){
							$("[name=users.name]").next().html("√");
						}
						else if(obj==1){
							$("[name=users.name]").next().html("昵称已存在!");
							$("#sub").attr("disabled","disabled");
						}
						
					});
				});
				
			}
		
			$("[name=users.name]").blur(function(){
				addName();
				Sub();
			});
			
			$("[name=users.name]").keyup(function(){
				addName();
				Sub();
			});
			

			/* 手机号 */
			function yzphone(){
				var telephone = $("[name=users.telephone]").val();
				/* alert(name);  */
				/*Ajax验证 手机号是否存在*/
				
				var sjh = /^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9]))\d{8}$/;
				
				if(telephone==""){
					$("[name=users.telephone]").next().html("请输入手机号");
   					return;
				}
				else if(sjh.test(telephone)==false){
   					$("[name=users.telephone]").next().html("格式错误");
   					$("#getcode").attr("disabled","disabled");
   					return;
   				}
   				if($phonebug!=null && $phonebug!=telephone){
					$("[name=code]").next().html("");
					$("[name=code]").val("");
					//清空验证码
					$code = null;
					//锁定注册
					$("#sub").attr("disabled","disabled");
							
				}
   				
				$.getJSON("users_selectPhone",{"users.telephone":telephone},function(data){
					$(data).each(function(i,obj){
						
						if(obj==2){
							$("[name=users.telephone]").next().html("√");
							var getcode = $("#getcode").val();
							if(getcode=="获取验证码" || getcode=="重新获取验证码"){
								$("#getcode").attr("disabled","");
							}

						}
						else if(obj==1){
							$("[name=users.telephone]").next().html("手机号已存在!");
							$("#getcode").attr("disabled","disabled");
						}
						
					});
				});
			}
			
			$("[name=users.telephone]").blur(function(){
				 yzphone();
				 addName();
				 Sub();
			});
			
			$("[name=users.telephone]").keyup(function(){
				 yzphone();
				 Sub();
			});
			

			
			//获取验证码
		 	$("#getcode").click(function(){
		 	
		 		/*//获取当前时间
				var date = new Date();
				var sec = date.getSeconds();*/
				var code = $("[name=users.telephone]").val();

				$.getJSON("users_SelectReg",{"phone":code},function(data){
					$(data).each(function(i,obj){
						alert(obj);
						if(obj=="00141"){
							alert("一小时内发送给单个手机次数超过限制"+"[00141]");
						}else if(obj.length==5){
							alert("未知错误，请联系技术客服。["+obj+"]");
						}else{
							$code=obj;
							//bug处理
							$phonebug=code;
						}
						
						
					});
				});  
				
				$("#getcode").val("60s 后重新发送");
				$("#getcode").attr("disabled","disabled");
		 		setTimeout(sec,1000);
			}); 
			
			//60秒sec
			function sec(){
				var getcode = $("#getcode").val();
					if(parseInt(getcode)!=1){
						$("#getcode").val((parseInt(getcode)-1)+"s 后重新发送" );
						setTimeout(sec,1000);
						
					}else{
						$("#getcode").attr("disabled","");
						$("#getcode").val("重新获取验证码");
					}	
					
			}
			
			//请输入验证码
			function yzcode(){
				var code = $("[name=code]").val();
				if(isNaN(code)){
					$("[name=code]").next().html("格式错误");
				}
				else if(code.length!=6){
					$("[name=code]").next().html("");
					return;
				}
				else if(code==$code	){
					$("[name=code]").next().html("√");
				}
				else{
					$("[name=code]").next().html("验证码错误");
				}
			}
			
			
			$("[name=code]").blur(function(){
				yzcode();
				Sub();
			});
			
			//请输入验证码
			$("[name=code]").keyup(function(){
				yzcode();
				Sub();
			}); 
			
			
			
	
			
			//String.trim() trim()是去掉首尾空格 2、str.replace(" ", ""); 去掉所有空格,包括首尾、中间 复制代码
			//replace(/(^\s*)|(\s*$)/g, "")
			//replace(/\s+/g, "")
			//设置密码
			function addpwd(){
				var pwd = $("[name=users.password]").val().replace(/\s+/g,"");
				/* 正则表达式 */
				var zzbds = /[\u4e00-\u9fa5]/;
				if(zzbds.test(pwd)){
					$("[name=users.password]").next().html("密码不能有中文");
				}else
				/*alert(pwd);*/
				if(pwd.length==0){
					$("[name=users.password]").next().html("密码不能为空");
				}
				else if(pwd.length<6){
					$("[name=users.password]").next().html("密码不能小于6位数");
				}
				else{
					$("[name=users.password]").next().html("√");
				}
			}
			
			$("[name=users.password]").blur(function(){
				addpwd();
				addName();
				yzphone();
				Sub();
			});
			
			//设置密码
			$("[name=users.password]").keyup(function(){
				addpwd();
				Sub();
			});
			
			
			/* $("#sub").click(function(){
				if(name!="√"){
					 $("[name=users.name]").focus();
					 return;
				}
				else if(phone!="√"){
					$("[name=users.telephone]").focus();
					return;
				} 
				else if(code!="√"){
					$("[name=code]").focus();
					 return;
				}
				else if(password!="√"){
					$("[name=users.password]").focus();
					 return;
				} 
				$("sub").submit();
			}); */
			
		});
		
		//注册
		function Sub(){
			var name = $("[name=users.name]").next().html();
			var phone = $("[name=users.telephone]").next().html();
			var code = $("[name=code]").next().html();
			var password = $("[name=users.password]").next().html();
			if(name=="√" &&  phone=="√" &&  code=="√" &&  password=="√"  ){
				$("#sub").attr("disabled","");
			}else{
				$("#sub").attr("disabled","disabled");
			}

		}
				
			
		
	</script> --%>
</html>
