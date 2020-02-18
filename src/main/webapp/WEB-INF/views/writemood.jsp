<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>发布心情</title>
</head>
<body>
<h2> 发布心情</h2>
<div>
    <form method="post" action="${pageContext.request.contextPath}/addNewMood">
    <table>
        <tr>
            <td style="display: none"><label><input id="id" name="id" value="${userId}"></label></td>
        </tr>
        <tr>
            <td><label><input id="content" name="content" type="text" style="height: 60px"></label></td>
        </tr>
        <tr>
            <td><input type="submit" value="发布"></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
