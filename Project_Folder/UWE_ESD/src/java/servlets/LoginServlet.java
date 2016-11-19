package servlets;

import user.User;
import database.MemberDAO;
import database.LoginDao;
import java.util.*;
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
            
            User userInput = new User(); //new user object
            userInput.setID(userid);    //set user ID 
            userInput.setPassword(passwordinput);   //set user password
            
            LoginDao loginDao = new LoginDao(); //new login DAO 
            String loginDaoReturn = loginDao.authenticateUser(userInput);  //authenticate user

            MemberDAO member = new MemberDAO();
            User user = (User)member.getSingleById(userid);

            
            if(loginDaoReturn.equals("SUCCESS")){
                session.setAttribute("user", user); //set User object as session wide attribute
            
                RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp"); //forwards to dashboard.jsp
                rd.forward(request, response);
            }  
            
            else 
            {
                session.setAttribute("loginErrorMessage", "Wrong username or password combination");
                response.sendRedirect("/UWE_ESD");
//                RequestDispatcher rd = request.getRequestDispatcher("UWE_ESD");
//                rd.forward(request, response);
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
