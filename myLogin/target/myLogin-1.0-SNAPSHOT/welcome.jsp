<%-- 
    Document   : welcome
    Created on : Mar 2, 2016, 9:06:28 AM
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
        <h1>Welcome ${param.username}</h1>
        <h1>Your password is: ${param.password}</h1>
        <br/><br/>
        <form action="logout" method="post">
            <input type="submit" value="Log Out" />
        </form>     
    </body>
</html>
