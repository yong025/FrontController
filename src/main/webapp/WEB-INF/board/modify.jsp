<%--
  Created by IntelliJ IDEA.
  User: stell
  Date: 2021-08-19
  Time: 오후 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    .body{
        background-color: lightgray;
    }

</style>
<head>
    <title>Update</title>
</head>
<body>
<form action="/board/modify.do" method="post">

번호: <input type="text" name="bno" readonly value="${boardDTO.bno}" class="body"><br>
제목: <input type="text" name="title" placeholder="${boardDTO.title}"><br>
내용: <textarea name="content" placeholder="${boardDTO.content}"></textarea><br>
작성자: <input type="text" name="writer" readonly placeholder="${boardDTO.writer}" class="body"><br>
작성일: <input type="text" name="regdate" readonly value="${boardDTO.regdate}" class="body"><br>

    <button type="submit">수정하기</button>
</form>
<form action="/board/delete.do?bno=${boardDTO.bno}" method="post">
    <button type="submit">삭제하기</button>
</form>
</body>
</html>
