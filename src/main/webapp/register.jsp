<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h2> 注册新账户</h2>
<div>
    <form method="post" action="/register">
        <table>
            <tr>
                <td>姓名：</td>
                <td><input id="姓名" name="name" type="name"></td>
            </tr>
            <tr>
                <td>账号：</td>
                <td><input id="account" name="account" type="text"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input id="password" name="password" type="password"></td>
            </tr>
            <tr>
                <td><input type="submit" value="注册"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
