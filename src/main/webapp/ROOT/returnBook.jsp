<%@ page import="com.luming.luming1.util.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="com.luming.luming1.util.Episode" %>

<!--返回展示精确查找到的书情况-->
<%
    Book book = (Book) request.getAttribute("book");
    ArrayList<Episode> content = book.getbookepisode();
%>


<html>
<style>
    .back
    {
        width:950px; height:100%;
        margin: 90px auto; padding:20px; background: rgba(255, 250, 205, 0.8);
    }
    .name
    {
        font-size: 200%; text-align:center; font-family: "Times New Roman", Times, serif;
    }
    .author
    {

    }
    .abstract
    {

    }
    .content a
    {
        text-decoration: none; font-size: 100%; font-family: SimSun
    }
    a:hover {color: blue;}
</style>

<%@include file="/ROOT/head.jsp" %>

<body>
<div class="back">
    <%if(book != null)
    {%>
    <div class="name">
        <h1><strong><%=book.getbookname()%></strong></h1>
    </div>
    <%}%>

    <%if(book != null)
      {
          if(content != null)
          {
              for(int i=0;i<=content.size()-1;i++) {%>
    <dl>
        <dd><p class="content">
                <a href="http://localhost:8080/LuMing/bookcontent?bookid=
                <%=book.getbookid()%>&episode=<%=i+1%>">
                <%=content.get(i).getepisodetitle()%>
                </a>
            </p>
        </dd>
    </dl>
    <%}}}%>
</div>
</body>
<%@include file="/ROOT/footer.jsp" %>
</html>
