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
    </head>
    <body>
        <div class="container">
            <div class="col-md-3 text-center">
                    <span class="glyphicon glyphicon-user img-circle" aria-hidden="true" style="font-size: 6em;"></span>
                    <h2>NAME</h2>
                    <h3>Balance</h3>
                    <h3><%//get user balance here%>$0.00</h3>
                    <button type="button" class="btn btn-default btn-lg btn-block" aria-label="Left Align">
                        <span class="glyphicon glyphicon-gbp"></span>PAY
                    </button>
                    <button type="button" class="btn btn-default btn-lg btn-block" aria-label="Left Align">
                        <span class="glyphicon glyphicon-pencil"></span>CLAIM
                    </button>
            </div>
            <div class="col-md-6 col-md-offset-3 ">
                <h1 style="color:green"><%//get account status here%>ACTIVE</h1>
                
            </div>
        </div>
    </body>
    <%@include file="/lib/footer.html" %>
</html>
