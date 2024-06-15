<%-- 
    Document   : addUser
    Created on : May 29, 2024, 1:20:15 AM
    Author     : Admin
--%>

<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container d-flex align-items-center justify-content-center min-vh-150">
            <div class="col-md-8 bg-white p-3 bg-transparent text-white">
                <h2 class="text-center">Create New Account</h2> <br>
                <h4 class="text-center text-danger">${mess}</h4>
                <form class="mt-5" action="AddUser" method="post">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="username">Username:</label><br>
                        <input class="col-sm-9 form-control" type="text" id="username" name="username"><br><br>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="password">Password:</label><br>
                        <input class="col-sm-9 form-control" type="password" id="password" name="password"><br><br>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="cfpassword">Confirm Password:</label><br>
                        <input class="col-sm-9 form-control" type="password" id="cfpassword" name="cfpassword"><br><br>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="email">Email:</label><br>
                        <input class="col-sm-9 form-control" type="email" id="email" name="email"><br><br>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="fullname">Fullname:</label><br>
                        <input class="col-sm-9 form-control" type="text" id="fullname" name="fullname"><br><br>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="dateOfBirth">Date of Birth:</label><br>
                        <input class="col-sm-9 form-control" type="date" id="dateOfBirth" name="dateOfBirth"><br><br>
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
                        <label class="col-sm-3 col-form-label" for="answer">Answer:</label><br>
                        <input class="col-sm-9 form-control" type="text" id="answer" name="answer"><br><br>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="role">Role:</label><br>
                        <select class="col-sm-9 form-control" id="role" name="role">
                            <option value="admin">Admin</option>
                            <option value="expert">Expert</option>
                            <option value="learner">Learner</option>
                        </select><br><br>
                    </div>

                    <button class="text-center" type="submit" value="Submit"> Submit</button>

                </form>
            </div>
        </div>



        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
