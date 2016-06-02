<%-- 
    Document   : top
    Created on : 2016/05/26, 2:12:20
    Author     : Jumpei
--%>
<%@page import="jums.UserData"
        import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    //キーワードが未入力のときにキーワードを入力してくださいと表示
    String noForm = (String) request.getAttribute("noForm");
    boolean reinput = false;
    if (noForm != null) {
        reinput = true;
    }
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
        <%//トップページ。ルートはここである%>
        <title>トップページ</title>
    </head>
    <body>
        <% if(login){ %>
        <a href="login">ログアウト</a>
        <a href="cart">買い物かご</a>
        <a href="mydata">ようこそ<%out.print(loginResult.getName());%></a>
        <%}else{%>
        <%=jh.login()%>
        <% }%>
        <br><br>

        <%//このシステムの簡単な説明が記載されている。テキストは自由%>
        このサービスは、フラストレーションを解消するために生まれた<br>
        <h1>『金銭取引が絶対に発生しない』<br>
            『いくらでも、どんなものでも購入できる(気分になれる)』<br>
            『ECサイト』です。</h1><br>


        <%//キーワード検索フォームが設置されている。検索の遷移先はsearchで、GETメソッド。未入力ならエラーを表示%>
        キーワード検索：
        <form action="search" class="Search" method="GET">
            <input type="text" name="query" value="">
            <input type="submit" value="かごゆめで検索">
        </form>

        <% if(reinput){ %>
        キーワードを入力してください
        <% } %>
    </body>
</html>