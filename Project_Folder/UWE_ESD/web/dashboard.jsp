<%-- 
    Document   : dashboard
    Created on : Nov 7, 2016, 10:19:38 PM
    Author     : Vincent
--%>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="user.Payments" %>
<%@ page import="user.User" %>
<%@ page import="user.Claim"%>
<%@ page import="database.ClaimDAO"%>
<%@ page import="database.PaymentDAO" %>
<%@ page import="database.MemberDAO" %>
<%! User user;%>
<% user = (User)session.getAttribute("user");%>
<%! PaymentDAO payments = new PaymentDAO();%>
<%! ClaimDAO claims = new ClaimDAO();%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <%@include file="/lib/bootstrap.html" %><!--call in bootstrap imports html-->
        <%@include file="/lib/banner.html" %><!--call in banner html-->   
    </head>
    <body>
        <div class="container">
            <%
                String error = (String)session.getAttribute("error");
                if(error!=null){
                    out.println("<div class=\"alert alert-danger\" role=\"alert\">");
                    out.println(error);
                    out.println("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">");
                    out.println("<span aria-hidden=\"true\">&times;</span>");
                    out.println("</button>");
                    out.println("</div>");
                }
                session.setAttribute("error", null);

                String success = (String)session.getAttribute("success");
                if(success!=null){
                    out.println("<div class=\"alert alert-success\" role=\"alert\">");
                    out.println(success);
                    out.println("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">");
                    out.println("<span aria-hidden=\"true\">&times;</span>");
                    out.println("</button>");
                    out.println("</div>");
                }
                session.setAttribute("success", null);
            %>
            <div class="col-md-3 text-center">
                <img src="/UWE_ESD/assets/user_icon.png" height="150px">
                    <h2><%=user.getFirstName()+" "+user.getLastName()%></h2>
                    <h3>Balance &#163;<%=String.format("%.2f",Double.parseDouble((new MemberDAO().getColumn(user.getID(),"balance"))))%></h3>
                    <button type="button" class="btn btn-primary btn-block" 
                            data-toggle="modal" data-target="#payment" aria-label="Left Align">
                        <div class="col-md-1">
                            <span class="glyphicon glyphicon-gbp"></span>
                        </div>
                        <div class="col-md-2 ">
                            PAY
                        </div>
                    </button>
                    <button type="button" class="btn btn-primary btn-block <%if(user.isUserValid().equals("SUSPENDED"))out.println("disabled");%>" 
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
                            data-toggle="modal" data-target="#delete" aria-label="Left Align">
                        <div class="col-md-1">
                            <span class="glyphicon glyphicon-remove"></span> 
                        </div>
                        <div class="col-md-2 ">
                            DELETE ACCOUNT
                        </div>
                    </button>
                    <button type="button" class="btn btn-default btn-block" 
                            data-toggle="modal" data-target="#logout"
                            aria-label="Left Align" onClick="location.href='/UWE_ESD/LogoutServlet'">
                        
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
                    String status =user.isUserValid();
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
                                out.println("<table class=\"table\" >");
                                out.println("<tr>");
                                out.println("<th>#</th>");
                                out.println("<th>ID</th>");
                                out.println("<th>Payment Type</th>");
                                out.println("<th>Amount</th>");
                                out.println("<th>Date</th>");
                                out.println("</tr>");
                                try{
                                    List list = payments.getRecordsById(user);
                                    if(list.isEmpty()){
                                        out.println("<tr>");
                                        out.println("<td colspan=\"5\">No Records Found</td>");
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
                                }catch(SQLException se){
                                    out.println("<tr>");
                                    out.println("<td>Database connection error.</td>");
                                    out.println("</tr>");
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
                            <%
                                    out.println("<table class=\"table\" >");
                                    out.println("<tr>");
                                    out.println("<th>#</th>");
                                    out.println("<th>ID</th>");
                                    out.println("<th>Date</th>");
                                    out.println("<th>Rational</th>");
                                    out.println("<th>Status</th>");
                                    out.println("<th>Amount</th>");
                                    out.println("</tr>");
                                    try{
                                        List claimList = claims.getClaimsById(user);
                                        if(claimList.isEmpty()){
                                            out.println("<tr>");
                                            out.println("<td colspan=\"6\">No Records Found</td>");
                                            out.println("</tr>");
                                        }else{
                                            for(int i=0;i<claimList.size();i++){
                                                Claim claim = (Claim)claimList.get(i);
                                                out.println("<tr>");
                                                out.println("<td>" + (i+1) + "</td>");
                                                out.println("<td>" + claim.getMem_id()+ "</td>");
                                                out.println("<td>" + claim.getDate()+ "</td>");
                                                out.println("<td>" + claim.getRationale() + "</td>");
                                                out.println("<td>" + claim.getStatus() + "</td>");
                                                out.println("<td>" + claim.getAmount() + "</td>");
                                                out.println("</tr>");
                                            }
                                        }
                                    }catch(SQLException se){
                                        out.println("<tr>");
                                        out.println("<td colspan=\"6\">Database connection error.</td>");
                                        out.println("</tr>");
                                    }
                                    
                                    out.println("<tr>");
                                    out.println("<td colspan=\"6\" align=\"right\">");
                                    out.println("<button onclick=\"jQuery('#list').load('LoadPaymentList');\">");
                                    out.println("<span class=\"glyphicon glyphicon-refresh\"></span>");
                                    out.println("</button>");
                                    out.println("</tr>");
                                    out.println("</table>");
                                %>
                        </div>
                    </div>
                </div>
            </div>
        </div>       
        <!--Payment Modal-->
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
                                  action="dashboard/payment" method="POST" >
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
                                            <label for="cardExpiry">
                                                <span class="hidden-xs">EXPIRATION</span>
                                                <span class="visible-xs-inline">EXP</span> DATE
                                            </label>
                                            <input 
                                                type="tel" 
                                                class="form-control" 
                                                name="cardExpiry"
                                                placeholder="MM / YY"
                                                autocomplete="cc-exp"
                                                required />
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
                                                required />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="form-group">
                                            <label for="couponCode">Amount</label>
                                            <input type="number" 
                                                   id="couponCode"
                                                   class="form-control"
                                                   name="payAmount"
                                                   min="0.01"
                                                   placeholder="0.00"
                                                   autocomplete="amount"
                                                   required />
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
        <!--Claim Modal-->
        <div class="modal fade bs-modal-sm" id="claim" tabindex="-1" role="dialog" 
               aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        Request Claim
                    </div>
                    <div class="modal-body">
                        <p class="text-justify">By submitting this request, you agree to our terms and conditions under the clause "Claims".</p>
                        
                        <form action="dashboard/claim" method="POST">
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
                                        <input type="number" 
                                               min="0.01"
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
        <!--Edit Profile Modal-->
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
                                                out.println("<input id=\"userid\" name=\"userid\" class=\"form-control\" "
                                                        + "type=\"text\" value=" + user.getFirstName() + " required>");
                                            %>
                                        
                                            <label class="control-label" for="Name">Last Name:</label>
                                            <% 
                                                out.println("<input id=\"userid\" name=\"userid\" class=\"form-control\" "
                                                        + "type=\"text\" value=" + user.getLastName() + " required>");
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
                                                out.println("<textarea id=\"address\" name=\"address\" "
                                                        + "class=\"form-control\" rows=\"3\" "
                                                        + "required>" + user.getAddress() + "</textarea>");
                                            %>
                                        </div>
                                    </div>                        
                                </div>
                                <div class="control-group">
                                    <div class="col-xs-12">
                                        <div class="form-group">
                                            <label class="control-label" for="dob">Date of Birth:</label>
                                            <% 
                                                out.println("<input id=\"dob\" name=\"dob\" class=\"form-control\" max=\"2016-01-01\" "+
                                                        "type=\"date\" value=\"" + user.getDOB() + "\" required>");
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
        <!--Delete Modal-->
        <div class="modal fade bs-modal-sm" id="delete" tabindex="-1" role="dialog" 
               aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        Delete Account
                    </div>
                    <div class="modal-body">
                        <p class="text-justify">Are you sure you want to delete your membership? To confirm, type in your full name.</p>
                        
                        <form action="#" method="POST">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="form-group">
                                        <label for="amount">Full Name</label>
                                        <input type="text" 
                                               class="form-control" 
                                               name="fullname"
                                               placeholder="Your Name"
                                               required />
                                    </div>
                                </div>                        
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <button class="subscribe btn btn-danger btn-lg btn-block" type="submit">
                                        Delete</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>                            
        <!--Logout Dimmer-->
        <div class="modal fade" id="logout" tabindex="-1" role="dialog" 
               aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog"></div>
        </div>
    </body>
    <%@include file="/lib/footer.html" %>
</html>
