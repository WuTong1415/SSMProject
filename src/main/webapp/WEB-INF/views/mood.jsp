<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>树洞</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body STYLE="margin-left: auto">
<h1>
    ${user.name}欢迎您&nbsp&nbsp&nbsp<a href="${pageContext.request.contextPath}/index.jsp">退出登录</a>
</h1><br>
<a id="writeMood" href="${pageContext.request.contextPath}/writeMood?userId=${user.id}">发布动态</a>
<div id="moods" style="margin-left: auto">
    <b>动态列表:</b><br>
    <c:forEach items="${moods}" var="mood">
    ------------------------------------
    <br>
    <b>用户：</b><span id="account">${mood.userName}</span><br>
    <b>动态内容：</b><span id="content">${mood.content}</span><br>
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
                <a id="cancelPraise" href="${pageContext.request.contextPath}/cancelPraise?moodId=${mood.id}&friend=${user.id}">取消赞</a>
            </c:if>
            <c:if test="${!iscontain}">
                <a id="praiseForRedis" href="${pageContext.request.contextPath}/praiseByRedis?moodId=${mood.id}&friend=${user.id}">赞</a>
            </c:if>
        </div>
        <b>评论:</b>
        <div style="margin-left: 50px;width: 360px">
            <c:forEach items="${mood.commentList}" var="comment">
                <span style="width: 70px">${comment.friendName}:${comment.comment}</span><a style="float: right" href="${pageContext.request.contextPath}/deleteComment?commentId=${comment.id}&userId=${user.id}">删除评论</a>
                <br>
            </c:forEach>
        </div><br>
        <div id="test" style="display:none">
            <form method="post" action="${pageContext.request.contextPath}/addNewComment">
                <table>
                    <tr>
                        <td style="display: none"><label><input id="userId" name="userId" value="${user.id}"></label></td>
                    </tr>
                    <tr>
                        <td style="display: none"><label><input id="moodId" name="moodId" value="${mood.id}"></label></td>
                    </tr>
                    <tr>
                        <td><label><input id="comment" name="comment" type="text" style="height: 60px"></label></td>
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
        const tt = document.getElementById("test");
        tt.style.display = "block";
    }
</script>
</html>

