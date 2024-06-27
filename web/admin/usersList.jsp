<%-- 
    Document   : usersList
    Created on : May 26, 2024, 7:12:58 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Coursera-Like Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

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
    </head>
    <body>

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
                    <a href="index.html" class="navbar-brand"><h1 class="text-primary display-6">Fruitables</h1></a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                        <div class="navbar-nav mx-auto">
                            <a href="index.html" class="nav-item nav-link active">Home</a>
                            <a href="shop.html" class="nav-item nav-link">Users list</a>
                            <a href="Subjects" class="nav-item nav-link">Subjects list</a>
                            <a href="Subjects" class="nav-item nav-link">Category list</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                                <div class="dropdown-menu m-0 bg-secondary rounded-0">
                                    <a href="cart.html" class="dropdown-item">Cart</a>
                                    <a href="chackout.html" class="dropdown-item">Chackout</a>
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

        <div class="container  mt-5"> 
            <div class="row">
                <div class="col-md-2 border-4 border-black mt-5" style="padding-right: 0px;">
                    <jsp:include page="/admin/adminStrator.jsp"/>
                </div>
                <div class="col-md-10">
                    <div class="col-md-8 row mt-5">
                        <div class="row">
                            <div class="col-md-5">
                                <form class="input-group" action="userslist" method="post">
                                    <input class="form-control" type="text" name="searchTerm" placeholder="Enter Name or Email">
                                    <button class="btn btn-success" type="submit">Search</button>
                                </form>
                            </div>
                            <div class="col-md-3">
                                <select class="form-select" onchange="filterByRole(this.value)">
                                    <option value="">Filter by Role: All</option>
                                    <option value="1">Admin</option>
                                    <option value="2">Expert</option>
                                    <option value="3">Learner</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <select class="form-select" onchange="filterByStatus(this.value)">
                                    <option value="">Filter by Status: All</option>
                                    <option value="0">Inactive</option>
                                    <option value="1">Active</option>
                                </select>
                            </div>
                        </div>
                    </div>


                    <div class="row">

                        <div class="col-md-10 justify-content-center mt-5 text-dark">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th onclick="sortByID()">ID</th>
                                        <th onclick="sortByName()">Full Name</th>
                                        <th onclick="sortByAge()">Date Of Birth</th>
                                        <th>Email</th>
                                        <th onclick="sortByRole()">Role</th>
                                        <th onclick="sortByStatus()">Status</th>
                                        <th> Detail </th>
                                    </tr>        
                                </thead>
                                <tbody>
                                    <c:forEach var="b" items="${requestScope.data}">
                                        <tr>
                                            <td>${b.getUserID()}</td>
                                            <td>${b.getFullName()}</td>
                                            <td>${b.getDoB()}</td>
                                            <td>${b.getMail()}</td>
                                            <c:choose>
                                                <c:when test="${b.getRole() eq 1}">
                                                    <td>Admin</td>
                                                </c:when>
                                                <c:when test="${b.getRole() eq 2}">
                                                    <td>Expert</td>
                                                </c:when>
                                                <c:when test="${b.getRole() eq 3}">
                                                    <td>Learner</td>
                                                </c:when>
                                            </c:choose>

                                            <c:choose>
                                                <c:when test="${b.getStatus() eq 1}">
                                                    <td>Active</td>
                                                </c:when>
                                                <c:when test="${b.getStatus() eq 0}">
                                                    <td>Inactive</td>
                                                </c:when>
                                            </c:choose>
                                            <td><a href="detailuser?ID=${b.getUserID()}" onclick)">Detail</a></td>
                                        </tr>
                                    </c:forEach>
                                <script>
                                    function getRoleName(role) {
                                        switch (role) {
                                            case 1:
                                                return "Admin";
                                            case 2:
                                                return "Expert";
                                            case 3:
                                                return "Learner";
                                        }
                                    }
                                </script>
                                </tbody>    
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/footer.jsp"/>

        <script>
            const sortByID = () => {
                const tbody = document.querySelector('.table tbody');
                const rows = Array.from(tbody.querySelectorAll('tr'));

                rows.sort((a, b) => {
                    const idA = parseInt(a.querySelector('td:nth-child(1)').textContent.trim());
                    const idB = parseInt(b.querySelector('td:nth-child(1)').textContent.trim());
                    return idA - idB;
                });

                rows.forEach(row => {
                    tbody.appendChild(row);
                });
            };


            const sortByName = () => {
                const tbody = document.querySelector('.table tbody');
                const rows = Array.from(tbody.querySelectorAll('tr'));

                rows.sort((a, b) => {
                    const nameA = a.querySelector('td:nth-child(2)').textContent.trim().toLowerCase();
                    const nameB = b.querySelector('td:nth-child(2)').textContent.trim().toLowerCase();
                    return nameA.localeCompare(nameB);
                });

                rows.forEach(row => {
                    tbody.appendChild(row);
                });
            };

            const sortByAge = () => {
                const tbody = document.querySelector('.table tbody');
                const rows = Array.from(tbody.querySelectorAll('tr'));

                rows.sort((a, b) => {
                    const dateOfBirthA = new Date(a.querySelector('td:nth-child(3)').textContent.trim());
                    const dateOfBirthB = new Date(b.querySelector('td:nth-child(3)').textContent.trim());
                    return dateOfBirthA - dateOfBirthB;
                });

                rows.forEach(row => {
                    tbody.appendChild(row);
                });
            };

            const sortByRole = () => {
                const tbody = document.querySelector('.table tbody');
                const rows = Array.from(tbody.querySelectorAll('tr'));

                rows.sort((a, b) => {
                    const roleA = a.querySelector('td:nth-child(5)').textContent.trim();
                    const roleB = b.querySelector('td:nth-child(5)').textContent.trim();
                    return roleA.localeCompare(roleB);
                });

                rows.forEach(row => {
                    tbody.appendChild(row);
                });
            };

            const sortByStatus = () => {
                const tbody = document.querySelector('.table tbody');
                const rows = Array.from(tbody.querySelectorAll('tr'));

                rows.sort((a, b) => {
                    const statusA = a.querySelector('td:nth-child(6)').textContent.trim();
                    const statusB = b.querySelector('td:nth-child(6)').textContent.trim();
                    return statusA.localeCompare(statusB);
                });

                rows.forEach(row => {
                    tbody.appendChild(row);
                });
            };


            const filterByRole = (role) => {
                const rows = document.querySelectorAll('.table tbody tr');

                rows.forEach(row => {
                    const roleCell = row.querySelector('td:nth-child(5)').textContent.trim();
                    if (role === "" || roleCell === getRoleName(parseInt(role))) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            };


            const filterByStatus = (status) => {
                const rows = document.querySelectorAll('.table tbody tr');

                rows.forEach(row => {
                    const statusCell = row.querySelector('td:nth-child(6)').textContent.trim();
                    if (status === "" || statusCell === (status === "1" ? "Active" : "Inactive")) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            };




        </script>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <!-- Template Javascript -->
        <script src="js/main.js"></script>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
