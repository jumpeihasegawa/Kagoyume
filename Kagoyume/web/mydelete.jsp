<%-- 
    Document   : mydelete
    Created on : 2016/05/26, 2:48:19
    Author     : Jumpei
--%>

<%@page import="jums.UserData"
        import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserData ud = (UserData) hs.getAttribute("loginResult");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ユーザー削除確認ページ</title>
    </head>
    <body>
        ユーザー情報<br>
        名前:<%= ud.getName()%><br>
        パスワード:<%=ud.getPassword()%><br>
        メールアドレス:<%=ud.getMail()%><br>
        住所:<%= ud.getAddress()%><br>
        <br>
        <h1>このユーザーをマジで削除しますか？</h1>
        <a href="mydeleteresult"><h6>はい</h6></a>
        <a href="top.jsp"><h1>いいえ</h1></a>
    </body>
</html>
