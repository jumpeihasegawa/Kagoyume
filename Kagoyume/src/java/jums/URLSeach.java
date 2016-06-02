package jums;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * URLでやり取りするオブジェクト
 *
 * @author Jumpei
 */
public class URLSeach {

    //商品検索の固定URL
    private String itemURL;
    private String queryEnc;
    private String result;
    private JsonNode head;
    private String ProductIDEnc;

    public URLSeach() {
        itemURL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid=dj0zaiZpPVFRemF3dGRvaHJobyZzPWNvbnN1bWVyc2VjcmV0Jng9N2Y-&";
        queryEnc = "";
        result = "";
        head = null;
        ProductIDEnc = "";
    }

    public void setQueryURL(String query) {
        //日本語をURL用に変換
        try {
            queryEnc = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.toString());
        }
        //キーワード検索のURL
        this.itemURL += "query=" + queryEnc;
    }

    public void setProductIDURL(String ProductID) {
        //日本語をURL用に変換
        try {
            ProductIDEnc = URLEncoder.encode(ProductID, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.toString());
        }
        //キーワード検索のURL
        this.itemURL += "product_id=" + ProductIDEnc;
    }

    //ヤフーの商品検索をする
    public void getItem() {
        try {
            URL url = new URL(itemURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String tmp = "";
            while ((tmp = in.readLine()) != null) {
                this.result += tmp;
            }
            in.close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //文字列からJsonへの変換
    public JsonNode getJsonNode() {
        try {
            JsonFactory jfactory = new JsonFactory();
            JsonParser parser = jfactory.createParser(result);
            ObjectMapper mapper = new ObjectMapper();
            head = mapper.readTree(parser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return head;
    }
}
