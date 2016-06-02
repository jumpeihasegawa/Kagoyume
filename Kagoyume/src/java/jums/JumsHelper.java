package jums;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;

/**
 * 画面系の処理や表示を簡略化するためのヘルパークラス。定数なども保存されます
 *
 * @author hayashi-s
 */
public class JumsHelper {

    //loginへのリンクを定数として設定
    private final String loginURL = "login";
    
    public static JumsHelper getInstance() {
        return new JumsHelper();
    }

    //loginへのリンクを返却
    public String login() {
        return "<a href=\"" + loginURL + "\">ログイン</a>";
    }
    
    /**
     * 入力されたデータのうち未入力項目がある場合、チェックリストにしたがいどの項目が 未入力なのかのhtml文を返却する
     *
     * @param chkList　UserDataBeansで生成されるリスト。未入力要素の名前が格納されている
     * @return 未入力の項目に対応する文字列
     */
    public String chkinput(ArrayList<String> chkList) {
        String output = "";
        for (String val : chkList) {
            if (val.equals("name")) {
                output += "名前";
            }
            if (val.equals("password")) {
                output += "パスワード";
            }
            if (val.equals("mail")) {
                output += "メールアドレス";
            }
            if (val.equals("address")) {
                output += "住所";
            }
            output += "が未記入です<br>";
        }
        return output;
    }

}
