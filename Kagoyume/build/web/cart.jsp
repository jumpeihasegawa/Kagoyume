<%-- 
    Document   : cart
    Created on : 2016/05/26, 2:35:53
    Author     : Jumpei
--%>

<%@page import="jums.Json"%>
<%@page import="java.util.ArrayList"
        import="com.fasterxml.jackson.databind.JsonNode"
        import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    Json js = Json.getInstence();
    HttpSession hs = request.getSession();
    ArrayList<JsonNode> cartArr = (ArrayList<JsonNode>) hs.getAttribute("cartArr");
    boolean noCart = false;
    //カートに商品があるとき
    if (cartArr != null) {
        noCart = true;
    }
    int Total = 0;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>買い物かご</title>
    </head>
    <body>
        <%if (noCart) {%>
        <% for (int i = 0; i < cartArr.size(); i++) {%>
        <%js.setJson(cartArr.get(i));%>
        <%js.getResult(0);%>
        <form action="buyconfirm" method="POST">
            <table>
                <tr>サムネイル：
                <img src="<%=js.getImage()%>"></tr>
                <%//商品IDをGETメソッドにより受け渡す%>
                <tr>商品名:
                    <%=js.getName()%></tr>
                <tr>金額:
                    <%=js.getPrice()%>円</tr>
                <tr>
                <a href="cart?delete=<%=i%>">削除</a></tr>
            </table>
            <%Total += js.getPrice();%>
            <% }%>
            <br><br>

            合計金額：<%=Total%>円
            <br><br>

            <input type="submit" name="btnSubmit" value="購入する">
            <br><br>
        </form>
        <% } else { %>
        カートに何も入っていません
        <% }%>
        <a href="top.jsp">トップページに戻る</a>
    </body>
</html>
