<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>好友列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body style="background-color: bisque">
<div STYLE="margin-left: 35%;width: 500px">
    <h1>
        ${user.name}欢迎您&nbsp&nbsp&nbsp
        <a href="${pageContext.request.contextPath}/main?userId=${user.id}">
            <input type="button" value="返回">
        </a>
    </h1><br>
    <c:if test="${msg != null}">
        <h3>${msg}</h3>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/addFriend?userId=${user.id}">
        <table>
            <tr>
                <td>好友账号：</td>
                <td><label><input id="friendAccount" name="friendAccount" type="text"></label></td>
                <td><input style="margin-left: 20px" type="submit" value="添加好友"></td>
            </tr>
        </table>
    </form>
    <div id="friend">
        <b>好友列表:</b><br>
        <%--迭代动态--%>
        <c:forEach items="${friends}" var="friend" varStatus="idxStatus">
        <div style="height: 15px;width: 500px;float: left">
            -------------------------------------------
        </div>
        <div style="width: 500px;height: auto">

            <br>
            <b>昵称：</b><span id="account">${friend.name}</span>
            <a style="margin-left: 70px"
               href="${pageContext.request.contextPath}/FriendData?friendId=${friend.id}&userId=${user.id}">
                <input type="button" value="好友信息"></a>
            <a style="margin-left: 100px"
               href="${pageContext.request.contextPath}/deleteFriends?friendId=${friend.id}&userId=${user.id}">
                <input type="button" value="删除好友"></a>


            </c:forEach>

        </div>
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

