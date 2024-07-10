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
        <title>Summary</title>
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
            function setAttempt(ID) {
                document.getElementById("AttemptID").value = ID;
            }

        </script>

    </head>

    <body>

        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->



        <!-- Navbar start -->
        <div class="container-fluid fixed-top">
            <div class="container topbar bg-primary d-none d-lg-block">
                <div class="d-flex justify-content-between">
                    <div class="top-info ps-2">
                        <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">123 Street, New York</a></small>
                        <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white">Email@Example.com</a></small>
                    </div>
                    <div class="top-link pe-2">
                        <a href="#" class="text-white"><small class="text-white mx-2">Privacy Policy</small>/</a>
                        <a href="#" class="text-white"><small class="text-white mx-2">Terms of Use</small>/</a>
                        <a href="#" class="text-white"><small class="text-white ms-2">Sales and Refunds</small></a>
                    </div>
                </div>
            </div>
            <div class="container px-0">
                <nav class="navbar navbar-light bg-white navbar-expand-xl">
                    <a href="index.html" class="navbar-brand"><h1 class="text-primary display-6">ECourse</h1></a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                        <div class="navbar-nav mx-auto">
                            <a href="Home" class="nav-item nav-link">Home</a>
                            <a href="CourseShop" class="nav-item nav-link">Shop</a>
                            <a href="MyCourse" class="nav-item nav-link active">My Courses</a>                                                            
                            <a href="Cart" class="nav-item nav-link">Cart</a>                                                                                                            
                            <a href="contact.html" class="nav-item nav-link">Contact</a>
                        </div>
                        <div class="d-flex m-3 me-0">
                            <button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal"><i class="fas fa-search text-primary"></i></button>
                            <a href="#" class="position-relative me-4 my-auto">
                                <i class="fa fa-shopping-bag fa-2x"></i>                                
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
        
        <div class="container-fluid page-header py-5">
            <h1 class="text-center text-white display-6">Quiz Summary</h1>
            <ol class="breadcrumb justify-content-center mb-0">
                <li class="breadcrumb-item"><a href="MyCourse">My Course</a></li>
                <li class="breadcrumb-item"><a href="CourseContent?CourseID=${CourseID}">Course</a></li>                            
                <li class="breadcrumb-item active text-white">Quiz</li>
            </ol>
        </div>       

        <!-- Bestsaler Product Start -->
        <input type="text" name="CourseID" value="${CourseID}" hidden>
        <input type="text" name="LessonID" value="${LessonID}" hidden>
        <input type="text" name="QuizID" value="${QuizID}" hidden> 
        <div class="container-fluid py-5">
            <div class="container">

                <div class="text-center">                    
                    <h1 class="display-5 mb-5 text-dark">${Quiz.getQuizName()}</h1>
                </div>

                <c:if test="${AttemptList.size() < 1}">
                    <form action="Summary" method="post">
                        <div class="col-xl-12 d-flex mt-5 justify-content-center">
                            <input type="text" name="CourseID" value="${CourseID}" hidden>
                            <input type="text" name="LessonID" value="${LessonID}" hidden>
                            <input type="text" name="QuizID" value="${QuizID}" hidden>                                      
                            <button class="btn border border-secondary rounded-pill px-3 text-primary">Attempt Quiz Now</button>
                        </div>
                    </form>
                </c:if>

                <c:if test="${AttemptList.size() > 0}">
                    <div class="row justify-content-center">                        
                        <div class="col-lg-12 row">
                            <form action="Review" method="post">
                                <input type="text" name="CourseID" value="${CourseID}" hidden>
                                <input type="text" name="LessonID" value="${LessonID}" hidden>
                                <input type="text" name="QuizID" value="${QuizID}" hidden> 
                                <input type="text" name="AttemptID" value id="AttemptID" hidden>
                                <hr/>
                                <table>

                                    <thead>
                                    <th class="col-lg-2">Attempt<hr/></th>
                                    <th class="col-lg-4">State<hr/></th>
                                    <th class="col-lg-3">Marks / Number of Questions<hr/></th>
                                    <th class="col-lg-3">Grades / 10<hr/></th>
                                    <th class="col-lg-3">Review<hr/></th>                                    
                                    </thead>

                                    <tbody>                                    
                                        <c:forEach items="${AttemptList}" var="x">
                                            <tr>                             
                                                <c:if test="${x.getFinished() == 0}">
                                                    <td>${x.getAttemptID()}</td>
                                                    <td>
                                                        <h5>In Progess</h5>
                                                        <p>Attempt Date: ${x.getAttemptDate()}</p>
                                                    </td>
                                                    <td>N/A</td>
                                                    <td>N/A</td>
                                                </c:if>
                                                <c:if test="${x.getFinished() == 1}">
                                                    <td>${x.getAttemptID()}</td>
                                                    <td>
                                                        <h5>Finished</h5>
                                                        <p>Submitted Date: ${x.getSubmittedDate()}</p>
                                                    </td>
                                                    <td>${UserINS.getAttemptMark(User.getUserID(), CourseID, LessonID, QuizID, x.getAttemptID())} / ${Quiz.getNoQ()}</td>
                                                    <td>${UserINS.getAttemptMark(User.getUserID(), CourseID, LessonID, QuizID, x.getAttemptID()) / Quiz.getNoQ() * 10}</td>
                                                    <td><button class="btn border border-secondary rounded-pill px-3 text-primary" onclick="setAttempt(${x.getAttemptID()})">Review</button></td>
                                                </c:if>               
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>                                
                            </form>
                        </div>
                    </div>
                    <form action="Summary" method="post">
                        <div class="col-xl-12 d-flex mt-5 justify-content-center">
                            <input type="text" name="CourseID" value="${CourseID}" hidden>
                            <input type="text" name="LessonID" value="${LessonID}" hidden>
                            <input type="text" name="QuizID" value="${QuizID}" hidden> 
                            <c:set var="Newest" value="${UserINS.getNewestAttempt(User.getUserID(), CourseID, LessonID, QuizID)}"></c:set>
                            <c:if test="${Newest.getFinished() == 1}">
                                <button class="btn border border-secondary rounded-pill px-3 text-primary">Re-Attempt Quiz</button>
                            </c:if>
                            <c:if test="${Newest.getFinished() == 0}">
                                <button class="btn border border-secondary rounded-pill px-3 text-primary">Continue the last attempt</button>
                            </c:if>
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