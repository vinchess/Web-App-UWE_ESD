package servlets;

import database.PaymentDAO;
import user.User;
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
 * @author God
 */
public class PaymentServlet extends HttpServlet {

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
                double amount = Double.parseDouble(request.getParameter("payAmount"));
                
                PaymentDAO payment = new PaymentDAO();
                
                boolean paymentReturn = payment.updatePayment(user, amount);
                
                if (paymentReturn){
                    session.setAttribute("success", "Payment made successfully."); //set success message
                    response.sendRedirect("/UWE_ESD/dashboard.jsp");
                    //System.out.println(url);
                    //response.sendRedirect("/UWE_ESD/user");
                    //RequestDispatcher rd = request.getRequestDispatcher("../dashboard.jsp"); //forwards to dashboard.jsp
                    //response.setHeader("Location", "UWE_ESD/dashboard"); 
                    //rd.forward(request, response);
                }else{
                    session.setAttribute("error", "Failed to make payment. Contact our helpdesk for assistance."); //set error message 
                    response.sendRedirect("/UWE_ESD/dashboard.jsp");
                    //RequestDispatcher rd = request.getRequestDispatcher("../dashboard.jsp"); //forwards to dashboard.jsp
                    //rd.forward(request, response);
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
