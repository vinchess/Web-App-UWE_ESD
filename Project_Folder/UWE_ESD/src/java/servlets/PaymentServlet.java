package servlets;

import database.PaymentDAO;
import database.MemberDAO;
import user.User;
import java.io.IOException;
import java.io.PrintWriter;
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
                double newAmount = amount + user.getBalance();
                
                //prevent user from paying too much into their account
                if(newAmount<=10000){
                    PaymentDAO payment = new PaymentDAO();
                
                    //attempt to make payment and update database and return appropriate message
                    boolean paymentReturn = payment.updatePayment(user, amount);

                    if (paymentReturn){
                        user.setBalance(Double.parseDouble(new MemberDAO().getColumn(user.getID(),"balance")));
                        session.setAttribute("user", user);
                        session.setAttribute("success", "Payment made successfully."); //set success message
                        session.setAttribute("paymentlist", payment.getRecordsById(user));

                        response.sendRedirect("/UWE_ESD/dashboard.jsp");
                    }else{
                        session.setAttribute("error", "Failed to make payment. Contact our helpdesk for assistance."); //set error message 
                        response.sendRedirect("/UWE_ESD/dashboard.jsp");
                    }
                }else{
                    session.setAttribute("error", "Your balance will exceed &#163; 10,000 limit. Pay smaller amount."); //set error message 
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
