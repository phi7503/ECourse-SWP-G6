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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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

            <div class="row justify-content-center mt-5">
                <div class="col-md-4">
                    <div class="input-group row">
                        <form action="searchuser" method="post">
                            <input class="col-md-8" type="text" name="searchTerm" placeholder="Enter Name or Email">
                            <button class="col-md-2" type="submit">Search</button>
                        </form>
                    </div>
                </div>
                <div class="col-md-2">
                    Filter by Role:
                    <select onchange="filterByRole(this.value)">
                        <option value="">All</option>
                        <option value="1">Admin</option>
                        <option value="2">Expert</option>
                        <option value="3">Learner</option>
                    </select>
                </div>
                <div class="col-md-2">
                    Filter by Status:
                    <select onchange="filterByStatus(this.value)">
                        <option value="">All</option>
                        <option value="0">Inactive</option>
                        <option value="1">Active</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <a href="AddUser" class="btn btn-success float-right">Add</a>
                </div>
            </div>    

            <div class="row">
                <div class="col-md-2 mt-3">
                    <ul class="list-group ">
                        <li class="list-group-item">User list</li>
                        <li class="list-group-item">Course list</li>
                        <li class="list-group-item">Settings List</li>
                        <li class="list-group-item">2 list</li>
                    </ul>
                </div>

                <div class="col-md-10 justify-content-center mt-3 text-dark">
                    <table class="table">
                        <thead>
                            <tr>
                                <th onclick="sortByID()">ID</th>
                                <th onclick="sortByName()">Full Name</th>
                                <th onclick="sortByAge()">Date Of Birth</th>
                                <th>Email</th>
                                <th onclick="sortByRole()">Role</th>
                                <th onclick="sortByStatus()">Status</th>
                                <th colspan="2">Action</th>
                            </tr>        
                        </thead>
                        <tbody>
                            <c:forEach var="s" items="${requestScope.searchResults}">
                                <tr>
                                    <td>${s.ID}</td>
                                    <td>${s.fullname}</td>
                                    <td>${s.dateOfBirth}</td>
                                    <td>${s.email}</td>
                                    <c:choose>
                                        <c:when test="${s.role eq 1}">
                                            <td>Admin</td>
                                        </c:when>
                                        <c:when test="${s.role eq 2}">
                                            <td>Expert</td>
                                        </c:when>
                                        <c:when test="${s.role eq 3}">
                                            <td>Learner</td>
                                        </c:when>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${s.status eq 1}">
                                            <td>Active</td>
                                        </c:when>
                                        <c:when test="${s.status eq 0}">
                                            <td>Inactive</td>
                                        </c:when>
                                    </c:choose>
                                    <td></td>
                                    <td><a href="edituser?ID=${s.ID}" onclick="return confirm('DO you want to edit')">Edit</a></td>
                                    <td><a href="detailuser?ID=${s.ID}" onclick)">Detail</a></td>
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

        <script>
            const sortByID = () => {
                // Lấy danh sách các hàng trong bảng
                const tbody = document.querySelector('.table tbody');
                const rows = Array.from(tbody.querySelectorAll('tr'));

                // Sắp xếp các hàng theo ID
                rows.sort((a, b) => {
                    const idA = parseInt(a.querySelector('td:nth-child(1)').textContent.trim());
                    const idB = parseInt(b.querySelector('td:nth-child(1)').textContent.trim());
                    return idA - idB;
                });

                // Sắp xếp lại các hàng trong tbody
                rows.forEach(row => {
                    tbody.appendChild(row);
                });
            };


            const sortByName = () => {
                // Lấy danh sách các hàng trong bảng
                const tbody = document.querySelector('.table tbody');
                const rows = Array.from(tbody.querySelectorAll('tr'));

                // Sắp xếp các hàng theo tên
                rows.sort((a, b) => {
                    const nameA = a.querySelector('td:nth-child(2)').textContent.trim().toLowerCase();
                    const nameB = b.querySelector('td:nth-child(2)').textContent.trim().toLowerCase();
                    return nameA.localeCompare(nameB);
                });

                // Sắp xếp lại các hàng trong tbody
                rows.forEach(row => {
                    tbody.appendChild(row);
                });
            };

            const sortByAge = () => {
                // Lấy danh sách các hàng trong bảng
                const tbody = document.querySelector('.table tbody');
                const rows = Array.from(tbody.querySelectorAll('tr'));

                // Sắp xếp các hàng theo ngày sinh
                rows.sort((a, b) => {
                    const dateOfBirthA = new Date(a.querySelector('td:nth-child(3)').textContent.trim());
                    const dateOfBirthB = new Date(b.querySelector('td:nth-child(3)').textContent.trim());
                    return dateOfBirthA - dateOfBirthB;
                });

                // Sắp xếp lại các hàng trong tbody
                rows.forEach(row => {
                    tbody.appendChild(row);
                });
            };

            const sortByRole = () => {
                // Lấy danh sách các hàng trong bảng
                const tbody = document.querySelector('.table tbody');
                const rows = Array.from(tbody.querySelectorAll('tr'));

                // Sắp xếp các hàng theo vai trò (role)
                rows.sort((a, b) => {
                    const roleA = a.querySelector('td:nth-child(5)').textContent.trim();
                    const roleB = b.querySelector('td:nth-child(5)').textContent.trim();
                    return roleA.localeCompare(roleB);
                });

                // Sắp xếp lại các hàng trong tbody
                rows.forEach(row => {
                    tbody.appendChild(row);
                });
            };

            const sortByStatus = () => {
                // Lấy danh sách các hàng trong bảng
                const tbody = document.querySelector('.table tbody');
                const rows = Array.from(tbody.querySelectorAll('tr'));

                // Sắp xếp các hàng theo trạng thái (status)
                rows.sort((a, b) => {
                    const statusA = a.querySelector('td:nth-child(6)').textContent.trim();
                    const statusB = b.querySelector('td:nth-child(6)').textContent.trim();
                    return statusA.localeCompare(statusB);
                });

                // Sắp xếp lại các hàng trong tbody
                rows.forEach(row => {
                    tbody.appendChild(row);
                });
            };


            const filterByRole = (role) => {
                // Lấy danh sách các hàng trong bảng
                const rows = document.querySelectorAll('.table tbody tr');

                // Lặp qua từng hàng và ẩn hoặc hiển thị tùy theo role
                rows.forEach(row => {
                    const roleCell = row.querySelector('td:nth-child(5)').textContent.trim();
                    if (role === "" || roleCell === getRoleName(parseInt(role))) {
                        // Hiển thị hàng nếu role trùng khớp hoặc lựa chọn là ""
                        row.style.display = '';
                    } else {
                        // Ẩn hàng nếu role không trùng khớp
                        row.style.display = 'none';
                    }
                });
            };


            const filterByStatus = (status) => {
                // Lấy danh sách các hàng trong bảng
                const rows = document.querySelectorAll('.table tbody tr');

                rows.forEach(row => {
                    const statusCell = row.querySelector('td:nth-child(6)').textContent.trim();
                    if (status === "" || statusCell === (status === "1" ? "Active" : "Inactive")) {
                        row.style.display = ''; // Hiển thị hàng nếu trạng thái trùng khớp hoặc lựa chọn là ""
                    } else {
                        row.style.display = 'none'; // Ẩn hàng nếu trạng thái không trùng khớp
                    }
                });
            };




        </script>
        //filterbyyearnuanhe


        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
