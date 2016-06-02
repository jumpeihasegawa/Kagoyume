/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ログイン管理ページ どのページからも遷移できる。ログインしているかいないかで処理が分岐する
 * ログインしていない状態(各ページの「ログイン」というリンクから)で遷移してきた場合は、ユーザー名とパスワードを入力するフォームが表示される。また、「新規会員登録」というリンクも表示される。
 * ログインに成功すると、その情報をログイン状態を管理できるセッションに書き込み、そのまま直前まで閲覧していたページに遷移する
 * ログインしている状態で(各ページの「ログアウト」というリンクから)遷移してきた場合は、ログアウト処理を行う(セッションの破棄、クッキーに保存されたセッションIDを破棄)その後topへ
 *
 * @author Jumpei
 */
public class login extends HttpServlet {

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

        try {
            //リクエストパラメータの文字コードをUTF-8に変更
            request.setCharacterEncoding("UTF-8");
            //セッションスタート
            HttpSession hs = request.getSession();

            //ログアウトで飛んできたとき
            UserData loginOut = (UserData) hs.getAttribute("loginResult");
            if (loginOut != null) {
                hs.invalidate();
                log.getInstance().outlog("top.jspに遷移");
                request.getRequestDispatcher("/top.jsp").forward(request, response);
            }

            String login = request.getParameter("login");

            //リンクのログインで飛んできたとき
            if (login == null) {
                //遷移元のURLを受け取る
                String referer = request.getHeader("Referer");
                String[] refererURL = referer.split("Kagoyume/");
                hs.setAttribute("beforeURL", refererURL[1]);
                log.getInstance().outlog("login.jspに遷移");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

            //ログインボタンで飛んだとき
            if (login != null) {
                //フォームの値を受け取ってUserDataBeansに値を入れる
                UserData ud = new UserData();
                ud.setName(request.getParameter("name"));
                ud.setPassword(request.getParameter("password"));

                //空入力があるか判断
                if (ud.chkLogin().size() != 0) {
                    //ログイン画面に戻す
                    request.setAttribute("ud", ud);
                    log.getInstance().outlog("login.jspに遷移");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }

                //DB用にマッピングしてログイン検索
                UserDataDTO loginData = new UserDataDTO();
                ud.UD2DTOMapping(loginData);
                UserDataDTO loginResult = UserDataDAO.getInstance().login(loginData);
                //表示用にマッピング
                ud.DTO2UDMapping(loginResult);
                hs.setAttribute("loginResult", ud);
                log.getInstance().outlog((String) hs.getAttribute("beforeURL"));
                request.getRequestDispatcher((String) hs.getAttribute("beforeURL")).forward(request, response);
            }

        } catch (Exception e) {
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
