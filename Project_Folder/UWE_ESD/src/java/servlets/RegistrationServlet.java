/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.RegistrationDAO;
import user.Registration;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author N
 */
public class RegistrationServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) 
        {
            HttpSession session = request.getSession();
            
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String address = request.getParameter("address");
            String dob = request.getParameter("dob");
            
            Registration newUser = new Registration(password,firstName,lastName,address,dob);
            //session.setAttribute("newUser", newUser);
            
            RegistrationDAO register = new RegistrationDAO();
            try{
                if(register.checkExist(newUser.getID())){
                    String error = "It seems user already exist. Contact our helpdesk for assistance.";
                    session.setAttribute("error", error);
                    response.sendRedirect("/UWE_ESD");
                }else{
                    try{
                        String registerResult;
                        registerResult = register.RegisterUser(newUser);
                        session.setAttribute("success", registerResult);
                        response.sendRedirect("/UWE_ESD");
                    }catch (SQLException se){
                        String error = "There seem to be a problem registering your details. Try again later.";
                        session.setAttribute("error", error);
                        response.sendRedirect("/UWE_ESD");
                    } 
                }
            }catch(SQLException se){
                String error = "Error establishing connection with database. Try again later.";
                session.setAttribute("error", error);
                response.sendRedirect("/UWE_ESD");
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
