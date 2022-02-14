<%@ page import="com.luming.luming1.util.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="com.luming.luming1.util.Episode" %>
<%@ page import="redis.clients.jedis.JedisPool" %>
<%@ page import="redis.clients.jedis.Jedis" %>
<%@ page import="com.luming.luming1.pool.SqlConpool" %>
<%@ page import="com.luming.luming1.DAO.OperationDAO" %>
<%@ page import="static com.luming.luming1.util.SerializeUtil.serialize" %>
<%@ page import="static com.luming.luming1.util.SerializeUtil.unserialize" %>

<!--返回展示精确查找到的书情况-->
<%
    String bookid = request.getParameter("bookid");
    JedisPool jpool;
    jpool = (JedisPool) request.getSession().getServletContext().getAttribute("jedispool");
    Jedis jedis = jpool.getResource();
    Book book = new Book();

    //获取book对象，如果在redis中有就直接提取。若没有就存入redis中。
    if(!jedis.exists(serialize(bookid)))
    {
        //获取全局数据库连接池
        SqlConpool scp;
        scp = (SqlConpool) request.getSession().getServletContext().getAttribute("dataconpool");

        OperationDAO operation = new OperationDAO();
        book.setId(bookid);
        //获取该书章节数
        int epnumber = operation.getTable(book, scp);
        System.out.println("number: "+epnumber);
        if(epnumber >= 0)
        {
            book = operation.getbyId(book, scp, epnumber);

            //在redis中存入该书
            jedis.set(serialize(bookid),serialize(book));
            //生存时间设定为30分钟
            jedis.expire(bookid, 3000);
        }
    } else
    {
        book = (Book) unserialize(jedis.get(serialize(bookid)));
    }
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
          ArrayList<Episode> content = book.getbookepisode();
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
