
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .back
    {
        width:950px; height:100%;
        margin: 90px auto; padding:20px; background: rgba(255, 250, 205, 0.8);
    }
    .empty
    {
        text-align:center; margin:0 auto; font-size:120%; font-family: Arial; color: black;
    }
</style>

<%@include file="/ROOT/view/head.jsp" %>

<body>
<hr><hr><hr>
<div class="back">
    <p class="empty">In this way, you have successfully noticed us to record the book
        <%=request.getParameter("bookname")%>.
    Thank you for your support.</p>
</div>
</body>

<%@include file="/ROOT/view/footer.jsp" %>
</html>
