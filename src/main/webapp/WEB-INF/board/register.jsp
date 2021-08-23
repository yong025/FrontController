<%--
  Created by IntelliJ IDEA.
  User: stell
  Date: 2021-08-19
  Time: 오후 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/board/register.do" method="post">
  제목:<input type="text" name="title" ><br>
  내용:<textarea name="content">sample</textarea><br>
  작성자:<input type="text" name="writer" value="user1"><br>
    <button type="submit">등록</button>
</form>
</body>
</html>
