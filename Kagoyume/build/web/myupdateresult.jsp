<%-- 
    Document   : myupdateresult
    Created on : 2016/05/26, 2:46:14
    Author     : Jumpei
--%>

<%@page import="jums.UserData"
        import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserData ud = (UserData) request.getAttribute("upDate");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="top.jsp"><h1>トップページへ戻る</h1></a>
        名前:<%= ud.getName()%><br>
        パスワード:<%=ud.getPassword()%><br>
        メールアドレス:<%=ud.getMail()%><br>
        住所:<%= ud.getAddress()%><br>
        以上の内容で更新しました。
    </body>
</html>
