<%--
  Created by IntelliJ IDEA.
  User: stell
  Date: 2021-08-19
  Time: 오전 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>${boardDTO.bno}</div>
<div>${boardDTO.title}</div>
<div>${boardDTO.content}</div>
<div>${boardDTO.writer}</div>
<div>${boardDTO.regdate}</div>

<div>${pageDTO}</div>

<a href="/board/list.do?page=${pageDTO.page}&size=${pageDTO.size}">목록가기</a>
<a href="/board/modify.do?bno=${boardDTO.bno}">수정/삭제</a>

</body>
</html>
