<%-- 
    Document   : dashboard
    Created on : Nov 2, 2016, 1:35:02 AM
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="user.User" %>
<%@ page import="user.Claim" %>
<%! String applied = null,approved = null,suspended = null, submitted = null,accepted = null,rejected = null ;%>
<%! boolean homeTab,usersTab,claimsTab,searchTab;%>
<%! List userClaims; %>
<%! List userList; %>
<%! User user;%>
<% user = (User)session.getAttribute("user");%>
<% userList = (ArrayList)session.getAttribute("userlist");%>
<% userClaims = (ArrayList)session.getAttribute("claimlist");%>
<% applied = (String)session.getAttribute("applied");%>
<% approved = (String)session.getAttribute("approved");%>
<% suspended = (String)session.getAttribute("suspended");%>
<% submitted = (String)session.getAttribute("submitted");%>
<% accepted = (String)session.getAttribute("accepted");%>
<% rejected = (String)session.getAttribute("rejected");%>
<% homeTab = (boolean)session.getAttribute("home");%>
<% usersTab = (boolean)session.getAttribute("users");%>
<% claimsTab = (boolean)session.getAttribute("claims");%>
<% searchTab = (boolean)session.getAttribute("search");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Admin Dashboard</title>
        <%@include file="/lib/bootstrap.html" %>
        <%@include file="/lib/banner.html" %>
        
        
    </head>
    <body>
        <div class="container">
            <!-- Display appropriate message to user -->
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
                session.removeAttribute("error");

                String success = (String)session.getAttribute("success");
                if(success!=null){
                    out.println("<div class=\"alert alert-success\" role=\"alert\">");
                    out.println(success);
                    out.println("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">");
                    out.println("<span aria-hidden=\"true\">&times;</span>");
                    out.println("</button>");
                    out.println("</div>");
                }
                session.removeAttribute("success");
            %>
            <div class="col-md-3 ">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" 
                               accesskey=""aria-expanded="true" aria-controls="collapseOne">
                                Users Filter
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <form action="filter" method="POST">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="applied" value="APPLIED" 
                                               <%if(applied!=null) { if(applied.equals("APPLIED")) { out.println("checked"); } }%>>
                                        APPLIED
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="approved" value="APPROVED" 
                                               <%if(approved!=null) { if(approved.equals("APPROVED")) { out.println("checked"); } }%>>
                                        APPROVED
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="suspended" value="SUSPENDED" 
                                               <%if(suspended!=null) { if(suspended.equals("SUSPENDED")) { out.println("checked"); } }%>>
                                        SUSPENDED
                                    </label>
                                </div>
                                <input type="submit" class="btn btn-success btn-block" data-toggle="modal" 
                                       data-target="#dimmer" value="Update"/>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="claimsFilterHeading">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#claimsFilterCollapse" 
                               accesskey=""aria-expanded="true" aria-controls="claimsFilterCollapse">
                                Claims Filter
                            </a>
                        </h4>
                    </div>
                    <div id="claimsFilterCollapse" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="claimsFilterHeading">
                        <div class="panel-body">
                            <form action="claimfilter" method="POST">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="submitted" value="SUBMITTED" 
                                               <%if(submitted!=null) { if(submitted.equals("SUBMITTED")) { out.println("checked"); } }%>>
                                        SUBMITTED
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="accepted" value="ACCEPTED" 
                                               <%if(accepted!=null) { if(accepted.equals("ACCEPTED")) { out.println("checked"); } }%>>
                                        ACCEPTED
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="rejected" value="REJECTED" 
                                               <%if(rejected!=null) { if(rejected.equals("REJECTED")) { out.println("checked"); } }%>>
                                        REJECTED
                                    </label>
                                </div>
                                <input type="submit" class="btn btn-success btn-block" data-toggle="modal" 
                                       data-target="#dimmer" value="Update"/>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" 
                               accesskey=""aria-expanded="true" aria-controls="collapseTwo">
                                Search Filter
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <form class="form-horizontal" action="searchfilter" method="POST">
                                <div class="form-group">
                                    <div class="col-sm-9">
                                        <input class="form-control" type="text" id="userid" name="userid" placeholder="User ID" required>
                                    </div>
                                        <button type="submit" class="btn btn-default btn-control" data-toggle="modal" data-target="#dimmer" >
                                            <center><span class="glyphicon glyphicon-search" /></center>
                                        </button>
                               
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <button type="button" class="btn btn-default btn-block" 
                        data-toggle="modal" data-target="#dimmer"
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
                <div>
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" <%if(homeTab) { out.println("class=\"active\""); }%> >
                            <a href="#home" aria-controls="home" role="tab" data-toggle="tab">Details</a>
                        </li>
                        <li role="presentation" <%if(usersTab) { out.println("class=\"active\""); }%>>
                            <a href="#users" aria-controls="users" role="tab" data-toggle="tab">Users</a>
                        </li>
                        <li role="presentation" <%if(claimsTab) { out.println("class=\"active\""); }%>>
                            <a href="#claims" aria-controls="claims" role="tab" data-toggle="tab">Claims</a>
                        </li>
                        <li role="presentation" <%if(searchTab) { out.println("class=\"active\""); }%>>
                            <a href="#search" aria-controls="search" role="tab" data-toggle="tab">Search</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane 
                             <%if(homeTab) { out.println("active"); }%>" id="home">
                            <br>
                            <div class="col-md-6">
                                <center>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Total Members</h3>
                                    </div>
                                    <div class="panel-body">
                                        <%
                                            out.println("<h1 style=\"font-size:70px;\">"+userList.size()+"</h1>");
                                                            
                                            int apply = 0,approve = 0,suspend = 0;
                                            for(Object e:userList){
                                                switch(((User)e).isUserValid()){
                                                    case "APPLIED":
                                                        apply++;break;
                                                    case "APPROVED":
                                                        approve++;break;
                                                    case "SUSPENDED":
                                                        suspend++;break;
                                                }
                                            }
                                        %>
                                        <div class="col-md-9 text-left">
                                            <%
                                                out.println("<h3 class=\"text-warning\">APPLIED</h3>");//APPLIED
                                                out.println("<h3 class=\"text-success\">APPROVED</h3>");//APPROVED
                                                out.println("<h3 class=\"text-danger\">SUSPENDED</h3>");//SUSPENDED
                                            %>
                                        </div>
                                        <div class="col-md-3">
                                            <%
                                                out.println("<h3 class=\"text-warning\">"+apply+"</h3>");//APPLIED
                                                out.println("<h3 class=\"text-success\">"+approve+"</h3>");//APPROVED
                                                out.println("<h3 class=\"text-danger\">"+suspend+"</h3>");//SUSPENDED
                                            %>
                                        </div>
                                    </div>
                                </div>
                                </center>
                            </div>
                            <div class="col-md-6">
                                <center>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Total Claims</h3>
                                    </div>
                                    <div class="panel-body">
                                        <%
                                            double totalClaims = 0;
                                            int size = 0;

                                            for(Object uc:userClaims){
                                                if(((Claim)uc).getStatus().equals("ACCEPTED"))
                                                    totalClaims += ((Claim)uc).getAmount();
                                            }
                                            size = apply + approve + suspend;
                                            session.setAttribute("amount", String.format("%.2f",(totalClaims/size)+10));

                                            out.println("<h1>&#163;"+String.format("%.2f", totalClaims)+"</h1>");
                                            out.println("<h3>Average : &#163; "+String.format("%.2f", totalClaims/size)+"</h3>");
                                            out.println("<h3>Fees : &#163; 10.00</h3>");
                                        %>
                                        <form action="charge-members" method="POST">
                                            <button class="btn btn-primary btn-block">Charge Membership</button>
                                        </form>
                                    </div>
                                </div>
                                </center>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane 
                             <%if(usersTab) { out.println("active"); }%>" id="users">
                            <br>
                            <table class="table" >
                                <tr>
                                    <th>#</th><th>ID</th><th>Name</th><th>Address</th><th>DOB</th><th>DOR</th><th>Status</th><th>Balance</th><th></th>
                                </tr>
                            <%

                                List filter = new ArrayList();
                                
                                for(Object u:userList){
                                    if(applied!=null) { if((((User)u).isUserValid()).equals(applied)) { filter.add(u); } }
                                    if(approved!=null) { if((((User)u).isUserValid()).equals(approved)) { filter.add(u); } }
                                    if(suspended!=null) { if((((User)u).isUserValid()).equals(suspended)) { filter.add(u); } }
                                }
                                if(applied!=null || approved!=null || suspended!=null) { userList = filter; }

                                if(userList.isEmpty()){
                                    out.println("<tr>");
                                    out.println("<td colspan=\"9\">No Records Found</td>");
                                    out.println("</tr>");
                                }else{
                                    for(int i=0;i<userList.size();i++){
                                        User member = (User)userList.get(i);
                                        
                                        switch(member.isUserValid()){
                                            case "APPLIED":
                                                out.println("<tr class=\"warning\">");break;
                                            case "APPROVED":
                                                out.println("<tr class=\"success\">");break;
                                            case "SUSPENDED":
                                                out.println("<tr class=\"danger\">");break;
                                            default :
                                                out.println("<tr class=\"active text-muted\">");
                                        }
                                        out.println("<td>" + (i+1) + "</td>");
                                        out.println("<td>" + member.getID() + "</td>");
                                        out.println("<td>" + member.getFirstName() + " " + member.getLastName() + "</td>");
                                        out.println("<td>" + member.getAddress() + "</td>");
                                        out.println("<td>" + member.getDOB() + "</td>");
                                        out.println("<td>" + member.getDOR() + "</td>");
                                        out.println("<td>" + member.isUserValid()+ "</td>");
                                        out.println("<td>&#163; " + member.getBalance() + "</td>");
                                        out.println("<td>");
                                        out.println("<form action=\"update-status\" method=\"POST\">");
                                        if(member.isUserValid().equals("APPROVED")){
                                            out.println("<button class=\"btn btn-default btn-sm\" type=\"submit\" "
                                                    + "data-toggle=\"modal\" data-target=\"#dimmer\""
                                                    + "name=\"suspend\" value=\""+member.getID()+"\">SUSPEND</button>");
                                        }else if(member.isUserValid().equals("DELETED")){
                                            out.println("");
                                        }else{
                                            out.println("<button class=\"btn btn-default btn-sm\" type=\"submit\" "
                                                    + "data-toggle=\"modal\" data-target=\"#dimmer\""
                                                    + "name=\"approve\" value=\""+member.getID()+"\">APPROVE</button>");
                                        }
                                        out.println("</form>");
                                        out.println("</td>");
                                        out.println("</tr>");
                                    }
                                }
                                out.println("</table>");
                            %>
                            </table>
                        </div>
                        <div role="tabpanel" class="tab-pane 
                             <%if(claimsTab) { out.println("active"); }%>" id="claims">
                            <br>
                            <table class="table" >
                                <tr>
                                    <th>#</th><th>ID</th><th>Date</th><th>Rationale</th><th>Status</th><th>Amount</th><th>
                                </tr>
                            <%
                                    
                                    List claimfilter = new ArrayList();
                                
                                    for(Object c:userClaims){
                                        if(submitted!=null) { if((((Claim)c).getStatus()).equals(submitted)) { claimfilter.add(c); } }
                                        if(accepted!=null) { if((((Claim)c).getStatus()).equals(accepted)) { claimfilter.add(c); } }
                                        if(rejected!=null) { if((((Claim)c).getStatus()).equals(rejected)) { claimfilter.add(c); } }
                                    }
                                    if(submitted!=null || accepted!=null || rejected!=null) { userClaims = claimfilter; }
                                        
                                    if(userClaims.isEmpty()){
                                        out.println("<tr>");
                                        out.println("<td colspan=\"7\">No Records Found</td>");
                                        out.println("</tr>");
                                    }else{
                                        for(int i=0;i<userClaims.size();i++){
                                            Claim claim = (Claim)userClaims.get(i);
                                            String disabled = "";

                                            switch(claim.getStatus()){
                                                case "SUBMITTED":
                                                    out.println("<tr class=\"warning\">");break;
                                                case "ACCEPTED":
                                                    out.println("<tr class=\"success\">");break;
                                                case "REJECTED":
                                                    out.println("<tr class=\"danger\">");break;
                                                default :
                                                    out.println("<tr class=\"active text-muted\">");
                                            }

                                            out.println("<td>" + (i+1) + "</td>");
                                            out.println("<td>" + claim.getMem_id() + "</td>");
                                            out.println("<td>" + claim.getDate() + "</td>");
                                            out.println("<td>" + claim.getRationale() + "</td>");
                                            out.println("<td>" + claim.getStatus() + "</td>");
                                            out.println("<td>&#163; " + claim.getAmount() + "</td>");
                                            out.println("<td>");
                                            out.println("<form action=\"update-claims\" method=\"POST\">");
                                            if(!claim.getStatus().equals("SUBMITTED")){
                                                disabled = "disabled";
                                            }
                                            out.println("<div class=\"pull-right\">");
                                            out.println("<button class=\"btn btn-success btn-sm btn-circle "+disabled+"\" type=\"submit\" "
                                                    + "data-toggle=\"modal\" data-target=\"#dimmer\" "
                                                    + "name=\"accepted\" value=\""+claim.getId()+"\" "+disabled+">");
                                            out.println("<span class=\"glyphicon glyphicon-ok\"></span>");
                                            out.println("</button>");

                                            out.println("<button class=\"btn btn-danger btn-sm btn-circle "+disabled+"\" type=\"submit\" "
                                                    + "data-toggle=\"modal\" data-target=\"#dimmer\" "
                                                    + "name=\"rejected\" value=\""+claim.getId()+"\" "+disabled+">");
                                            out.println("<span class=\"glyphicon glyphicon-remove\"></span>");
                                            out.println("</button>");
                                            out.println("</div>");
                                            out.println("</form>");
                                            out.println("<td>");
                                            out.println("</td>");
                                            out.println("</tr>");
                                        }
                                    }
                                %>
                            </table>
                        </div>
                        <div role="tabpanel" class="tab-pane 
                             <%if(searchTab) { out.println("active"); }%>" id="search">
                            <br>
                            <table >
                                <%
                                
                                    if(user==null){
                                        out.println("<tr>");
                                        out.println("<td>No Records Found</td>");
                                        out.println("</tr>");
                                    }else{
                                        if(user.isUserValid().equals("DELETED"))
                                            out.println("<tr class=\"text-muted\">"); 
                                        else
                                            out.println("<tr>");
                                        out.println("<td class=\"col-md-2\">");
                                        out.println("<center><img src=\"/UWE_ESD/assets/user_icon.png\" width=\"100px\"></center>");
                                        out.println("</td>");
                                        out.println("<td class=\"col-md-7\">");
                                        switch(user.isUserValid()){
                                            case "APPLIED":
                                                out.println("<h5><span class=\"label label-warning\">APPLIED</span></h5>");break;
                                            case "APPROVED":
                                                out.println("<h5><span class=\"label label-success\">APPROVED</span></h5>");break;
                                            case "SUSPENDED":
                                                out.println("<h5><span class=\"label label-danger\">SUSPENDED</span></h5>");break;
                                            case "DELETED":
                                                out.println("<h5><span class=\"label label-default\">DELETED</span></h5>");break;
                                        }
                                        out.println("<h4>"+user.getFirstName()+" "+user.getLastName()+"</h4>");
                                        out.println("<h4>&#163; "+user.getBalance()+"</h4>");
                                        out.println("</td>");
                                        out.println("<td class=\"col-md-3\">");
                                        if(!user.isUserValid().equals("DELETED")){
                                            out.println("<button class=\"btn btn-default btn-block\" "
                                                + "data-toggle=\"modal\" data-target=\"#dimmer\" "
                                                + "onClick=\"location.href='charge-members'\" >Distributed Charges</button>");
                                            out.println("<button class=\"btn btn-default btn-block\" "
                                                + "data-toggle=\"modal\" data-target=\"#dimmer\" "
                                                + "onClick=\"location.href='charge-members'\" >&#163; 10 Fees</button>");
                                        }
                                        out.println("</td>");
                                    }
                                %>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Dimmer-->
        <div class="modal fade" id="dimmer" tabindex="-1" role="dialog" 
               aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog"></div>
        </div>
    </body>
    <%@include file="/lib/footer.html" %>

</html>
