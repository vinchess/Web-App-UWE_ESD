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
        <%@ page import="user.Payments" %>
    </head>
    <body>
        <div class="container">
            <div class="col-md-3 text-center">
                <img src="assets/user_icon.png">
                    <h2><%= session.getAttribute("username1") %></h2>
                    <h3>Balance</h3>
                    <h3>0.00</h3>
                    <button type="button" class="btn btn-default btn-block" 
                            data-toggle="modal" data-target="#payment" aria-label="Left Align">
                        <div class="col-md-1">
                            <span class="glyphicon glyphicon-gbp"></span>
                        </div>
                        <div class="col-md-2 ">
                            PAY
                        </div>
                    </button>
                    <button type="button" class="btn btn-default btn-block" 
                            data-toggle="modal" data-target="#claim" aria-label="Left Align">
                        <div class="col-md-1">
                            <span class="glyphicon glyphicon-pencil"></span> 
                        </div>
                        <div class="col-md-2 ">
                            CLAIM
                        </div>
                    </button>
                    <button type="button" class="btn btn-default btn-block" 
                            data-toggle="modal" data-target="#claim" aria-label="Left Align">
                        <div class="col-md-1">
                            <span class="glyphicon glyphicon-cog"></span> 
                        </div>
                        <div class="col-md-2 ">
                            EDIT ACCOUNT
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
                <h1 style="color:green"><% out.println("hahahaha"); %>ACTIVE</h1>/
                <h1 style="color:yellow"><%//get account status here%>PENDING</h1>/
                <h1 style="color:red"><%//get account status here%>INACTIVE</h1>
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
                            <table class="table">
                                <tr>
                                    <th>#</th>
                                    <th>ID</th>
                                    <th>Payment Type</th>
                                    <th>Amount</th>
                                    <th>Date</th>
                                </tr>
                            </table>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="claims">
                            No Records Found
                            <%//get records%>
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
                            <img class="img-responsive pull-right" src="http://i76.imgup.net/accepted_c22e0.png">
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
                                    <input type="text" 
                                           id="couponCode"
                                           class="form-control"
                                           name="payAmount"
                                           placeholder="0.00"
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
                        
                        <form>
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
                                    <button class="subscribe btn btn-success btn-lg btn-block" type="button">
                                        Request</button>
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
