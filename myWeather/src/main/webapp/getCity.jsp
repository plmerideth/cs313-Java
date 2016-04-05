<%-- 
    Document   : getCity
    Created on : Apr 2, 2016, 10:56:12 AM
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
                <h1 class="myLogo">myWeather</h1>
                <img class="imgSun" src="Sun-6.jpg"/>
                <p class="mySubLogo">Location Page</p>                               
            </div>
            <div id="myContent">                
                <h1>Welcome ${first_name}</h1>                
                <form action="getWeather" method="POST">
                    <fieldset class="myCitySet"><legend>Please enter city and state OR zip code</legend><br/>
                        City: <input class="userClass" type="text" name="city" />
                        State: <input class="passwordClass" type="text" name="state" /><br /><br/>
                        OR<br/><br/>
                        Zip Code: <input class="userClass" type="text" name="zipcode" /><br/><br/>
                        <input class="submitLogin" name="submit" type="submit" value="Submit" />
                        <a href="index.jsp" class="homePage" title="Home">Home Page</a>
                    </fieldset>
                </form>                                                                                                             
            </div>
            <div class="myClear"></div>
        </div>
</html>
