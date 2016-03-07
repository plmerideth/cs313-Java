<%-- 
    Document   : invalidLogin
    Created on : Mar 2, 2016, 9:40:27 AM
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
        <h1>Sorry ${param.username},</h1>
        <h1>Invalid Login Credentials!</h1>

        <form action="login.jsp" method="post">
            <input type="submit" value="Return to LogIn" />
        </form>        
    </body>
</html>

