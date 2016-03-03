<%-- 
    Document   : submitNumbers
    Created on : Mar 1, 2016, 9:06:56 AM
    Author     : Paul Merideth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Number Submission</title>
    </head>
    <body>
        <form action="ComputeSum" method="GET">
              First Number: <input type="text" name="first" /><br />
              Second Number: <input type="text" name="second" /><br />
              <input type="submit" value="Compute Sum" />
       </form>
    </body>
</html>
