/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.PaymentDAO;
import database.MemberDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vincent
 */
public class ChargeMembershipServlet extends HttpServlet {

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
            double amount = Double.parseDouble((String)session.getAttribute("amount"));
            PaymentDAO charge = new PaymentDAO();
            MemberDAO members = new MemberDAO();
            
            List list = members.getAllRecords();
            try{
                charge.chargePayment(list, amount);
            }catch(SQLException se){
                session.setAttribute("error", "Problem charging members."); //set error message to be sent to index.jsp
                
                session.setAttribute("home", true);
                session.setAttribute("users", false);
                session.setAttribute("claims", false);
                session.setAttribute("search", false);

                response.sendRedirect("/UWE_ESD/admin/dashboard.jsp");
            }
            
            session.setAttribute("userlist", members.getAllRecords());
            session.setAttribute("success", "All members charged.");
            
            session.setAttribute("home", true);
            session.setAttribute("users", false);
            session.setAttribute("claims", false);
            session.setAttribute("search", false);
            
            response.sendRedirect("/UWE_ESD/admin/dashboard.jsp");
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
