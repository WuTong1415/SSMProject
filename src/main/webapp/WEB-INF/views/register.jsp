<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>用户注册-心情树洞</title>
</head>
<body>
<h2> 注册新账户</h2>
<c:if test="${msg != null}">
    <h3>${msg}</h3>
</c:if>
<div>
    <form method="post" action="${pageContext.request.contextPath}/register">
        <table>
            <tr>
                <td>姓名：</td>
                <td><label><input id="姓名" name="name" type="text"></label></td>
            </tr>
            <tr>
                <td>账号：</td>
                <td><label><input id="account" name="account" type="text"></label></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><label><input id="password" name="password" type="password"></label></td>
            </tr>
            <tr>
                <td><input type="submit" value="注册"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
