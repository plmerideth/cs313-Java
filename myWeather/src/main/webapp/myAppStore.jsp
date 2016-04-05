<%-- 
    Document   : index
    Created on : Apr 2, 2016, 4:26:20 PM
    Author     : Paul Merideth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="myAppStoreStyle.css" rel="stylesheet" type="text/css" media="screen"/>                
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div id="myHeader">                
                <h1 class="myLogo">myWeather</h1>
                <img class="imgSun" src="Sun-6.jpg"/>
                <p class="mySubLogo">Register to have your own personal weatherman!</p>                
            </div>
            
            <div id="myContent">
                <span class="contentTitle">myWeather</span>
                <a href="index.jsp" class="gotoWeather" title="Go to myWeather">Go To MyWeather</a>
                <h3 class="subTitle">Don't let surprise weather ruin your next family or business trip!</h3>
                <p>myWeather gives you all the weather information you need for any city in the US.</p>
                <p>Just enter the city and state OR zip code for any valid US city</p>
                <div class="FeatureListLeft">
                    <ul class="Features">
                        <li>Current Temperature</li>
                        <li>Wind Speed</li>
                        <li>Humidity</li>
                        <li>Precipitation</li>
                    </ul>
                </div>
                <div class="FeatureListRight">
                    <ul class="Features">
                        <li>Visibility</li>
                        <li>Local 10-day Forecast</li>
                        <li>Local Weather History</li>
                        <li>Latitude and Longitude</li>
                    </ul>
                </div>
                <p class="myClear"></p>                
                <p>To build the myWeather application I used the Weather Underground API, described at this site: <a href="https://www.wunderground.com/weather/api/">Weather Underground</a>.</p>
                <p>I also used the Jackson JSON library to parse the JSON data returned by the weather underground web services.  I found an awesome site called JSON Utils at this site: <a href="https://www.wunderground.com/weather/api/">JSON Utils</a> which created all my POJOs directly from the JSON output from the weather underground API calls.</p>
            </div>                                 
        </div>
    </body>
</html>
