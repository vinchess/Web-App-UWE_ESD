package servlets;

import database.MemberDAO;
import user.User;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vincent
 */
public class EditUserServlet extends HttpServlet {

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
            User user = (User)session.getAttribute("user");
            
            if(request.getParameter("password").equals(request.getParameter("reenterpassword"))){
                
                user.setFirstName(request.getParameter("firstname"));
                user.setLastName(request.getParameter("lastname"));
                user.setAddress(request.getParameter("address"));
                user.setDOB(request.getParameter("dob"));

                MemberDAO member = new MemberDAO();

                try{
                    session.setAttribute("success", member.editDetails(user)); //set error message to be sent to index.jsp
                    response.sendRedirect("/UWE_ESD/dashboard.jsp"); //redirect back to main page
                }catch(SQLException se){
                    session.setAttribute("error", "Connection error, please try again later."); //set error message to be sent to index.jsp
                    response.sendRedirect("/UWE_ESD/dashboard.jsp"); //redirect back to main page
                }catch(IOException io){
                    session.setAttribute("error", "Address is invalid, try again."); //set error message to be sent to index.jsp
                    response.sendRedirect("/UWE_ESD/dashboard.jsp"); //redirect back to main page
                }
            
            }else{
                session.setAttribute("error", "Password not identical. Try again."); //set error message to be sent to index.jsp
                response.sendRedirect("/UWE_ESD/dashboard.jsp"); //redirect back to main page
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
