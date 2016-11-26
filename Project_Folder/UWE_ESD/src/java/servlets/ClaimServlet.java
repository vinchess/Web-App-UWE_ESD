/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import database.ClaimDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.User;

/**
 *
 * @author User
 */
public class ClaimServlet extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //get session
            HttpSession session = request.getSession();
            
            //get Params from JSP
            String claimRational = request.getParameter("rationale");
            double claim_amount = Double.parseDouble(request.getParameter("claimAmount"));
            
            //get user ID
            User user = (User)session.getAttribute("user");
            String userid = user.getID();
            
            //add claim to DB
            ClaimDAO claimDao = new ClaimDAO();
            boolean addClaimReturn = claimDao.add_claim(userid, claim_amount,claimRational);
            
            //if claim added successfully
            if (addClaimReturn){
                session.setAttribute("success", "Claim added successfully"); //set success message
                session.setAttribute("claimlist", claimDao.getClaimsById(user));
                
                response.sendRedirect("/UWE_ESD/dashboard.jsp");
            }
            //else 
            else 
            {
                session.setAttribute("error", "Error adding claim"); //set error message 
                response.sendRedirect("/UWE_ESD/dashboard.jsp");
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
