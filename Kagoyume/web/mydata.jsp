<%-- 
    Document   : mydata
    Created on : 2016/05/26, 2:45:12
    Author     : Jumpei
--%>

<%@page import="jums.UserData"
        import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserData loginResult = (UserData) hs.getAttribute("loginResult");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ユーザー情報</title>
    </head>
    <body>
        <h1>ユーザー情報</h1>
        名前:<%= loginResult.getName()%><br>
        パスワード:<%=loginResult.getPassword()%><br>
        メールアドレス:<%=loginResult.getMail()%><br>
        住所:<%= loginResult.getAddress()%><br>
        総購入金額:<%= loginResult.getUsertotal()%><br>
        <form action="myupdate" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="update" value="登録情報を更新する">
        </form>
        <form action="mydelete" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="delete" value="登録情報を削除する">
        </form>
    </body>
</html>