<%-- 
    Document   : registration
    Created on : 2016/05/26, 2:32:59
    Author     : Jumpei
--%>
<%@page import="java.util.ArrayList"
        import="jums.UserData"
        import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    //空文字や再入力に値を残す
    HttpSession hs = request.getSession();
    UserData ud = null;
    ArrayList<String> chkList = null;
    boolean reinput = false;
    if (hs.getAttribute("ud") != null) {
        reinput = true;
        ud = (UserData) hs.getAttribute("ud");
        chkList = ud.chkproperties();
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新規会員登録ページ</title>
    </head>
    <body>
        <form action="registrationconfirm" method="POST">
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

            <input type="submit" name="btnSubmit" value="新規会員登録">
        </form>
        <br>
    </body>
</html>
