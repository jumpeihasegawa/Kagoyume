<%-- 
    Document   : myupdate
    Created on : 2016/05/26, 2:45:51
    Author     : Jumpei
--%>

<%@page import="java.util.ArrayList"
        import="jums.UserData"
        import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    //空文字や再入力に値を残す
    HttpSession hs = request.getSession();
    UserData ud = null;
    boolean reinput = false;
    if (hs.getAttribute("loginResult") != null) {
        reinput = true;
        ud = (UserData) hs.getAttribute("loginResult");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>更新ページ</title>
    </head>
    <body>
        <form action="myupdateresult" method="POST">
            ユーザー名:
            <input type="text" name="name" value="<% if (reinput) {out.print(ud.getName());}%>">
            <br>
            パスワード:
            <input type="text" name="password" value="<% if (reinput) {out.print(ud.getPassword());}%>">
            <br>
            メールアドレス:
            <input type="text" name="mail" value="<% if (reinput) {out.print(ud.getMail());}%>">
            <br>
            住所:
            <input type="text" name="address" value="<% if (reinput) {out.print(ud.getAddress());}%>">
            <br>
            <input type="submit" name="btnSubmit" value="更新">
        </form>
        <br>
    </body>
</html>
