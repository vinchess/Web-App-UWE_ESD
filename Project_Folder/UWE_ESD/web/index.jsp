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
    <body style="padding-top: 70px;">
        
        <center>
            <div class="container">
                <div class="jumbotron">
                    <h1>XYZ Drivers Associations</h1>
                    <h3>Welcome to your personal claims portal.<br>
                        For all your minor claim needs.<br>
                        To proceed please login.</h3>
                    <button class="btn btn-primary btn-lg" href="#signin" 
                            data-toggle="modal" data-target="#myModal">Sign In/Register</button>
                    <button class="btn btn-primary btn-lg" href="#admin"
                            data-toggle="modal" data-target="#myAdmin">Admin</button>
                    <a href="admin/dashboard.jsp"><button class="btn btn-primary btn-lg" >Admin</button></a>
                </div>
            </div>
        </center>

        <!-- Modal -->
        <div class="modal fade bs-modal-sm" id="myModal" tabindex="-1" role="dialog" 
             aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content"><br>
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
                        <p>We need this information so that you can receive access to the site and its content. Rest assured your information will not be sold, traded, or given to anyone.</p>
                        <p></p><br> Please contact <a mailto:href="JohnDoe@example.com">JohnDoe@example.com</a> for any other inquiries.</p>
                    </div>
                    <div class="tab-pane fade active in" id="signin">
                        <form class="form-horizontal">
                            <fieldset>
                                <!-- Sign In Form -->
                                <!-- Text input-->
                                <div class="control-group">
                                  <label class="control-label" for="userid">Username:</label>
                                  <div class="controls">
                                    <input required="" id="userid" name="userid" type="text" class="form-control" 
                                           placeholder="name@xyz.com" class="input-medium" required="">
                                  </div>
                                </div>

                                <!-- Password input-->
                                <div class="control-group">
                                  <label class="control-label" for="passwordinput">Password:</label>
                                  <div class="controls">
                                    <input required="" id="passwordinput" name="passwordinput" 
                                           class="form-control" type="password" placeholder="********" class="input-medium">
                                  </div>
                                </div>

                                <!-- Multiple Checkboxes (inline) -->
                                <div class="control-group">
                                    <div class="controls">
                                        <label>
                                            <input type="checkbox" aria-label="Remember me">Remember me
                                        </label>
                                    </div>
                                </div>

                                <!-- Button -->
                                <div class="control-group">
                                    <label class="control-label" for="signin"></label>
                                    <div class="controls">
                                        <button id="signin" name="signin" class="btn btn-success">Sign In</button>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="signup">
                        <form class="form-horizontal">
                            <fieldset>
                                <!-- Sign Up Form -->
                                <!-- Text input-->
                                <div class="control-group">
                                    <label class="control-label" for="Email">Email:</label>
                                    <div class="controls">
                                        <input id="Email" name="Email" class="form-control" type="text" 
                                               placeholder="johndoe@example.com" class="input-large" required="">
                                    </div>
                                </div>
                                <div class="control-group">
                                  <label class="control-label" for="userid">Name:</label>
                                  <div class="controls">
                                    <input id="userid" name="userid" class="form-control" type="text" 
                                           placeholder="JohnDoe" class="input-large" required="">
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
                                <br>
                                <div class="control-group">
                                    <label class="control-label" for="humancheck">Humanity Check:</label>
                                    <div class="controls">
                                        <label>
                                            <input type="radio" name="humancheck" id="humancheck-0" value="robot" 
                                                   checked="checked">I'm a Robot</label>
                                        <label style="float:right">
                                            <input type="radio" name="humancheck" id="humancheck-1" value="human">I'm Human</label>
                                    </div>
                                </div>

                                <!-- Button -->
                                <div class="control-group">
                                    <label class="control-label" for="confirmsignup"></label>
                                    <div class="controls">
                                        <button id="confirmsignup" name="confirmsignup" class="btn btn-success">Sign Up</button>
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

        <div class="modal fade bs-modal-sm" id="myAdmin" tabindex="-1" role="dialog" 
               aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <br>
                    <div class="bs-example bs-example-tabs">
                        <ul id="myTab" class="nav nav-tabs">
                            <li class="active"><a href="#admin" data-toggle="tab">Sign In</a></li>
                        </ul>
                    </div>
                    <div class="modal-body">
                        <div class="tab-pane fade active in" id="admin">
                            <form class="form-horizontal">
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
                                            <button id="signin" name="signin" class="btn btn-success">Sign In</button>
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
