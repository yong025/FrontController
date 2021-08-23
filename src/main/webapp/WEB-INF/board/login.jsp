<%--
  Created by IntelliJ IDEA.
  User: stell
  Date: 2021-08-13
  Time: 오후 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login</h1>

<style>
    .warnDiv {
        background-color: #974a2d;
    }
</style>

<c:if test="${param.result != null && param.result.equals('fail')}">
<div class="warnDiv">
    <h1>Login Failed</h1>
</div>
</c:if>

<form action="/login.do" method="post">
    ID:<input type="text" name="mid">
    PW:<input type="text" name="mpw">
    <input type="checkbox" name="remember">Remember-me
    <button type="submit">Login</button>
</form>
</body>
</html>
