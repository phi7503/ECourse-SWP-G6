<%-- 
    Document   : detailUser
    Created on : May 28, 2024, 1:37:00 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Detail</title>
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
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid fixed-top">
            <header>
                <div class="container topbar bg-success d-none d-lg-block">
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
            </header>
            <div class="row">
                <div class="col-md-2 mt-5">
                    <ul class="list-group ">
                        <li class="list-group-item">User list</li>
                        <li class="list-group-item">Course list</li>
                        <li class="list-group-item">Settings List</li>
                        <li class="list-group-item">2 list</li>
                    </ul>
                </div>
                <div class="col-md-7 mt-5">
                    <c:if test="${not empty u}">
                        <div class="form-group row">
                            <label for="username" class="col-sm-3 col-form-label">USER NAME</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="username" value="${u.getUserName()}" name="username" readonly >
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="email" class="col-sm-3 col-form-label">EMAIL</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="userID" value="${u.getMail()}" name="email" readonly >
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="fullname" class="col-sm-3 col-form-label">FULLNAME </label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="fullname" value="${u.getFullName()}" name="fullname" readonly >
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="dateOfBirth" class="col-sm-3 col-form-label">DATE OF BIRTH</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="dateOfBirth" value="${u.getDoB()}" name="dateOfBirth" readonly >
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="role" class="col-sm-3 col-form-label">ROLE</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="role" value="${u.getRole()}" name="role" readonly >
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="status" class="col-sm-3 col-form-label">STATUS</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="status" value="${u.getStatus()}" name="status" readonly >
                            </div>
                        </div>
                    </c:if>
                </div>
                <div class="col-md-2 justify-content-end mt-5">
                    <a href="userslist" class="btn btn-success float-right">Back</a>
                </div>
            </div>
        </div>
    </body>
</html>
