<%-- 
    Document   : register
    Created on : May 24, 2024, 7:29:06 PM
    Author     : Admin
--%>

<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-image: url('img/ZoomBackground_BlueGreenGradient.png');
                background-size: cover;
                background-position: center;
                min-height: 100vh;
                margin: 0;
                padding: 0;
            }
            .bg-transparent {
                background-color: rgba(255, 255, 255, 0.8);
                border-radius: 10px;
                padding: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container d-flex align-items-center justify-content-center min-vh-100 ">
            <div class="col-md-8 bg-white p-3 bg-transparent text-white">
                <h2 class="text-center">Create New Account</h2> <br>
                <h4 class="text-center text-danger">${mess}</h4>
                <form action="register" method="post">
                    <div class="form-group row">
                        <label for="fullname" class="col-sm-3 col-form-label">FULL NAME</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Enter your full name" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="email" class="col-sm-3 col-form-label">EMAIL</label>
                        <div class="col-sm-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="username" class="col-sm-3 col-form-label">USER NAME</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="username" name="username" placeholder="Enter user name" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="password" class="col-sm-3 col-form-label">PASSWORD</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="Create password" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="cfpassword" class="col-sm-3 col-form-label">CONFIRM PASSWORD</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="cfpassword" name="cfpassword" placeholder="Confirm your password" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="dateOfBirth" class="col-sm-3 col-form-label">DATE OF BIRTH</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" placeholder="YYYY-MM-DD" required>

                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">ROLE</label>
                        <div class="col-sm-9">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="expert" value="expert" checked>
                                <label class="form-check-label" for="expert">Expert</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="learner" value="learner">
                                <label class="form-check-label" for="learner">Learner</label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="securityQ" class="col-sm-3 col-form-label">SECURITY QUESTION</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="securityQ" name="securityQuestion">
                                <% Vector<String> list = (Vector<String>)request.getAttribute("list");
                                   if(list!=null) {
                                       for (String e  : list) {
                                %>
                                <option value="<%=e%>">
                                    <%=e%>
                                </option>
                                <% }} %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="answer" class="col-sm-3 col-form-label">ANSWER</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="answer" name="answer" placeholder="Enter your answer" required> 
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-9 offset-sm-3">
                            <button class="btn btn-success btn-block" type="submit">Sign Up</button>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-9 offset-sm-3">
                            <a href="login.jsp" class="btn btn-success btn-block" type="button">Sign In</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>



        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
