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
<body style="background-color: bisque">
<div STYLE="margin-left: 35%;width: 500px" >
    <h1>
        ${user.name}欢迎您&nbsp&nbsp&nbsp
        <a href="${pageContext.request.contextPath}/">
            <input type="button" value="退出登录">
        </a>
    </h1><br>
    <a id="writeMood" href="${pageContext.request.contextPath}/writeNewMood?userId=${user.id}">
        <input type="button" value="发布动态">
    </a>
    <div id="moods">
        <b>动态列表:</b><br>
        <%--迭代动态--%>
        <c:forEach items="${moods}" var="mood" varStatus="idxStatus">
            <div style="height: 15px;width: 500px;float: left">
                -------------------------------------------
            </div>
            <div style="width: 500px;height: auto">

                <br>
                <b>用户：</b><span id="account">${mood.userName}</span>
                <c:if test="${mood.userName eq user.name}">
                    <a style="margin-left: 270px"
                       href="${pageContext.request.contextPath}/deleteMood?moodId=${mood.id}&userId=${user.id}">
                        <input type="button" value="删除动态"></a>
                </c:if>

                <br>
                <div><b>动态内容：</b><span id="content">${mood.content}</span><br></div>
                <div>
                    <c:if test="${mood.img !=null}">
                        <img style="width: 300px" src="${pageContext.request.contextPath}/static/img/${mood.img}">
                    </c:if>
                </div>
                <b>发布时间：</b><span><fmt:formatDate value="${mood.publishTime}" pattern="yyyy年MM月dd日HH点mm分ss秒"/><br>
                <b>点赞数：</b><span id="praiseNum">${mood.praiseNames.size()}</span><br>
                <b>点赞人: </b><span>
                    <c:forEach items="${mood.praiseNames}" var="praisename">
                        ${praisename}
                    </c:forEach>
                    </span><br>
                <div style="margin-left: 350px">
                    <input type="button" value="评论" onclick="doComment(${idxStatus.index});"/>
                    <c:set var="iscontain" value="false"/>
                    <c:forEach items="${mood.praiseNames}" var="friendname">
                        <c:if test="${user.name eq friendname}">
                            <c:set var="iscontain" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:if test="${iscontain}">
                        <a id="cancelPraise"
                           href="${pageContext.request.contextPath}/cancelPraise?moodId=${mood.id}&userId=${user.id}">
                            <input type="button" value="取消赞">

                        </a>
                    </c:if>
                    <c:if test="${!iscontain}">
                        <a id="praiseForRedis"
                           href="${pageContext.request.contextPath}/praiseByRedis?moodId=${mood.id}&userId=${user.id}">
                            <input type="button" value="赞">
                        </a>
                    </c:if>
                </div>
                <b>评论:</b>
                <div style="margin-left: 50px;width: 400px">
                    <c:forEach items="${mood.commentList}" var="comment">
                        <div style="width: 360px ;height: auto">
                                <div style="width: 45px; float: left">
                                    ${comment.friendName}:
                                </div>
                            <div style="width: 180px;float: left">
                                    ${comment.comment}
                            </div>

                        <c:if test="${comment.friendName eq user.name}">

                            <a style="float: right"
                               href="${pageContext.request.contextPath}/deleteComment?commentId=${comment.id}&userId=${user.id}">
                                <input type="button" value="删除评论">
                            </a>
                        </c:if>
                        </div>
                        <div style="width: 360px;height: 5px;float: left"></div>
                    </c:forEach>
                </div><br>
                <div id="test${idxStatus.index}" style="display:none">
                    <form method="get" action="${pageContext.request.contextPath}/addNewComment">
                        <table>
                            <tr>
                                <td style="display: none"><label><input id="userId" name="userId"
                                                                        value="${user.id}"></label></td>
                            </tr>
                            <tr>
                                <td style="display: none"><label><input id="moodId" name="moodId"
                                                                        value="${mood.id}"></label></td>
                            </tr>
                            <tr>
                                <td><label><input id="comment" name="comment" type="text" style="height: 60px"></label></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="评论">
                                    <input type="button" value="取消" onclick="cancelComment(${idxStatus.index})">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div style="height: 5px;width: 500px;float: left"></div>
        </c:forEach>

    </div>
</div>
</body>
<script type="text/javascript">
    function doComment(x) {
        const tt = document.getElementById("test" + x);
        tt.style.display = "block";
    }

    function cancelComment(x) {
        const tt = document.getElementById("test" + x);
        tt.style.display = "none";
    }

</script>
</html>

