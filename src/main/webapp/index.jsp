<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h2> 欢迎</h2>
<div>
    <form method="post" action="/login">
        <table>
            <tr>
                <td>账号：</td>
                <td><input id="account" name="account" type="text"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input id="password" name="password" type="password"></td>
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
