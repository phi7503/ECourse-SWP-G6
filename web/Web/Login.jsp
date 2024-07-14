<%-- 
    Document   : Login
    Created on : May 25, 2024, 5:12:23 PM
    Author     : hi2ot
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Login</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">

        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>

        <!-- Navbar start -->   
        <div class="container-fluid" style="padding-top: 50px">           
            <div class="container">
                <div class="text-center mx-auto mb-5">
                    <a href="Home"><h1 class="text-primary display-6">Ecourse</h1></a>              
                </div>
            </div>
        </div>
        <!-- Navbar End -->                   

        <!-- Fact Start -->
        <div class="container-fluid py-5 row">
            <div class="col-lg-1"></div>
            <div class="col-lg-5">
                <form action="Login" method="post">                    
                        <div class="col-lg-12 col-md-12 col-xl-12" style="padding-top: 5%">
                            <div class="counter bg-white rounded p-4" >
                                <h2 class="display-6 text-primary px-4">Username</h2>                                
                                <input class="form-control border-2 border-secondary px-4 rounded-pill" type="text" name="Username" placeholder="Username" required>
                                <h2 class="display-6 text-primary px-4">Password</h2>
                                <input class="form-control border-2 border-secondary px-4 rounded-pill" type="password" name="Password" placeholder="Password" required>
                                <br/>                                
                                <p>${err}</p>                                
                                <input type="submit" name="LoginSubmit" value="Login" class="btn btn-primary border-2 border-secondary rounded-pill text-white">
                            </div>                             
                        </div>                    
                </form>
            </div>
            <div class="col-lg-1">                
                <p class="text-center" style="margin-top: 150%"> OR </p>
            </div>
            <div class="col-lg-3" style="margin-top: 9%">
                <p class="text-center">New to our system? <a href="Register">Create New Account</a></p>
                <hr/>
                <p class="text-center">Forget your password? <a href="ForgetPassword">Forget Password</a></p>
            </div>
        </div>
        <!-- Fact Start -->

        <!-- JavaScript Libraries -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>

</html>
