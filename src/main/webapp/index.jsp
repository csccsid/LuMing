<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<head><style>
    body
    {
        width: 100%;
        height: 100%;
        background: url("ROOT/background.jpeg") no-repeat;
        background-position: center center;
        background-attachment: fixed;
        z-index:-10;
        background-size: cover;
        background-color: rgba(123, 104, 238, 1);
    }
    .b{ width:650px;height:240px;
        text-align:center;margin:0 auto;padding:5px; background: rgba(255, 255, 255, 0.4);}
    .t{  font-size: 400%; text-align:center; color: #2F4F4F;
        font-family: "Times New Roman", Times, serif;}
    .s{  text-align:center;
        font-family: "Times New Roman", Times, serif;}
    .in{ text-align: center}
</style></head>

<body>
<<div class="b">
    <h1><strong><div class="t">LuMing</div></strong>
    </h1>
    <h1><i><div class="s">Quick Read Library</div></i></h1>
</div>
</div>
<div class="in">
<form method="post" action="searchresult">
    <input type="text" style="margin-top: 30px; height: 35px; width: 850px;
           border-radius:15px; font-size: 18px;"
           placeholder="enter the name please" name="bookname">
    <input type="submit" style="position:absolute; right: 330px; top: 311px;
           width: 90px; height: 38px; background-color: #696969; color: #F5FFFA;
           border-radius:5px; font-size: 25px"  value="search">
</form>
</div>
<%@include file="/ROOT/view/footer2.jsp" %>
</body>
</html>