<%@ page import="com.luming.luming1.util.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="com.luming.luming1.util.Episode" %>
<%
    Book book = (Book) request.getAttribute("book");

%>

<html>
<style>
    .h
    {
        font-size: 300%; text-align:center; font-family: "Times New Roman", Times, serif;
    }
    .b
    {

    }
</style>


<head>
    <div class="h">
        <h1><strong><%=book.getbookname()%></strong></h1>
    </div>
</head>

<body>
    <div class="b">
        <%ArrayList<Episode> content = book.getbookepisode();%>
        <%for(int i=0;i<=content.size()-1;i++) {%>
        <dl>
            <dd><a><%=content.get(i).getepisodetitle()%></a></dd>
        </dl>
        <%}%>
    </div>
</body>
<%@include file="/ROOT/view/footer.jsp" %>
</html>
