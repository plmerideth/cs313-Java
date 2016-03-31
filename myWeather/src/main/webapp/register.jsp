<%-- 
    Document   : register
    Created on : Mar 28, 2016, 4:27:52 PM
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
                <p id="myLogo">Registration Page</p>
            </div>
            <div id="myContent">
                <p class="registerInstructions">Please complete the following registration form.  Click "Register" when done.</p>                
                <form action="MyRegistration" method="post">
                    <fieldset class="myFieldSet"><legend>Please Enter Desired Login Credentials</legend>
                        <label class="label" for="firstname"><b>First Name:</b></label>
                        <input type="text" name="firstname" size="30" maxlength="60"/>
                        <br/>			        
                        <label class="label" for="lastname"><b>Last Name:</b></label>
                        <input type="text" name="lastname" size="30" maxlength="60"/>
                        <br/>
                        <label class="label" for="username"><b>User Name:</b></label>
                        <input type="text" name="username" size="30" maxlength="60"/>
                        <br/>                        
                        <label class="label" for="email"><b>Email Address:</b></label>
                        <input type="text" name="email" size="30" maxlength="60"/>
                        <br/>
                        <label class="label" for="password"><b>Password:</b></label>
                        <input type='password' name="password" size="30" maxlength="20"/>			        
                        <br/><br/>
                        <input type="submit" class="submitRegistration" name="registration" value="Register"/>
                        <a href="index.jsp" class="homePage" title="Home">Home Page</a>
                    </fieldset>
                </form>                                                                             
            </div>                                 
        </div>
    </body>
</html>
