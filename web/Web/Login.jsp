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
                    <a href="Home"><h1 class="text-primary display-6">Fruitables</h1></a>              
                </div>
            </div>
        </div>
        <!-- Navbar End -->                   

        <!-- Fact Start -->
        <div class="container-fluid py-5 col-lg-6">
<<<<<<< HEAD
            
                <form action="Login" method="post">
                    <div class="bg-light p-5">
                        <div class="col-lg-12 col-md-12 col-xl-12">
                            <div class="counter bg-white rounded p-4" >
                                <h1 class="display-6 text-primary px-4">Username</h1>                                
                                <input class="form-control border-2 border-secondary px-4 rounded-pill" type="text" name="Username" placeholder="Username" required>
                                <h1 class="display-6 text-primary px-4">Password</h1>
                                <input class="form-control border-2 border-secondary px-4 rounded-pill" type="password" name="Password" placeholder="Password" required>
                                <br/>
                                <c:if test='${err != null}'>
                                    <h4 class="mb-3 text-secondary">${err}</h4>
                                </c:if>
                                <input type="submit" name="LoginSubmit" value="Login" class="btn btn-primary border-2 border-secondary rounded-pill text-white">
                            </div>                             
                        </div>
=======


            <a href="../../src/java/Controller/Login.java"></a>
            <form action="Login" method="post">
                <div class="bg-light p-5">
                    <div class="col-lg-12 col-md-12 col-xl-12">
                        <div class="counter bg-white rounded p-4" >
                            <h1 class="display-6 text-primary px-4">Username</h1>                                
                            <input class="form-control border-2 border-secondary px-4 rounded-pill" type="text" name="Username" placeholder="Username" required>
                            <h1 class="display-6 text-primary px-4">Password</h1>
                            <input class="form-control border-2 border-secondary px-4 rounded-pill" type="password" name="Password" placeholder="Password" required>
                            <br/>
                            <c:if test='${err != null}'>
                                <h4 class="mb-3 text-secondary">${err}</h4>
                            </c:if>
                            <input type="submit" name="LoginSubmit" class="btn btn-primary border-2 border-secondary rounded-pill text-white">
                        </div>                             
                        <form action="Login" method="post">
                            <div class="bg-light p-5">
                                <div class="col-lg-12 col-md-12 col-xl-12">
                                    <div class="counter bg-white rounded p-4" >
                                        <h1 class="display-6 text-primary px-4">Username</h1>                                
                                        <input class="form-control border-2 border-secondary px-4 rounded-pill" type="text" name="Username" placeholder="Username" required>
                                        <h1 class="display-6 text-primary px-4">Password</h1>
                                        <input class="form-control border-2 border-secondary px-4 rounded-pill" type="password" name="Password" placeholder="Password" required>
                                        <br/>
                                        <c:if test='${err != null}'>
                                            <h4 class="mb-3 text-secondary">${err}</h4>
                                        </c:if>
                                        <input type="submit" name="LoginSubmit" class="btn btn-primary border-2 border-secondary rounded-pill text-white">
                                    </div>                        
                                    <a href="ForgotPassword">Forgot Password </a>
                                </div>
                            </div>


>>>>>>> 55609f03a79a05168caa2a99f12537d0da970c95
                    </div>
            </form>

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
