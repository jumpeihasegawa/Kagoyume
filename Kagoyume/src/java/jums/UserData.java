package jums;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * フォームからの入出力されるデータを格納するBeansオブジェクト DTOからの変換、逆に、ＤＴＯへの変換を行うメソッドを保持する
 *
 * @author Jumpei
 */
public class UserData implements Serializable {

    //user_tテーブルに対応
    private int userID;
    private String name;
    private String password;
    private String mail;
    private String address;
    private int usertotal;
    private Timestamp newDate;

    //buy_tテーブルに対応
    private int buyID;
    private int buyTotal;
    private int type;
    private Timestamp buyDate;

    public UserData() {
        //user_tテーブルに対応
        this.userID = 0;
        this.name = "";
        this.password = "";
        this.mail = "";
        this.address = "";
        this.usertotal = 0;
        this.newDate = null;

        //buy_tテーブルに対応
        this.buyID = 0;
        this.buyTotal = 0;
        this.type = 0;
        this.buyDate = null;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //空文字(未入力)の場合空文字をセット
        if (name.trim().length() == 0) {
            this.name = "";
        } else {
            this.name = name;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        //初期選択状態の場合空文字をセット
        if (password.trim().length() == 0) {
            this.password = "";
        } else {
            this.password = password;
        }
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        if (mail.trim().length() == 0) {
            this.mail = "";
        } else {
            this.mail = mail;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.trim().length() == 0) {
            this.address = "";
        } else {
            this.address = address;
        }
    }

    public int getUsertotal() {
        return usertotal;
    }

    public void setUsertotal(int usertotal) {
        this.usertotal = usertotal;
    }

    public Timestamp getNewDate() {
        return newDate;
    }

    public void setNewDate(Timestamp newDate) {
        this.newDate = newDate;
    }

    public int getBuyID() {
        return buyID;
    }

    public void setBuyID(String buyID) {
        if (buyID.trim().length() == 0) {
            this.buyID = 0;
        } else {
            this.buyID = Integer.parseInt(buyID);
        }
    }

    public int getBuytotal() {
        return buyTotal;
    }

    public void setBuytotal(String buyTotal) {
        if (buyTotal.trim().length() == 0) {
            this.buyTotal = 0;
        } else {
            this.buyTotal = Integer.parseInt(buyTotal);
        }
    }

    public int getType() {
        return type;
    }

    public void setType(String type) {
        if (type.trim().length() == 0) {
            this.type = 0;
        } else {
            this.type = Integer.parseInt(type);
        }
    }

    public Timestamp getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Timestamp buyDate) {
        this.buyDate = buyDate;
    }

    public ArrayList<String> chkLogin() {
        ArrayList<String> chkList = new ArrayList<String>();
        if (this.name.equals("")) {
            chkList.add("name");
        }
        if (this.password.equals("")) {
            chkList.add("password");
        }
        return chkList;
    }

    public ArrayList<String> chkproperties() {
        ArrayList<String> chkList = new ArrayList<String>();
        if (this.name.equals("")) {
            chkList.add("name");
        }
        if (this.password.equals("")) {
            chkList.add("password");
        }
        if (this.mail.equals("")) {
            chkList.add("mail");
        }
        if (this.address.equals("")) {
            chkList.add("address");
        }
        return chkList;
    }

    public ArrayList<String> chkBuy() {
        ArrayList<String> chkList = new ArrayList<String>();
        if (this.buyTotal ==0) {
            chkList.add("buyTotal");
        }
        if (this.type == 0) {
            chkList.add("password");
        }
        return chkList;
    }
    
    public void UD2DTOMapping(UserDataDTO udd) {
        udd.setName(this.name);
        udd.setPassword(this.password);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
        udd.setUserID(this.userID);
    }

    //表示用に変換
    public void DTO2UDMapping(UserDataDTO udd) {
        this.setUserID(udd.getUserID());
        this.setName(udd.getName());
        this.setPassword(udd.getPassword());
        this.setMail(udd.getMail());
        this.setAddress(udd.getAddress());
        this.setUsertotal(udd.getUserTotal());
        this.setNewDate(udd.getNewDate());
    }
}
