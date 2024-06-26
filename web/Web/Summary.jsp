<%-- 
    Document   : Summary
    Created on : May 29, 2024, 5:00:04 PM
    Author     : hi2ot
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Fruitables - Vegetable Website Template</title>
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

        <script>
            
        </script>

    </head>

    <body>

        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->



        <!-- Navbar start -->
        <div class="container-fluid">            
            <div class="container px-0">
                <nav class="navbar navbar-light bg-white navbar-expand-xl">
                    <a href="index.html" class="navbar-brand"><h1 class="text-primary display-6">ECourse</h1></a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                        <div class="navbar-nav mx-auto">
                            <a href="/Home" class="nav-item nav-link">Home</a>
                            <a href="shop.html" class="nav-item nav-link">Course</a>
                            <a href="/Quiz" class="nav-item nav-link active">Quiz</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                                <div class="dropdown-menu m-0 bg-secondary rounded-0">
                                    <a href="cart.html" class="dropdown-item">Cart</a>
                                    <a href="chackout.html" class="dropdown-item">Checkout</a>
                                    <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                                    <a href="404.html" class="dropdown-item">404 Page</a>
                                </div>
                            </div>
                            <a href="contact.html" class="nav-item nav-link">Contact</a>
                        </div>
                        <div class="d-flex m-3 me-0">
                            <button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal"><i class="fas fa-search text-primary"></i></button>
                            <a href="#" class="position-relative me-4 my-auto">
                                <i class="fa fa-shopping-bag fa-2x"></i>
                                <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
                            </a>
                            <a href="#" class="my-auto">
                                <i class="fas fa-user fa-2x"></i>
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->        

        <!-- Bestsaler Product Start -->
        <input type="text" name="CourseID" value="${CourseID}" hidden>
        <input type="text" name="LessonID" value="${LessonID}" hidden>
        <input type="text" name="QuizID" value="${QuizID}" hidden> 
        <div class="container-fluid testimonial py-5">
            <div class="container py-5">

                <div class="testimonial-header text-center">                    
                    <h1 class="display-5 mb-5 text-dark">Summary</h1>
                </div>

                <c:if test="${AttemptList.size() < 1}">
                    <form action="QuizNavigate" method="post">
                        <div class="col-xl-12 d-flex mt-5 justify-content-center">
                            <input type="text" name="CourseID" value="${CourseID}" hidden>
                            <input type="text" name="LessonID" value="${LessonID}" hidden>
                            <input type="text" name="QuizID" value="${QuizID}" hidden>                                      
                            <button class="btn border border-secondary rounded-pill px-3 text-primary">Attempt Quiz Now</button>
                        </div>
                    </form>
                </c:if>

                <c:if test="${AttemptList.size() > 0}">
                    <div class="row g-4 justify-content-center">                        
                        <c:forEach items="${AttemptList}" var="x">

                            <div class="col-md-5">
                                <div class="testimonial-item img-border-radius bg-light rounded p-4">
                                    <div class="position-relative">                                                                        
                                        <div class="d-flex align-items-center flex-nowrap">
                                            <div class="bg-secondary rounded">
                                                <img src="img/best-product-1.jpg" class="img-fluid rounded" style="width: 100px; height: 100px;" alt="">
                                            </div>
                                            <div class="ms-4 d-block">
                                                <h4 class="text-dark">Attempt ${x.getAttemptID()}</h4>
                                                <p class="m-0 pb-3">Attempt Date: ${x.getAttemptDate()}</p>
                                                <p class="m-0 pb-3">Mark: ${UserINS.getAttemptMark(User.getUserID(), CourseID, LessonID, QuizID, x.getAttemptID())}</p>                                                
                                                <form action="ReviewNavigate" method="post">
                                                    <input type="text" name="CourseID" value="${CourseID}" hidden>
                                                    <input type="text" name="LessonID" value="${LessonID}" hidden>
                                                    <input type="text" name="QuizID" value="${QuizID}" hidden> 
                                                    <input type="text" name="AttemptID" value="${x.getAttemptID()}" hidden>
                                                    <button class="btn border border-secondary rounded-pill px-3 text-primary">Review</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>   
                            </div>

                        </c:forEach>
                    </div>
                    <form action="QuizNavigate" method="post">
                        <div class="col-xl-12 d-flex mt-5 justify-content-center">
                            <input type="text" name="CourseID" value="${CourseID}" hidden>
                            <input type="text" name="LessonID" value="${LessonID}" hidden>
                            <input type="text" name="QuizID" value="${QuizID}" hidden>                                      
                            <button class="btn border border-secondary rounded-pill px-3 text-primary">Re-Attempt Quiz</button>
                        </div>
                    </form>

                </c:if>
            </div>
        </div>

        <!-- Bestsaler Product End -->        

        <!-- Copyright Start -->
        <div class="container-fluid copyright bg-dark py-4">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                        <span class="text-light"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All right reserved.</span>
                    </div>
                    <div class="col-md-6 my-auto text-center text-md-end text-white">
                        <!--/*** This template is free as long as you keep the below author’s credit link/attribution link/backlink. ***/-->
                        <!--/*** If you'd like to use the template without the below author’s credit link/attribution link/backlink, ***/-->
                        <!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
                        Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Copyright End -->



        <!-- Back to Top -->
        <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   


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