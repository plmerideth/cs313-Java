<%-- 
    Document   : movieSearch
    Created on : Mar 9, 2016, 7:19:08 PM
    Author     : Paul Merideth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Movie Search</h1>
        <form action="movieSearch" method="POST">
              Movie Title: <input type="text" name="movieTitle" /><br /><br/>              
              <input type="submit" value="Submit" />
       </form>        
    </body>
</html>
