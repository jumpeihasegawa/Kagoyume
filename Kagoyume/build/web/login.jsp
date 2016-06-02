<%-- 
    Document   : login
    Created on : 2016/05/26, 2:32:06
    Author     : Jumpei
--%>

<%@page import="java.util.ArrayList"
        import="jums.UserData"
        import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    //空文字や再入力に値を残す
    UserData ud = null;
    ArrayList chkList = null;
    boolean reinput = false;
    if ((UserData) request.getAttribute("ud") != null) {
        reinput = true;
        ud = (UserData) request.getAttribute("ud");
        chkList = ud.chkLogin();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログイン管理ページ</title>
    </head>
    <body>
        <form action="login" method="POST">
            名前：<input type="text" name="name" value="<%if (reinput) {out.print(ud.getName());}%>">
            パスワード：<input type="text" name="password" value="<%if (reinput) {out.print(ud.getPassword());}%>">
            <input type="submit" name="login" value="ログイン">
        </form>
        <%if (reinput) {out.print(jh.chkinput(chkList));}%>
        <a href="registration">新規会員登録</a>
    </body>
</html>
