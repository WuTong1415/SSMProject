<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>心情树洞</title>
</head>
<body style="margin-left: 40%">
<h2> 欢迎</h2>
<c:if test="${msg != null}">
    <h3>${msg}</h3>
</c:if>
<div>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <table>
            <tr>
                <td>账号：</td>
                <td><label><input id="account" name="account" type="text"></label></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><label><input id="password" name="password" type="password"></label></td>
            </tr>
            <tr>
                <td><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>
    <a href="/reg"><input type="button" value="注册"></a>
</div>
</body>
</html>
