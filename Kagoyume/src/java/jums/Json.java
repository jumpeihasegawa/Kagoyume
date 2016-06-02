/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author Jumpei
 */
public class Json {

    private JsonNode json;
    private String query;
    private int totalResultsReturned;
    private JsonNode result;
    private String image;
    private String name;
    private int price;
    private String productId;
    private String description;

    public Json() {
        json = null;
        query = "";
        totalResultsReturned = 0;
        result = null;
        image = "";
        name = "";
        price = 0;
        productId = "";
        description = "";
    }

    public static Json getInstence() {
        return new Json();
    }

    //Jsonを受け取る
    public void setJson(JsonNode json) {
        this.json = json;
    }

//検索したキーワード
    public String getQuery() {
        this.query = json.get("ResultSet").get("0").get("Result").get("Request").get("Query").textValue();
        return query;
    }

    //検索結果数
    public int getTotalResultsReturned() {
        this.totalResultsReturned = json.get("ResultSet").get("totalResultsReturned").intValue();
        return totalResultsReturned;
    }

    public JsonNode getResult(int num) {
        String numStr = String.valueOf(num);
        this.result = json.get("ResultSet").get("0").get("Result").get(numStr);
        return result;
    }

    //検索したサムネイル
    public String getImage() {
        this.image = result.get("Image").get("Small").textValue();
        return image;
    }

    //検索した商品名
    public String getName() {
        this.name = result.get("Name").textValue();
        return name;
    }

    //検索した金額
    public int getPrice() {
        this.price = Integer.parseInt(result.get("Price").get("_value").textValue());
        return price;
    }

    //検索した商品コード
    public String getProductId() {
        this.productId = result.get("ProductId").textValue();
        return productId;
    }

    //詳細情報
    public String getDescription() {
        this.description = result.get("Description").textValue();
        return description;
    }
}
