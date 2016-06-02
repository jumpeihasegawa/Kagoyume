<%-- 
    Document   : buyconfirm
    Created on : 2016/05/26, 2:37:13
    Author     : Jumpei
--%>

<%@page import="jums.Json"
        import="java.util.ArrayList"
        import="com.fasterxml.jackson.databind.JsonNode"
        import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    Json js = Json.getInstence();
    ArrayList<JsonNode> cartArr = (ArrayList<JsonNode>) hs.getAttribute("cartArr");
    int Total = 0;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入確認ページ</title>
    </head>
    <body>
        <% for (int i = 0; i < cartArr.size(); i++) {%>
        <%js.setJson(cartArr.get(i));%>
        <%js.getResult(0);%>
        <form action="buycomplete" method="POST">
            <table>
                商品名:
                <tr><%=js.getName()%></tr>
                金額:
                <tr><%=js.getPrice()%>円</tr>
                <%Total += js.getPrice(); %>
                <% }%>
                <br><br>
                合計金額：<%=Total%>円
                <br>
                発送方法<br>
                <input type="hidden" name="total" value="<%=Total%>">
                <input type="radio" name="type" value="1">速達<br>
                <input type="radio" name="type" value="2">時間指定<br>
                <input type="radio" name="type" value="3">通常<br>
                <input type="submit" name="buy" value="上記の内容で購入する">
            </table>
        </form>
        <form action="cart" method="POST">
            <input type="submit" name="cart" value="カートに戻る">
        </form>
    </body>
</html>
