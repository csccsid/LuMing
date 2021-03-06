
<%--返回展示模糊查询书列表--%>

<%@ page import="com.luming.luming1.util.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="com.luming.luming1.util.Episode" %>
<%
    ArrayList<Book> bookList = (ArrayList<Book>) request.getAttribute("bookList");
    String keyWord = (String) request.getAttribute("searchname");

%>

<%@include file="/ROOT/view/head.jsp" %>

<html>
<style>
    .back
    {
        width:950px;
        margin: 0px auto; padding:20px; background: rgba(255, 250, 205, 0.8);
    }
    .notice
    {
        font-size: 90%; font-family: monospace
    }
    .name a
    {
        text-decoration: none; font-size: 150%; font-family: SimSun; color: black;
    }
    a:hover {color: red;}
    .author
    {
        font-size: 70%; font-family: KaiTi; color: dimgrey;
    }
    .abstract
    {
        font-size: 90%; font-family: SimSun
    }
    .include
    {
        text-align:center; margin:0 auto; font-size: 90%; font-family: Arial; color: black;
    }
</style>

<body>
<div class="back">
    <p class="notice">Here are <%=bookList.size()%> searching results for the key word <big style="color: red">"<%=keyWord%>"</big></p>
    <br><br>

    <ul style = "list-style:none;padding:0; margin:0;">
        <%Book bookTemp;%>
        <%for(int i=0;i<=bookList.size()-1;i++) {%>
            <%bookTemp = bookList.get(i);%>
    <li>
        <p class="name"><a href="http://localhost:8080/LuMing/bookcontent?bookid=<%=bookTemp.getbookid()%>">
            <B><%=bookTemp.getbookname()%></B></a></p>
        <p class="author">Author: <%=bookTemp.getauthor()%></p>
        <p class="abstract">Abstract: <%=bookTemp.getBookAbstract()%></p>
    </li>
        <hr style="border:1px dotted #000" />
        <%}%>
    </ul>

    <br><br><br><br><br>
    <p class = "include">If you do not find the one you want, pleas click
        <a href="http://localhost:8080/LuMing/including?bookname=<%=request.getParameter("bookname")%>">here</a>
        to inform us. And we are going to update the library as soon as possible.</p>
</div>
</body>

<%@include file="/ROOT/view/footer.jsp" %>
</html>
