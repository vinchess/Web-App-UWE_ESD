/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.LoginDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.User;

/**
 *
 * @author Richard
 */
@WebServlet("/test1")
public class LoginServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(); 
            String userid = request.getParameter("userid");
            String passwordinput = request.getParameter("passwordinput");
            
            Cookie cookie = new Cookie("userid",userid); //add cookie
            cookie.setMaxAge(20*60); //cookie age is (number) * 60 seconds
            response.addCookie(cookie); //send cookie to client
            
            User userInput = new User();//!!Consider store the user object into session. Probably easier to retrieve stuff!!//
            userInput.setID(userid);
            userInput.setPassword(passwordinput);
            LoginDao loginDao = new LoginDao();
            String loginDaoReturn = loginDao.authenticateUser(userInput);
            
            if(loginDaoReturn.equals("SUCCESS")){
                session.setAttribute("user", userInput);
                session.setAttribute("username1", userid); //set session wide attributes
                session.setAttribute("password1", passwordinput);
            
                RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp"); //forwards to dashboard.jsp
                rd.forward(request, response);
            }  
            
            //temp code for test without DB only
            session.setAttribute("user", userInput);
            session.setAttribute("username1", userid); //set session wide attributes
            session.setAttribute("password1", passwordinput);
            
            RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp"); //forwards to dashboard.jsp
            rd.forward(request, response);
            //end of temp code - TO BE DELETED
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
