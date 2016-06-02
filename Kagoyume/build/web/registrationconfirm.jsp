<%-- 
    Document   : registrationconfirm
    Created on : 2016/05/26, 2:33:32
    Author     : Jumpei
--%>

<%@page import="jums.UserData"
        import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserData ud = (UserData) hs.getAttribute("ud");
    ArrayList<String> chkList = ud.chkproperties();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新規会員登録確認画面</title>
    </head>
    <body>
        <% if (chkList.size() == 0) {%>
        <h1>登録確認</h1>
        名前:<%= ud.getName()%><br>
        パスワード:<%=ud.getPassword()%><br>
        メールアドレス:<%=ud.getMail()%><br>
        住所:<%= ud.getAddress()%><br>
        上記の内容で登録いたします。よろしいですか？
        <form action="registrationcomplete" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="yes" value="はい">
        </form>
        <form action="registration" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="no" value="いいえ">
        </form>
        <% } else {%>
        <h1>入力が不完全です</h1>
        <%=jh.chkinput(chkList)%>
        <a href="registration.jsp">新規会員登録ページに戻る</a>
        <% }%>
    </body>
</html>
