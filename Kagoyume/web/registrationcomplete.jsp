<%-- 
    Document   : registrationcomplete
    Created on : 2016/05/26, 2:33:59
    Author     : Jumpei
--%>

<%@page import="java.util.ArrayList"
        import="jums.UserData"
        import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserData ud = (UserData) request.getAttribute("ud");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録結果</title>
    </head>
    <body>
        <a href="top.jsp"><h1>トップページへ戻る</h1></a>
        名前:<%= ud.getName()%><br>
        パスワード:<%=ud.getPassword()%><br>
        メールアドレス:<%=ud.getMail()%><br>
        住所:<%= ud.getAddress()%><br>
        以上の内容で登録しました。
    </body>
</html>
