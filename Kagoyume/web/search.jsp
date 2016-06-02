<%@page import="jums.Json"
        import="jums.UserData"
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
        <title>検索結果ページ</title>
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

        <%//検索キーワード、検索結果数を表示%>
        検索キーワード:
        <%=js.getQuery()%>
        検索結果数:
        <%=js.getTotalResultsReturned()%>
        <br><br>

        <%//結果は上位20件まで%>
        <%for (int i = 0; i < js.getTotalResultsReturned(); i++) {%>
        <%js.getResult(i);//Jsonの配列とiが対応している%>
        <%//縦のリスト型に表示。サムネイルと、その横に商品名、金額が載っている。クリックでitemへ%>
        <form action="item" method="GET">
            <table>
                <tr>サムネイル：
                <img src="<%=js.getImage()%>"></tr>
                <%//商品IDをGETメソッドにより受け渡す%>
                <tr>商品名:
                    <%=js.getName()%></tr>
                <tr>金額:
                    <%=js.getPrice()%>円</tr>
                <tr>
                <input type="hidden" name="商品ID" value="<%=js.getProductId()%>">
                <input type="submit" name="btnSubmit" value="商品詳細">
                </tr>
            </table>
        </form>
        <% }%>
    </body>
</html>
