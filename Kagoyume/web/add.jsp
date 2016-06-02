<%-- 
    Document   : add
    Created on : 2016/05/26, 2:31:36
    Author     : Jumpei
--%>

<%@page import="jums.UserData"%>
<%@page import="jums.JumsHelper"%>
<%@page import="com.fasterxml.jackson.databind.JsonNode"
        import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();

    //ログインしているか確認
    boolean login = false;
    UserData loginResult = (UserData) hs.getAttribute("loginResult");
    if (loginResult != null) {
        login = true;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カートに追加ページ</title>
    </head>
    <body>
        <% if (login) { %>
        <a href="login">ログアウト</a>
        <a href="cart">買い物かご</a>
        <a href="mydata">ようこそ<%out.print(loginResult.getName());%></a>
        <%} else {%>
        <%=jh.login()%>
        <% }%>
        <br><br>

        <%//画面には「カートに追加しました」という文言が出てくる。%>
        カートに追加しました。
        <br><br>
        
        <a href="top.jsp">トップに戻る</a>
    </body>
</html>
