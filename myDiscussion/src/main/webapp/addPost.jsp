<%-- 
    Document   : addPost
    Created on : Mar 6, 2016, 1:11:51 PM
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
    <form action="addUserPost" method="post">
        <h1>Please enter your comments below</h1>
        <textarea name="comment" cols="60" rows="10"></textarea>
        <input type="submit" value="Done" />
    </body>
</html>
