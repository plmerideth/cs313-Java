<%-- 
    Document   : badLocation
    Created on : Apr 2, 2016, 2:42:34 PM
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
                <p id="myLogo">Invalid Location</p>
            </div>
            <div id="myContent">
                <h1>Invalid Location Entered</h1>
                <h3>Please enter a valid city and state or zip code</h3>
                <a href="getCity.jsp" class="submitLogin" title="Login">Back</a>
                <a href="index.jsp" class="homePage" title="Home">Home Page</a>
            </div>                                 
        </div>
    </body>
</html>
