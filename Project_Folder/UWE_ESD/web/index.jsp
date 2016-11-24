<%-- 
    Document   : index
    Created on : Nov 6, 2016, 5:45:11 PM
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
        
        <%@include file="/lib/bootstrap.html" %>
        <%@include file="/lib/banner.html" %>
        <%!Cookie[] cookies;%>
        <%cookies = request.getCookies();%>
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
                session.setAttribute("success", null);
            %>
                
            <div class="jumbotron">
                <h1>XYZ Drivers Associations</h1>
                <h3>Welcome to your personal claims portal.<br>
                    For all your minor claim needs.<br>
                    To proceed please login.</h3>
                <button class="btn btn-primary " href="#signin" 
                    data-toggle="modal" data-target="#myModal">Sign In/Register</button>
                <button class="btn btn-primary " href="#admin"
                        data-toggle="modal" data-target="#myAdmin">Admin</button>
            </div>
        </div>
        
        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
             aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="bs-example bs-example-tabs">
                        <ul id="myTab" class="nav nav-tabs">
                            <li class="active"><a href="#signin" data-toggle="tab">Sign In</a></li>
                            <li class=""><a href="#signup" data-toggle="tab">Register</a></li>
                        </ul>
                    </div>
                    <div class="modal-body">
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade active in" id="signin">
                                <form class="form-horizontal" action="dashboard" method="POST">
                                    <fieldset>
                                        <div class="form-group focus">
                                            <label class="col-sm-4 control-label" for="userid">Username:</label>
                                            <div class="col-sm-8 controls">
                                                <input id="userid" 
                                                       name="userid" 
                                                       type="text" 
                                                       class="form-control focus" 
                                                       placeholder="username"
                                                       <%
                                                           for(Cookie c:cookies){
                                                               if (c.getName().equals("userid")) {
                                                                    out.println("value=\"" + c.getValue() + "\"");
                                                                }
                                                           } 
                                                       %>
                                                       required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="passwordinput">Password:</label>
                                            <div class="col-sm-8 controls">
                                              <input id="passwordinput" 
                                                     name="passwordinput" 
                                                     class="form-control" 
                                                     type="password" 
                                                     placeholder="********" 
                                                     required>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="signin"></label>
                                            <div class="controls">
                                                <button id="signin" name="signin" class="btn btn-success btn-block">Sign In</button>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="tab-pane fade" id="signup">
                                <form class="form-horizontal" action="RegistrationServlet" method="POST">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="firstname">First Name:</label>
                                            <div class="col-sm-9 controls">
                                                <input id="firstname" 
                                                       name="firstname" 
                                                       class="form-control" 
                                                       type="text" 
                                                       <%
                                                           for(Cookie c:cookies){
                                                               if (c.getName().equals("firstname")) {
                                                                    out.println("value=\"" + c.getValue() + "\"");
                                                                }
                                                           } 
                                                       %>
                                                       placeholder="John" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="lastname">Last Name:</label>
                                            <div class="col-sm-9 controls">
                                                <input id="lastname" 
                                                       name="lastname" 
                                                       class="form-control" 
                                                       type="text" 
                                                       <%
                                                           for(Cookie c:cookies){
                                                               if (c.getName().equals("lastname")) {
                                                                    out.println("value=\"" + c.getValue() + "\"");
                                                                }
                                                           } 
                                                       %>
                                                       placeholder="Doe" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                          <label class="col-sm-3 control-label" for="address">Address:</label>
                                          <div class="col-sm-9 controls">
                                                <textarea id="address" 
                                                          name="address" 
                                                          class="form-control" rows="3" 
                                                          placeholder="Address Here" 
                                                          required><%
                                                        for(Cookie c:cookies){
                                                            if (c.getName().equals("address")) {
                                                                 out.println(c.getValue());
                                                             }
                                                        } 
                                                    %></textarea>
                                          </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="dob">Date of Birth:</label>
                                            <div class="col-sm-9">
                                                <input id="dob" name="dob" 
                                                       class="form-control" 
                                                       type="date"
                                                       max="2016-01-01"
                                                       <%
                                                           for(Cookie c:cookies){
                                                               if (c.getName().equals("dob")) {
                                                                    out.println("value=\"" + c.getValue() + "\"");
                                                                }
                                                           } 
                                                       %>
                                                       required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="password">Password:</label>
                                            <div class="col-sm-9">
                                                <input id="password" name="password" class="form-control" type="password" 
                                                       placeholder="********" class="input-large" required="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="reenterpassword">Re-Enter Password:</label>
                                            <div class="col-sm-9">
                                                <input id="reenterpassword" class="form-control" name="reenterpassword" 
                                                       type="password" placeholder="********" class="input-large" required="">
                                            </div>
                                        </div>
                                        <!-- Button -->
                                        <div class="control-group">
                                            <label class="control-label" for="confirmsignup"></label>
                                            <div class="controls">
                                                <button id="confirmsignup" name="confirmsignup" class="btn btn-success btn-block" type="submit">Sign Up</button>
                                            </div>
                                        </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <center>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </center>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="myAdmin" tabindex="-1" role="dialog" 
               aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        Sign In
                    </div>
                    <div class="modal-body">
                        <div class="tab-pane fade active in" id="admin">
                            <form class="form-horizontal" action="admin-dashboard" method="POST">
                                <fieldset>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label" for="userid">Username:</label>
                                        <div class="col-sm-8 controls">
                                            <input id="userid" name="userid" type="text" class="form-control" 
                                                 placeholder="username" class="input-medium" focused required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label" for="passwordinput">Password:</label>
                                        <div class="col-sm-8 controls">
                                            <input required="" id="passwordinput" name="passwordinput" class="form-control" type="password" placeholder="********" class="input-medium">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="signin"></label>
                                        <div class="controls">
                                            <center>
                                                <button id="signin" name="signin" class="btn btn-success">Sign In</button>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </center>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <%@include file="/lib/footer.html" %>
</html>
