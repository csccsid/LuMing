<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>

<%--返回展示精确查找到的章节情况--%>

<%
    //获取各类参数与数据
    String bookId = request.getParameter("bookid");
    int episodeIndex = Integer.parseInt(request.getParameter("episode"));
    String title = (String) request.getAttribute("title");
    String cont = (String) request.getAttribute("content");
%>

<%@include file="/ROOT/view/head.jsp" %>


<html>

<style>
    .back
    {
        width:950px; height:100%;
        margin: 90px auto; padding:20px; background: rgba(255, 250, 205, 0.8);
    }
    .name
    {
        font-size: 150%; text-align:center; font-family: "Times New Roman", Times, serif;
    }
    .substance
    {
        font-size: 100%; font-family: SimSun
    }
    .content a
    {

    }
    a:hover {color: blue;}
</style>

<body>
<div class="back">
    <div class="name">
        <%=title%>
    </div>

    <div class="substance">
        <%=cont%>
    </div>

    <div>

    </div>
</div>
</body>
<%@include file="/ROOT/view/footer.jsp" %>
</html>
