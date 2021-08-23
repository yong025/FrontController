<%--
  Created by IntelliJ IDEA.
  User: stell
  Date: 2021-08-18
  Time: 오후 4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script>

    let bno= '${param.bno}'

    if(bno){
        alert(bno)
        window.history.replaceState(null, '','/board/list');
    }

</script>

<h1>list page</h1>

<h4>${pageMaker}</h4>

<form action="/board/list.do" method="get">
    <input type="hidden" name="page" value="1"> <!--화면에는 보이지 않지만 설정값을 함께 전달해줌-->
    <select name="size">
        <option value="10" ${pageMaker.size == 10?"selected":""}>10</option>
        <option value="20" ${pageMaker.size == 20?"selected":""}>20</option>
        <option value="50" ${pageMaker.size == 50?"selected":""}>50</option>
        <option value="100" ${pageMaker.size == 100?"selected":""}>100</option>
    </select>
    <button type="submit">적용</button>
</form>

<ul>
    <c:forEach items="${dtolist}" var="dtolist">
    <li>
        <div>
            ${dtolist.bno}
            <a href="/board/read.do?bno=${dtolist.bno}&page=${pageMaker.page}&size=${pageMaker.size}">${dtolist.title}</a>
            ${dtolist.viewcount}
            ${dtolist.writer}
           ${dtolist.regdate}
        </div>
    </li>
    </c:forEach>
</ul>

<hr>

<style>
    .pagelist{
        list-style: none;
        display: flex;
        flex-direction: row;
    }
    .pagelist li{
        margin-left: 0.3em ;
        background-color: lightgray;
    }
</style>

<ul class="pagelist">

    <c:if test="${pageMaker.prev}">
        <li><a href="/board/list?page=${pageMaker.start -1 }&size=${pageMaker.size}">Prev</a></li>
    </c:if>

    <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="page">
    <li class="${page == pageMaker.page?"current":""}"><a href="/board/list?page=${page}&size=${pageMaker.size}">${page}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next}">
        <li><a href="/board/list?page=${pageMaker.end +1 }&size=${pageMaker.size}"> Next</a></li>
    </c:if>
</ul>
</body>
</html>
