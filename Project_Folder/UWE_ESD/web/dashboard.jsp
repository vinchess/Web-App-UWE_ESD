<%-- 
    Document   : dashboard
    Created on : Nov 7, 2016, 10:19:38 PM
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <%@include file="/lib/bootstrap.html" %>
        <%@include file="/lib/banner.html" %>
        <%@ page import="java.util.*" %>
        <%@ page import="database.PaymentDAO" %>
        <%@ page import="user.Payments" %>
        <%@ page import="user.User" %>
    </head>
    <body>
        <div class="container">
            <div class="col-md-3 text-center">
                <img src="assets/user_icon.png" height="150px">
                    <h2><%=((User)session.getAttribute("user")).getFirstName()%></h2>
                    <h3>Balance &#163;<%=String.format("%.2f",((User)session.getAttribute("user")).getBalance())%></h3>
                    <button type="button" class="btn btn-primary btn-block" 
                            data-toggle="modal" data-target="#payment" aria-label="Left Align">
                        <div class="col-md-1">
                            <span class="glyphicon glyphicon-gbp"></span>
                        </div>
                        <div class="col-md-2 ">
                            PAY
                        </div>
                    </button>
                    <button type="button" class="btn btn-primary btn-block" 
                            data-toggle="modal" data-target="#claim" aria-label="Left Align">
                        <div class="col-md-1">
                            <span class="glyphicon glyphicon-pencil"></span> 
                        </div>
                        <div class="col-md-2 ">
                            CLAIM
                        </div>
                    </button>
                    <button type="button" class="btn btn-success btn-block" 
                            data-toggle="modal" data-target="#edit" aria-label="Left Align">
                        <div class="col-md-1">
                            <span class="glyphicon glyphicon-cog"></span> 
                        </div>
                        <div class="col-md-2 ">
                            EDIT ACCOUNT
                        </div>
                    </button>
                    <button type="button" class="btn btn-danger btn-block" 
                            data-toggle="modal" data-target="#claim" aria-label="Left Align">
                        <div class="col-md-1">
                            <span class="glyphicon glyphicon-remove"></span> 
                        </div>
                        <div class="col-md-2 ">
                            DELETE ACCOUNT
                        </div>
                    </button>
                    <button type="button" class="btn btn-default btn-block" 
                            aria-label="Left Align" onClick="location.href='LogoutServlet'">
                        <div class="col-md-1">
                            <span class="glyphicon glyphicon-off"></span> 
                        </div>
                        <div class="col-md-2 ">
                            LOG OUT
                        </div>
                    </button>
            </div>
            <div class="col-md-9 ">
                <%
                    String status =((User)session.getAttribute("user")).isUserValid();
                    if(status.equals("APPLIED"))
                        out.println("<h1><span class=\"label label-warning\">APPLIED</span></h1>");
                    else if(status.equals("APPROVED"))
                        out.println("<h1><span class=\"label label-success\">APPROVED</span></h1>");
                    else if(status.equals("SUSPENDED"))
                        out.println("<h1><span class=\"label label-danger\">SUSPENDED</span></h1>");
                %>
                <br>
                <div>
                    <ul class="nav nav-tabs" role="tablist">
                      <li role="presentation" class="active">
                          <a href="#payments" aria-controls="home" role="tab" data-toggle="tab">Payments</a>
                      </li>
                      <li role="presentation">
                          <a href="#claims" aria-controls="profile" role="tab" data-toggle="tab">Claims</a>
                      </li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="payments">
                            <%
                                    User user = (User)session.getAttribute("user");
                                    out.println("<table class=\"table\" >");
                                    out.println("<tr>");
                                    out.println("<th>#</th>");
                                    out.println("<th>ID</th>");
                                    out.println("<th>Payment Type</th>");
                                    out.println("<th>Amount</th>");
                                    out.println("<th>Date</th>");
                                    out.println("</tr>");
                                    PaymentDAO payments = new PaymentDAO();
                                    List list = payments.getRecordsById(user);

                                    if(list.isEmpty()){
                                        out.println("<tr>");
                                        out.println("<td>No Records Found</td>");
                                        out.println("</tr>");
                                    }else{
                                        for(int i=0;i<list.size();i++){
                                            Payments payment = (Payments)list.get(i);
                                            out.println("<tr>");
                                            out.println("<td>" + (i+1) + "</td>");
                                            out.println("<td>" + payment.getId() + "</td>");
                                            out.println("<td>" + payment.getType() + "</td>");
                                            out.println("<td>" + payment.getAmount() + "</td>");
                                            out.println("<td>" + payment.getDate() + "</td>");
                                            out.println("</tr>");
                                        }
                                    }
                                    out.println("<tr>");
                                    out.println("<td colspan=\"5\" align=\"right\">");
                                    out.println("<button onclick=\"jQuery('#list').load('LoadPaymentList');\">");
                                    out.println("<span class=\"glyphicon glyphicon-refresh\"></span>");
                                    out.println("</button>");
                                    out.println("</tr>");
                                    out.println("</table>");
                                %>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="claims">
                            <table class="table">
                                <tr>
                                    <th>#</th>
                                    <th>Date</th>
                                    <th>Rationale</th>
                                    <th>Status</th>
                                    <th>Amount</th>
                                </tr>
                                <tr>
                                    <td colspan="5">No Record Found</td>
                                </tr>
                                <%%>
                                <tr>
                                    <td colspan="5" align="right">
                                        <button><span class="glyphicon glyphicon-refresh"></span></button>
                                        <button onclick="jQuery('#aaa').load(' #aaa');">Reload</button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="modal fade bs-modal-sm" id="payment" tabindex="-1" role="dialog" 
               aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        Payment Details
                    </div>
                    <div class="modal-body">
                        <div class="panel panel-default credit-card-box">
                            <div class="panel-heading display-table" >
                                <div class="row display-tr" >
                                    <div class="display-td" >                            
                                        <img class="img-responsive pull-right" 
                                             src="http://i76.imgup.net/accepted_c22e0.png">
                                    </div>
                                </div>                    
                            </div>
                        <div class="panel-body">
                            <form role="form" id="payment-form" 
                                  action="PaymentServlet" method="POST" >
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="form-group">
                                            <label for="cardNumber">CARD NUMBER</label>
                                            <div class="input-group">
                                                <input 
                                                    type="tel"
                                                    class="form-control"
                                                    name="cardNumber"
                                                    placeholder="Valid Card Number"
                                                    autocomplete="cc-number"
                                                    required autofocus 
                                                />
                                                <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                                            </div>
                                        </div>                            
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-7 col-md-7">
                                        <div class="form-group">
                                            <label for="cardExpiry"><span class="hidden-xs">EXPIRATION</span><span class="visible-xs-inline">EXP</span> DATE</label>
                                            <input 
                                                type="tel" 
                                                class="form-control" 
                                                name="cardExpiry"
                                                placeholder="MM / YY"
                                                autocomplete="cc-exp"
                                                required 
                                            />
                                        </div>
                                    </div>
                                    <div class="col-xs-5 col-md-5 pull-right">
                                        <div class="form-group">
                                            <label for="cardCVC">CV CODE</label>
                                            <input 
                                                type="tel" 
                                                class="form-control"
                                                name="cardCVC"
                                                placeholder="CVC"
                                                autocomplete="cc-csc"
                                                required
                                            />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="form-group">
                                            <label for="couponCode">Amount</label>
                                            <input type="tel" 
                                                   id="couponCode"
                                                   class="form-control"
                                                   name="payAmount"
                                                   placeholder="0.00"
                                                   autocomplete="amount"
                                                   required
                                            />
                                        </div>
                                    </div>                        
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <button class="subscribe btn btn-success btn-lg btn-block" type="submit">
                                            Pay
                                        </button>
                                    </div>
                                </div>
                                <div class="row" style="display:none;">
                                    <div class="col-xs-12">
                                        <p class="payment-errors"></p>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div> 
                    </div>
                </div>
            </div>
        </div>
                
        <div class="modal fade bs-modal-sm" id="claim" tabindex="-1" role="dialog" 
               aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        Request Claim
                    </div>
                    <div class="modal-body">
                        <p class="text-justify">By submitting this request, you agree to our terms and conditions under the clause "Claims".</p>
                        
                        <form action="ClaimS" method="POST">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="form-group">
                                        <label for="amount">Reason</label>
                                        <textarea class="form-control" 
                                               name="rationale"
                                               rows="4"
                                               placeholder="I would like to make claim for..."
                                               required></textarea>
                                    </div>
                                </div>                        
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="form-group">
                                        <label for="amount">Amount</label>
                                        <input type="text" 
                                               class="form-control" 
                                               name="claimAmount"
                                               placeholder="0.00"
                                               required
                                        />
                                    </div>
                                </div>                        
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <button class="subscribe btn btn-success btn-lg btn-block" type="submit">
                                        Request</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="edit" tabindex="-1" role="dialog" 
               aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        Edit Profile
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" action="" method="POST">
                            <fieldset class="form-inline">
                                <div class="control-group">
                                    <div class="col-xs-12">
                                        <div class="form-group">
                                            <label class="control-label" for="Name">First Name:</label>
                                            <% 
                                                String firstname = ((User)session.getAttribute("user")).getFirstName();
                                                out.println("<input id=\"userid\" name=\"userid\" class=\"form-control\" "
                                                        + "type=\"text\" value=" + firstname + " required>");
                                            %>
                                        
                                            <label class="control-label" for="Name">Last Name:</label>
                                            <% 
                                                String lastname = ((User)session.getAttribute("user")).getLastName();
                                                out.println("<input id=\"userid\" name=\"userid\" class=\"form-control\" "
                                                        + "type=\"text\" value=" + lastname + " required>");
                                            %>
                                        </div>
                                    </div>
                                </div>
                                        </fieldset>
                                        <fieldset>
                            
                            <div class="control-group">
                                <div class="col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label" for="Address">Address:</label>
                                        <% 
                                            String address = ((User)session.getAttribute("user")).getAddress();
                                            out.println("<textarea id=\"address\" name=\"address\" "
                                                    + "class=\"form-control\" rows=\"3\" "
                                                    + "required>" + address + "</textarea>");
                                        %>
                                    </div>
                                </div>                        
                            </div>
                            <div class="control-group">
                                <div class="col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label" for="dob">Date of Birth:</label>
                                        <% 
                                            String dob = ((User)session.getAttribute("user")).getDOB();
                                            out.println("<input id=\"dob\" name=\"dob\" class=\"form-control\""+
                                                    "type=\"date\" value=\"" + dob + "\" required>");
                                        %>
                                    </div>
                                </div>                        
                            </div>
                            <div class="control-group">
                                <div class="col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label" for="password">Password:</label>
                                        <input id="password" name="password" class="form-control" type="password" 
                                               placeholder="********" class="input-large" required>
                                        <em>1-8 Characters</em>
                                    </div>
                                </div>                        
                            </div>
                            <div class="control-group">
                                <div class="col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label" for="reenterpassword">Re-Enter Password:</label>
                                        <input id="reenterpassword" class="form-control" name="reenterpassword" 
                                               type="password" placeholder="********" required>
                                    </div>
                                </div>                        
                            </div>
                                    </fieldset>
                            <!-- Button -->
                            <div class="control-group">
                                <label class="control-label" for="confirmsignup"></label>
                                <div class="controls">
                                    <button id="confirmsignup" name="confirmsignup" class="btn btn-success btn-block">Confirm</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <%@include file="/lib/footer.html" %>
</html>
