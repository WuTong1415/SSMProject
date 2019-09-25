<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h2> 发布说说</h2>
<div>
    <form method="post" action="/addnewmood">
    <table>
        <tr>
            <td style="display: none"><input id="id" name="id" value="${userid}"></td>
        </tr>
        <tr>
            <td><input id="content" name="content" type="text" style="height: 60px"></td>
        </tr>
        <tr>
            <td><input type="submit" value="发布"></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
