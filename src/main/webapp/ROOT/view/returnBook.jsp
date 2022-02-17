<%@ page import="com.luming.luming1.util.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="com.luming.luming1.util.Episode" %>

<!--返回展示精确查找到的书情况-->
<%
    Book book = (Book) request.getAttribute("book");
    ArrayList<Episode> content = book.getbookepisode();
%>

<%@include file="/ROOT/view/head.jsp" %>

<html>
<style>
    .back
    {
        width:950px; height:100%;
        margin: 0px auto; padding:20px; background: rgba(255, 250, 205, 0.8);
    }
    .name
    {
        font-size: 200%; text-align:center; font-family: "Times New Roman", Times, serif;
    }
    dd
    {
        display: inline; float: right; width: 360px;
    }
    .author
    {
        font-size: 100%; text-align:center; font-family: KaiTi; color: dimgrey;
    }
    .substance a
    {
        text-decoration: none; font-size: 120%; font-family: SimSun; color: black;
    }
    a:hover {color: blue;}
</style>

<body>
<div class="back">
    <%if(book != null)
    {%>
    <div class="name">
        <h1><strong><%=book.getbookname()%></strong></h1>
    </div>
    <div class="author">
        <p>Author: <%=book.getauthor()%></p>
    </div>
    <%}%>

    <%if(book != null)
      {
          if(content != null)
          {
              for(int i=0;i<=content.size()-1;i = i + 2)
              {
                  if( i == content.size() - 1 && content.size() % 2 != 0)
                  {
    %>
    <dl>
        <dd><p class="substance"> </p></dd>
    </dl>
    <dl>
        <dd><p class="substance">
            <a href="http://localhost:8080/LuMing/bookcontent?bookid=<%=book.getbookid()%>&episode=<%=i+1%>">
                <%=content.get(i).getepisodetitle()%>
            </a>
        </p>
        </dd>
    </dl>
    <%} else
    {%>
    <dl>
        <dd><p class="substance">
            <a href="http://localhost:8080/LuMing/bookcontent?bookid=<%=book.getbookid()%>&episode=<%=i+2%>">
                <%=content.get(i+1).getepisodetitle()%>
            </a>
        </p>
        </dd>
    </dl>
    <dl>
        <dd><p class="substance">
            <a href="http://localhost:8080/LuMing/bookcontent?bookid=<%=book.getbookid()%>&episode=<%=i+1%>">
                <%=content.get(i).getepisodetitle()%>
            </a>
        </p>
        </dd>
    </dl>
    <%}}}}%>
</div>
</body>
<%@include file="/ROOT/view/footer.jsp" %>
</html>
