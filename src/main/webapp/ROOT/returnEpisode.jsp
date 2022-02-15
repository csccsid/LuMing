<%@ page import="redis.clients.jedis.JedisPool" %>
<%@ page import="redis.clients.jedis.Jedis" %>
<%@ page import="com.luming.luming1.util.Book" %>
<%@ page import="static com.luming.luming1.util.SerializeUtil.serialize" %>
<%@ page import="com.luming.luming1.pool.SqlConpool" %>
<%@ page import="com.luming.luming1.DAO.OperationDAO" %>
<%@ page import="static com.luming.luming1.util.SerializeUtil.unserialize" %>

<%--返回展示精确查找到的章节情况--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String bookid = request.getParameter("bookid");
    int episode = Integer.parseInt(request.getParameter("episode"));
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
            book.setBookEpisodeNumber(epnumber);
            book = operation.getbyId(book, scp);

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
<head>
    <title>Title</title>
</head>
<body>

</body>
<%@include file="/ROOT/footer.jsp" %>
</html>
