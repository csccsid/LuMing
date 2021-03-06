<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>

<%--返回展示精确查找到的章节情况--%>

<%
    //获取各类参数与数据
    String bookId = request.getParameter("bookid");
    int episodeIndex = Integer.parseInt(request.getParameter("episode"));
    int episodeNumber = Integer.parseInt((String) request.getAttribute("number"));
    String title = (String) request.getAttribute("title");
    String cont = (String) request.getAttribute("content");
%>

<%@include file="/ROOT/view/head.jsp" %>


<html>

<style>
    .back
    {
        width:950px; text-align:center;
        margin: 0px auto; padding:20px; background: rgba(255, 250, 205, 0.8);
    }
    .name
    {
        font-size: 150%; text-align:center; font-family: "Times New Roman", Times, serif;
    }
    .substance
    {
        font-size: 100%; font-family: SimSun
    }
    .l a
    {
        font-size: 120%; float: left; font-family: Courier New; color: black;
    }
    a:hover {color: blue;}
    .mi a
    {
        font-size: 120%; margin: 0 auto; font-family: Courier New; color: black;
    }
    a:hover {color: blue;}
    .r a
    {
        font-size: 120%; float: right; font-family: Courier New; color: black;
    }
    a:hover {color: blue;}
</style>

<body>
<div class="back">
    <div class="name">
        <b><%=title%></b>
    </div>
    <br><br>

    <div class="substance">
        <%=cont%>
    </div>

    <br><br>
    <div class="min">
        <a href="http://localhost:8080/LuMing/bookcontent?bookid=<%=bookId%>">Content</a>
    </div>
    <br>

    <div class="l">
        <%if(episodeIndex == 1)
        {%>
        <a>No More</a>
        <%}else
        {%>
        <a href="http://localhost:8080/LuMing/bookcontent?bookid=<%=bookId%>&episode=<%=episodeIndex-1%>">Previous Chapter</a>
        <%}%>
    </div>

    <div class="r">
        <%if(episodeIndex == episodeNumber)
        {%>
        <a>No More</a>
        <%}else
        {%>
        <a href="http://localhost:8080/LuMing/bookcontent?bookid=<%=bookId%>&episode=<%=episodeIndex+1%>">Next Chapter</a>
        <%}%>
    </div>
<br><br>
</div>
</body>
<%@include file="/ROOT/view/footer.jsp" %>
</html>
