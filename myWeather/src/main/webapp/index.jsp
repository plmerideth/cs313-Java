<%-- 
    Document   : index
    Created on : Mar 26, 2016, 1:20:12 PM
    Author     : Paul Merideth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="myWeatherStyle.css" rel="stylesheet" type="text/css" media="screen"/>                
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div id="myHeader">
                <h1 id="myLogo">myWeather</h1>
                <p id="myLogo">Home Page</p>
            </div>
            <div id="myContent">
                <fieldset class="myFieldSet"><legend>Please Login or Register New Account</legend>
                    <form action="login.jsp" method="POST">
                          <input class="indexLogin" name="login" type="submit" value="Log In" />
                    </form>
                    <span class="orClass">OR</span>                
                    <form action="register.jsp" method="POST">
                          <input class="registerLogin" name="register" type="submit" value="Register" />
                    </form>                
                </fieldset>
            </div>                                 
        </div>
    </body>
</html>
