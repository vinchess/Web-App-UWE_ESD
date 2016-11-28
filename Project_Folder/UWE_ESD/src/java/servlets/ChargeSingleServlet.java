/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.MemberDAO;
import database.PaymentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.User;

/**
 *
 * @author Vincent
 */
public class ChargeSingleServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            
            PaymentDAO charge = new PaymentDAO();
            MemberDAO members = new MemberDAO();
            User user = (User)session.getAttribute("searchuser");
            List list = new ArrayList();
            list.add(user);
            double amount = 0.00;
            
            if(request.getParameter("fees")!=null){ 
                amount = Double.parseDouble(request.getParameter("fees"));
                
                try{
                    charge.chargePayment(list, amount);
                    session.setAttribute("userlist", members.getAllRecords());
                    session.setAttribute("searchuser",(User)members.getSingleById(user.getID()));
                    session.setAttribute("success", user.getFirstName() + " successfully charged.");
                    
                }catch(SQLException se){
                    session.setAttribute("error", "Problem charging "+ user.getFirstName() +"."); //set error message to be sent to index.jsp
                }finally{
                    session.setAttribute("home", false);
                    session.setAttribute("users", false);
                    session.setAttribute("claims", false);
                    session.setAttribute("search", true);

                    response.sendRedirect("/UWE_ESD/admin/dashboard.jsp");
                }
            }else if(request.getParameter("distributed")!=null){ 
                amount = Double.parseDouble(request.getParameter("distributed"));
                
                try{
                    charge.chargePayment(list, amount);
                    session.setAttribute("userlist", members.getAllRecords());
                    session.setAttribute("searchuser",(User)members.getSingleById(user.getID()));
                    session.setAttribute("success", user.getFirstName() + " successfully charged.");

                }catch(SQLException se){
                    session.setAttribute("error", "Problem charging "+ user.getFirstName() +"."); //set error message to be sent to index.jsp
                }finally{
                    session.setAttribute("home", false);
                    session.setAttribute("users", false);
                    session.setAttribute("claims", false);
                    session.setAttribute("search", true);

                    response.sendRedirect("/UWE_ESD/admin/dashboard.jsp");
                }
            }else{
                session.setAttribute("error", "Error in button submit."); //set error message to be sent to index.jsp
                
                session.setAttribute("home", false);
                session.setAttribute("users", false);
                session.setAttribute("claims", false);
                session.setAttribute("search", true);

                response.sendRedirect("/UWE_ESD/admin/dashboard.jsp");
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
