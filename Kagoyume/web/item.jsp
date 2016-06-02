<%-- 
    Document   : item
    Created on : 2016/05/26, 2:27:53
    Author     : Jumpei
--%>

<%@page import="jums.Json"%>
<%@page import="jums.UserData"
        import="jums.JumsHelper"
        import="com.fasterxml.jackson.databind.JsonNode"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    JsonNode json = (JsonNode) request.getAttribute("json");
    Json js = Json.getInstence();
    js.setJson(json);

    //ログインしているか確認
    boolean login = false;
    HttpSession hs = request.getSession();
    UserData loginResult = (UserData) hs.getAttribute("loginResult");
    if (loginResult != null) {
        login = true;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>商品詳細ページ</title>
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

        
        <%js.getResult(0);%>

        サムネイル：<br>
        <img src="<%=js.getImage()%>"><br>
        <%//商品IDをGETメソッドにより受け渡す%>
        商品名:<br>
        <%=js.getName()%><br>
        金額:<br>
        <%=js.getPrice()%>円<br>
        詳細情報:<br>
        <%//概要や評価値%>
        <%=js.getDescription()%><br>


        <%//「カートに追加する」ボタンがあり、クリックするとaddに遷移する。%>
        <form action="add" method="GET">
            <input type="hidden" name="商品ID" value="<%=js.getProductId()%>">
            <input type="submit" name="add" value="カートに追加する">
        </form>
    </body>
</html>
