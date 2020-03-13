<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-nav">
	<li class="dropdown"><a href="#" class="dropdown-toggle"
		data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i>
			${sessionScope.loginMember.username }<span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu">
			<li><a href="member.html"><i
					class="glyphicon glyphicon-scale"></i> 会员中心</a></li>
			<li><a href="#"><i class="glyphicon glyphicon-comment"></i>
					消息</a></li>
			<li class="divider"></li>
			<li><a href="${APP_PATH }/login.htm?closeCookieToLogin=1">切换账号</a></li> 
			<li><a href="${APP_PATH }/logout.do"><i
					class="glyphicon glyphicon-off"></i> 退出系统</a></li>
		</ul>
		</li>
</ul>