<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--errorPage="/error.jsp"--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
</head>
<body>
<%-- 
index

http://www.atcrowdfunding.com/

<a href="htt	p://www.atcrowdfunding.com/test.do">test</a>绝对路径

<a href="${pageContext.request.contextPath }/test.do">test</a>相对于当前项目的根来找,一种特殊的相对路径

<a href="/testselect.do">test2</a>

<a href="/testlist.do">test3</a>
${APP_PATH }
<a href="${APP_PATH }/testlist.do">test3</a>
--%>
<%--<jsp:forward page="${APP_PATH }/index.htm"></jsp:forward>--%>

<a href="${APP_PATH}/index.htm">测试</a>
</body>
</html>