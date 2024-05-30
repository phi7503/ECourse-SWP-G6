<%-- 
    Document   : ForgotPassword
    Created on : May 30, 2024, 2:30:54 AM
    Author     : DELL
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
                    <a href="Home"><h1 class="text-primary display-6">Forgot Password</h1></a>              
                </div>
            </div>
        </div>
        <!-- Navbar End -->                   

        <!-- Fact Start -->
        <div class="container-fluid py-5 col-lg-6">

            <a href="../../src/java/Controller/Login.java"></a>
            <form action="ForgotPassword" method="post">
                <div class="bg-light p-5">
                    <div class="col-lg-12 col-md-12 col-xl-12">
                        <div class="counter bg-white rounded p-4" >
                            <h1 class="display-6 text-primary px-4">User Name</h1>                                
                            <input class="form-control border-2 border-secondary px-4 rounded-pill" type="text" name="Username" placeholder="Enter user-name" required>
                            <h1 class="display-6 text-primary px-4">E-Mail</h1>                                
                            <input class="form-control border-2 border-secondary px-4 rounded-pill" type="text" name="mail" placeholder="Enter e-mail" required>
                            <h1 class="display-6 text-primary px-4">Question</h1>
                            <select class="form-control border-2 border-secondary px-4 rounded-pill" name="question">
                                <c:forEach var="item" items="${SEQuestions}">
                                    <option value="${item.getSecutiryQuestionID()}">${item.getQuestion()}</option>
                                </c:forEach>
                            </select>
                            <h1 class="display-6 text-primary px-4">Answer</h1>
                            <input class="form-control border-2 border-secondary px-4 rounded-pill" type="text" name="answer" placeholder="Enter Answer" required>
                            <h1 class="display-6 text-primary px-4">New Passworld</h1>
                            <input class="form-control border-2 border-secondary px-4 rounded-pill" type="text" name="newPass" placeholder="Enter New Password" required>
                                
                            <br/>
                            <c:if test='${err != null}'>
                                <h4 class="mb-3 text-secondary">${err}</h4>
                            </c:if>
                                <c:if test='${suc != null}'>
                                <h4 class="mb-3 text-primary">${err}</h4>
                            </c:if>
                            <input type="submit" name="LoginSubmit" class="btn btn-primary border-2 border-secondary rounded-pill text-white">
                        </div>                        
                        <a href="Login">Login </a>
                    </div>

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
