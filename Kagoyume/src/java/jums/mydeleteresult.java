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
 *
 * @author Jumpei
 */
public class mydeleteresult extends HttpServlet {

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
            //セッションスタート
            HttpSession hs = request.getSession();

            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更

//            アクセスルートチェック
//            String accesschk = request.getParameter("ac");
//            if (accesschk == null || (Integer) session.getAttribute("ac") != Integer.parseInt(accesschk)) {
//                throw new Exception("不正なアクセスです");
//            }
            
            //ユーザー情報を受け取る
            UserData loginResult = (UserData)hs.getAttribute("loginResult");
            
            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            UserDataDTO deleteData = new UserDataDTO();
            loginResult.UD2DTOMapping(deleteData);

            //DBへデータの挿入
            UserDataDAO.getInstance().delete(deleteData);

            //成功したのでセッションの値を削除
            hs.invalidate();
            
            log.getInstance().outlog("mydeleteresult.jspに遷移");
            request.getRequestDispatcher("/mydeleteresult.jsp").forward(request, response);
        } catch (Exception e) {
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
