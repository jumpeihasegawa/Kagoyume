package jums;

import base.DBManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する DB接続系はDBManagerクラスに一任 基本的にはやりたい1種類の動作に対して1メソッド
 *
 * @author hayashi-s
 */
public class UserDataDAO {

    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance() {
        return new UserDataDAO();
    }

    public void insert(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO user_t(name,password,mail,address,total,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, udd.getName());
            st.setString(2, udd.getPassword());
            st.setString(3, udd.getMail());
            st.setString(4, udd.getAddress());
            st.setInt(5, udd.getUserTotal());
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));//指定のタイムスタンプ値からSQL格納用のDATE型に変更
            st.executeUpdate();
            System.out.println("insert completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public UserDataDTO login(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT * FROM user_t WHERE name=? AND password=?");
            st.setString(1, udd.getName());
            st.setString(2, udd.getPassword());

            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUdd = new UserDataDTO();
            resultUdd.setUserID(rs.getInt(1));
            resultUdd.setName(rs.getString(2));
            resultUdd.setPassword(rs.getString(3));
            resultUdd.setMail(rs.getString(4));
            resultUdd.setAddress(rs.getString(5));
            resultUdd.setUserTotal(rs.getInt(6));
            resultUdd.setNewDate(rs.getTimestamp(7));

            System.out.println("login completed");

            return resultUdd;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public void buy(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO buy_t(userID,total,type,buyDate) VALUES(?,?,?,?)");
            st.setInt(1, udd.getUserID());
            st.setInt(2, udd.getBuyTotal());
            st.setInt(3, udd.getType());
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("buy completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public void upDate(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();

            String sql = "UPDATE user_t SET name=?,password=?,mail=?,address=? WHERE userID=?";

            st = con.prepareStatement(sql);
            st.setString(1, udd.getName());
            st.setString(2, udd.getPassword());
            st.setString(3, udd.getMail());
            st.setString(4, udd.getAddress());
            st.setInt(5, udd.getUserID());
            st.executeUpdate();

            System.out.println("upData completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public void delete(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();

            String sql = "DELETE FROM user_t WHERE userID = ?";

            st = con.prepareStatement(sql);
            st.setInt(1, udd.getUserID());

            st.executeUpdate();

            System.out.println("delete completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }
}
