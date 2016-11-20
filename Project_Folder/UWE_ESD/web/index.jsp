<%-- 
    Document   : index
    Created on : Nov 6, 2016, 5:45:11 PM
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
        
        <%@include file="/lib/bootstrap.html" %>
        <%@include file="/lib/banner.html" %>
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
                
                <center>
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
                </center>
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
                            <li class=""><a href="#why" data-toggle="tab">Why?</a></li>
                        </ul>
                    </div>
                    <div class="modal-body">
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in" id="why">
                                <p>We need this information so that you can receive access to the site and its content.</p>
                                <p></p><br> Please email <a mailto:href="johndoe@example.com">johndoe@example.com</a> for any other inquiries.</p>
                            </div>
                            <div class="tab-pane fade active in" id="signin">
                                <form class="form-horizontal" action="logins" method="POST">
                                    <fieldset>
                                        <div class="control-group focus">
                                            <label class="control-label" for="userid">Username:</label>
                                            <div class="controls">
                                                <input id="userid" 
                                                       name="userid" 
                                                       type="text" 
                                                       class="form-control focus" 
                                                       placeholder="username"
                                                       required>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="passwordinput">Password:</label>
                                            <div class="controls">
                                              <input id="passwordinput" 
                                                     name="passwordinput" 
                                                     class="form-control" 
                                                     type="password" 
                                                     placeholder="********" 
                                                     required>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="control-group">
                                            <div class="controls">
                                                <label>
                                                    <input type="checkbox" aria-label="Remember me">Remember me
                                                </label>
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
                                <form action="RegistrationServlet" method="POST">
                                    <fieldset class="form-inline">
                                        <div class="control-group">
                                            <div class="controls">
                                          <label class="control-label" for="firstname">First Name:</label>
                                            <input id="firstname" name="firstname" class="form-control" type="text" 
                                                   placeholder="John" required>
                                         
                                          <label class="control-label" for="lastname">Last Name:</label>
                                          
                                            <input id="lastname" name="lastname" class="form-control" type="text" 
                                                   placeholder="Doe" required>
                                            </div>
                                        </div>
                                        </fieldset>
                                        <fieldset>
                                        <div class="control-group">
                                          <label class="control-label" for="address">Address:</label>
                                          <div class="controls">
                                              <textarea id="address" name="address" class="form-control" rows="3" 
                                                      placeholder="Address Here" required></textarea>
                                          </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="dob">Date of Birth:</label>
                                            <div class="controls">
                                                <input id="dob" name="dob" class="form-control" type="date" 
                                                       class="input-large" required="">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="password">Password:</label>
                                            <div class="controls">
                                                <input id="password" name="password" class="form-control" type="password" 
                                                       placeholder="********" class="input-large" required="">
                                                <em>1-8 Characters</em>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="reenterpassword">Re-Enter Password:</label>
                                            <div class="controls">
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
                                    </fieldset>
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
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        Sign In
                    </div>
                    <div class="modal-body">
                        <div class="tab-pane fade active in" id="admin">
                            <form class="form-horizontal" action="AdminLoginServlet" method="POST">
                                <fieldset>
                                    <div class="control-group">
                                        <label class="control-label" for="userid">Username:</label>
                                        <div class="controls">
                                            <input required="" id="userid" name="userid" type="text" class="form-control" 
                                                 placeholder="name@xyz.com" class="input-medium" required="">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="passwordinput">Password:</label>
                                        <div class="controls">
                                            <input required="" id="passwordinput" name="passwordinput" class="form-control" type="password" placeholder="********" class="input-medium">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <div class="controls">
                                            <label>
                                                <input type="checkbox" aria-label="Remember me">
                                                Remember me
                                            </label>
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
                    </div>
                    <div class="modal-footer">
                        <center>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </center>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <%@include file="/lib/footer.html" %>
</html>
