/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import database.ClaimDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.User;
import user.Claim;

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
            Date userDOR = user.getDOR();
            
            Calendar today = Calendar.getInstance();
            Calendar registerDay = Calendar.getInstance();
            
            today.setTime(new Date());registerDay.setTime(userDOR);
            
            today.clear(Calendar.HOUR_OF_DAY);registerDay.clear(Calendar.HOUR_OF_DAY);
            today.clear(Calendar.AM_PM);registerDay.clear(Calendar.AM_PM);
            today.clear(Calendar.MINUTE);registerDay.clear(Calendar.MINUTE);
            today.clear(Calendar.SECOND);registerDay.clear(Calendar.SECOND);
            today.clear(Calendar.MILLISECOND);registerDay.clear(Calendar.MILLISECOND);
            
            Date startDate = registerDay.getTime();Date endDate = today.getTime();
            long startTime = startDate.getTime();long endTime = endDate.getTime();
            long diffTime = endTime - startTime;
            long diffDays = diffTime / (1000 * 60 * 60 * 24);
            long minDays =  6 * 30;
            
            int thisYear = today.get(Calendar.YEAR);
            int claimAmount = 0;
            //add claim to DB
            ClaimDAO claimDao = new ClaimDAO();
            List list = claimDao.getClaimsById(user);
            
            for(Object c:list){
                Claim userClaim = (Claim)c;
                Calendar checkClaim = Calendar.getInstance();
                checkClaim.setTime(userClaim.getDate());
                if(checkClaim.get(Calendar.YEAR)==thisYear && userClaim.getStatus().equals("ACCEPTED")) { claimAmount++; }
            }
            
            
            if(minDays<diffDays){
                if(claimAmount < 2){
                    boolean addClaimReturn = claimDao.add_claim(userid, claim_amount,claimRational);
                    //if claim added successfully
                    if (addClaimReturn){
                        session.setAttribute("success", "Claim added successfully"); //set success message
                        session.setAttribute("claimlist", claimDao.getClaimsById(user));

                        response.sendRedirect("/UWE_ESD/dashboard.jsp");
                    }else{
                        session.setAttribute("error", "Error adding claim"); //set error message 
                        response.sendRedirect("/UWE_ESD/dashboard.jsp");
                    }
                }else{
                    session.setAttribute("error", "You've exceeded max amount of claims."); //set error message 
                response.sendRedirect("/UWE_ESD/dashboard.jsp");
                }
            }else{
                session.setAttribute("error", "Your account has not mature yet."); //set error message 
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
