<%-- 
    Document   : login
    Created on : 6-set-2017, 22.24.30
    Author     : Ste2016
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Login page NerdBook">
        <meta name="author" content="Stefano Fenu">
         <link rel="stylesheet" type="text/css" href="style.css"
        media="screen">
    </head>
    <body>
        
        
         <c:set var="page" value="login" scope="request"/>
         
        <div class="titleL">
            <h2> NerdBook </h2>
        </div>
        
        <div class="imgT">
            
            
        </div>
        
        <div class="formP">
            
            <c:if test="${loginError==true}">
                <div class="error">
                   <h3> Combinazione Email e Password incorretta, riprovare. </h3>
                </div>
            </c:if>
            
            <form action="login.html"  class="register-form" method="post">
              <label for="Username">Username</label>
              <input name="username" id="Username" type="text"/>
              <br><br>
              <label for="psw">Password</label>
              <input name="nome" id="psw" type="text"/>
              
              <div class="btlg">
                <button type="submit" >create</button>    
              </div>    
            </form>
            
        </div>
        
    </body>
</html>

