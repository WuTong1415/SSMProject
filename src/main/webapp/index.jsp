<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>心情树洞</title>
</head>
<body>
<h2> 欢迎</h2>
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
    <input type="button" value="注册" onclick="window.location='register.jsp'">
</div>
</body>
</html>
