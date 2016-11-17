<%-- 
    Document   : dashboard
    Created on : Nov 2, 2016, 1:35:02 AM
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Admin Dashboard</title>
        <%@include file="/lib/bootstrap.html" %>
        <%@include file="/lib/banner.html" %>
        <%@ page import="java.util.*" %>
        <%@ page import="user.Payments" %>
    </head>
    <body>
        <div class="container">
            <div class="col-md-3 ">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" 
                               accesskey=""aria-expanded="true" aria-controls="collapseOne">
                                Filter
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <form action="#" method="POST"><!--need servlet to update the list-->
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value="">
                                        Active
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value="">
                                        Pending
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value="">
                                        Inactive
                                    </label>
                                </div>
                                <input type="submit" class="btn btn-success btn-block" value="Update"/>
                            </form>
                        </div>
                    </div>
                </div>
                <button type="button" class="btn btn-default btn-block" 
                        aria-label="Left Align" onClick="location.href='./LogoutServlet'">
                    <div class="col-md-1">
                        <span class="glyphicon glyphicon-off"></span> 
                    </div>
                    <div class="col-md-2 ">
                        LOG OUT
                    </div>
                </button>
            </div>
            <div class="col-md-9 ">
                <div>
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#payments" aria-controls="home" role="tab" data-toggle="tab">Users</a>
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
                                <%
                                    List list = (ArrayList)session.getAttribute("paymentlist");
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
                                %>
                            </table>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="claims">
                            <table class="table">
                                <tr>
                                    <th>#</th>
                                    <th>ID</th>
                                    <th>Date</th>
                                    <th>Rationale</th>
                                    <th>Status</th>
                                    <th>Amount</th>
                                </tr>
                                <%
                                    
                                        out.println("<tr>");
                                        out.println("<td rowspan=\"6\">No Records Found</td>");
                                        out.println("</tr>");
                                    
                                %>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <%@include file="/lib/footer.html" %>

</html>
