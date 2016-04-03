<%-- 
    Document   : displayWeather
    Created on : Apr 2, 2016, 11:32:19 AM
    Author     : Paul Merideth
--%>

<%@page import="myWeather.MyWeatherData"%>
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
                <p id="myLogo">Weather Page</p>
            </div>
            <%
                MyWeatherData WeatherData = new MyWeatherData();
                if (request.getAttribute("weather") != null) {
                 WeatherData = (MyWeatherData) request.getAttribute("weather");
                }
                %>
            <div id="cityTitle">
                <h1><%= WeatherData.current_observation.display_location.full%></h1>
            </div>
            <div id="myWeather-1">                                
                <p>Local Date & Time = <%= WeatherData.current_observation.local_time_rfc822%></p>
                <p>Longitude = <%= WeatherData.current_observation.display_location.longitude%></p>
                <p>Latitude = <%= WeatherData.current_observation.display_location.latitude%></p>
                <p>Temp = <%= WeatherData.current_observation.temperature_string%></p>
                <p>Wind = <%= WeatherData.current_observation.wind_string%></p>
                <a href="getCity.jsp" class="submitWeather" title="getCity">Change Location</a>
                <a href="index.jsp" class="homeWeather" title="Home">Home</a>                
            </div>
            <div id="myWeather-2">
                <p>Wind Chill = <%= WeatherData.current_observation.windchill_f%></p>
                <p>Humidity = <%= WeatherData.current_observation.relative_humidity%></p>
                <p>Precipitation Today = <%= WeatherData.current_observation.precip_today_string%></p>
                <p>Visibility (mi) = <%= WeatherData.current_observation.visibility_mi%></p>
                <p>Feels Like = <%= WeatherData.current_observation.feelslike_string%></p>
                <a href=<%= WeatherData.current_observation.forecast_url%> class="submitWeather" title="Forecast">Local Forecast</a>
                <a href=<%= WeatherData.current_observation.history_url%> class="homeWeather" title="Forecast">Weather History</a>
            </div>
            <br/><br/>
        </div>
</html>
