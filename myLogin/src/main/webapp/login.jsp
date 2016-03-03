<%-- 
    Document   : login
    Created on : Mar 2, 2016, 8:10:12 AM
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
        <form action="verifyCredentials" method="POST">
              Username: <input type="text" name="username" /><br /><br/>
              Password: <input type="password" name="password" /><br /><br/>
              <input type="submit" value="Log In" />
       </form>
    </body>
</html>
