<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body STYLE="margin-left: auto">
<h1>
    ${user.name}欢迎您&nbsp&nbsp&nbsp<a href="/index.jsp">退出登录</a>
</h1><br>
<a id="writeMood" href="/writemood?userid=${user.id}">发布说说</a>
<div id="moods" style="margin-left: auto">
    <b>说说列表:</b><br>
    <c:forEach items="${moods}" var="mood">
    ------------------------------------
    <br>
    <b>用户：</b><span id="account">${mood.userName}</span><br>
    <b>说说内容：</b><span id="content">${mood.content}</span><br>
    <b>发布时间：</b><span><fmt:formatDate value="${mood.publishTime}" pattern="yyyy年MM月dd日HH点mm分ss秒"/><br>
        <b>点赞数：</b><span id="praise_num">${mood.praiseNum}</span><br>
        <b>点赞人: </b><span>
            <c:forEach items="${mood.praisenames}" var="praisename">
                ${praisename}
            </c:forEach>
            </span><br>
        <div style="margin-left: 350px">
            <input id="test1" type="button" value="评论" onclick="che();"/>
            <c:set var="iscontain" value="false"/>
            <c:forEach items="${mood.praisenames}" var="praiseaccount">
                <c:if test="${praiseaccount eq user.account}">
                    <c:set var="iscontain" value="true"/>
                </c:if>
            </c:forEach>
            <c:if test="${iscontain}">
                <a id="praiseforRedis" href="/cancelpraise?moodid=${mood.id}&friend=${user.id}">取消赞</a>
            </c:if>
            <c:if test="${!iscontain}">
                <a id="cancelpraise" href="/praisebyRedis?moodid=${mood.id}&friend=${user.id}">赞</a>
            </c:if>
        </div>
        <b>评论:</b>
        <div style="margin-left: 50px;width: 360px">
            <c:forEach items="${mood.commentList}" var="comment">
                <span style="width: 70px">${comment.friendName}:${comment.comment}</span><a style="float: right" href="/deletecomment?commentid=${comment.id}&userid=${user.id}">删除评论</a>
                <br>
            </c:forEach>
        </div><br>
        <div id="test" style="display:none">
            <form method="post" action="/addnewcomment">
                <table>
                    <tr>
                        <td style="display: none"><input id="userid" name="userid" value="${user.id}"></td>
                    </tr>
                    <tr>
                        <td style="display: none"><input id="moodid" name="moodid" value="${mood.id}"></td>
                    </tr>
                    <tr>
                        <td><input id="comment" name="comment" type="text" style="height: 60px"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="评论"></td>
                    </tr>
                </table>
            </form>
        </div>
    </c:forEach>

</div>
</body>
<script type="text/javascript">
    function che() {
        tt = document.getElementById("test");
        tt.style.display = "block";
    }
</script>
</html>

