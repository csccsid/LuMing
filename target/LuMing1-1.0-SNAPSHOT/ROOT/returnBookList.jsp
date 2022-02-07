
<%--返回展示模糊查询书列表--%>

<%@ page import="com.luming.luming1.util.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="com.luming.luming1.util.Episode" %>
<%
    ArrayList<Book> bookList = (ArrayList<Book>) request.getAttribute("bookList");

%>
<html>
<style>
    .back
    {
        width:1000px;height:100%;
        text-align:center;margin:0 auto;padding:5px; background: rgba(255, 250, 205, 0.8);
    }
    .name
    a {
        font-size: 150%; font-family: SimSun; color: black;
    }
    .author
    {
        font-size: 50%; font-family: KaiTi; color: dimgrey;
    }
    .abstract
    {
        font-size: 80%; font-family: SimSun
    }
</style>

<body>
<div class="back">
    <ul>
        <%Book bookTemp;%>
        <%for(int i=0;i<=bookList.size()-1;i++) {%>
            <%bookTemp = bookList.get(i);%>
    <li>
        <p class="name"><a href="/<%=bookTemp.getbookname()%>/">
            <%=bookTemp.getbookname()%></a></p>
        <p class="author">Author: <%=bookTemp.getauthor()%></p>
        <p class="abstract"><%=bookTemp.getBookAbstract()%></p>
    </li>
        <%}%>
    </ul>
</div>
</body>
<%@include file="/ROOT/head.jsp" %>
<%@include file="/ROOT/footer.jsp" %>
</html>
