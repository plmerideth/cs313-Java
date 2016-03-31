<%-- 
    Document   : login
    Created on : Mar 28, 2016, 4:27:36 PM
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
                <p id="myLogo">Login Page</p>
            </div>
            <div id="myContent">
                <form action="MyCredentials" method="POST">
                    <fieldset class="myFieldSet"><legend>Please Enter Username and Password</legend><br/>
                        Username: <input class="userClass" type="text" name="username" />
                        Password: <input class="passwordClass" type="password" name="password" /><br /><br/>
                        <input class="submitLogin" name="login" type="submit" value="Log In" />
                        <a href="index.jsp" class="homePage" title="Home">Home Page</a>
                    </fieldset>
                </form>                             
            </div>                                 
        </div>
    </body>

</html>
