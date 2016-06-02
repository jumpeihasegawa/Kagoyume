/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jumpei
 */
public class CreateTable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection db_con = null;
        PreparedStatement db_st = null;
        String SQL = "";

        try {
            //データベースに接続する
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Kagoyume_db", "root", "");
            System.out.println("DBConnected!!");

            //ユーザー情報管理テーブル。初回の登録や更新以外に、購入時には総購入金額が更新
            SQL = "CREATE TABLE user_t(userID int, name varchar(255), password varchar(255),mail varchar(255), address text, total int, newDate Datetime);";
            db_st = db_con.prepareStatement(SQL);
            db_st.executeUpdate();
            System.out.println("user_tTableComplet!!");

            //購入管理テーブル。1度の購入につき1レコード生成される
            SQL = "CREATE TABLE buy_t(buyID int, userID int, total int, type int, buyDate Datetime);";
            db_st = db_con.prepareStatement(SQL);
            db_st.executeUpdate();
            System.out.println("byt_tTableComplet!!");

            //主キーの設定
            SQL = "ALTER TABLE user_t ADD PRIMARY KEY(userID)";
            db_st = db_con.prepareStatement(SQL);
            db_st.executeUpdate();
            System.out.println("PRIMARY_KEY(userID)Complet!!");
            SQL = "ALTER TABLE buy_t ADD PRIMARY KEY(buyID)";
            db_st = db_con.prepareStatement(SQL);
            db_st.executeUpdate();
            System.out.println("PRIMARY KEY(buyID)Complet!!");

            //AUTO_ INCREMENTを設定
            SQL = "ALTER TABLE user_t CHANGE userID userID int AUTO_INCREMENT";
            db_st = db_con.prepareStatement(SQL);
            db_st.executeUpdate();
            System.out.println("AUTO_INCREMENT(userID)Complet!!");
            SQL = "ALTER TABLE buy_t CHANGE buyID buyID int AUTO_INCREMENT";
            db_st = db_con.prepareStatement(SQL);
            db_st.executeUpdate();
            System.out.println("AUTO_INCREMENT(buyID)Complet!!");

            //外部キーを設定
            SQL = "ALTER TABLE buy_t ADD FOREIGN KEY (userID) REFERENCES user_t(userID)";
            db_st = db_con.prepareStatement(SQL);
            db_st.executeUpdate();
            System.out.println("FOREIGN KEY(buyID)Complet!!");

            db_st.close();
            db_con.close();

        } catch (SQLException e_sql) {
            out.println("接続時にエラーが発生しました：" + e_sql.toString());
        } catch (Exception e) {
            out.println("接続時にエラーが発生しました：" + e.toString());
        } finally {
            if (db_con != null) {
                try {
                    db_con.close();
                } catch (Exception e_con) {
                    System.out.println(e_con.getMessage());
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
