package servlets;


import database.ClaimDao;
import user.Claim;
import java.sql.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.User;

/**
 *
 * @author Jayson
 */
public class ClaimServlet extends HttpServlet{

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            DateFormat df= new SimpleDateFormat("yyy-mm-dd");
            String current_date= df.format(new Date());
            User user = (User)session.getAttribute("user");
            double claim_amount = Double.parseDouble(request.getParameter("claimAmount"));
            ClaimDao claim = new ClaimDao();
            claim.claim_status(dor,status,balance);
            if(claim.claim_check(status)){
                RequestDispatcher rd = request.getRequestDispatcher("admin/dashboard.jsp");
                rd.forward(request, response);
            }else{
                String error = "It appears that the username and password is wrong. Try again.";
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
