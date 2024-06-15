<%-- 
    Document   : adminstrator
    Created on : Jun 8, 2024, 2:35:45 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator Panel</title>
        <style>
            .sidebar { /* Green background */
                color: black; /* Black text */
                padding: 15px;
                height: 100vh; /* Full height */
            }
            .sidebar a {
                color: black; /* Black text for links */
                text-decoration: none;
                display: block;
                padding: 10px 0;
            }
            .sidebar a:hover {
                background-color: #218838; /* Darker green on hover */
                color: white; /* White text on hover */
            }
            .menu-item {
                margin-top: 10px;
            }
            #logo {
                margin-bottom: 20px;
            }
        </style>
        <!-- Icon Font Stylesheet -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body>
        <div class="sidebar">
            <div id="logo">
                <a href="${pageContext.request.contextPath}/userslist">
                    <i class="bi bi-house-fill" style="padding-right: 7px"></i>User List
                </a>
            </div>
            <div class="menu-item">
                <a href="${pageContext.request.contextPath}/subjectlist">
                    <i class="bi bi-journal" style="padding-right: 7px"></i> Subject List
                </a>
            </div>
            <div class="menu-item">
                <a href="${pageContext.request.contextPath}/settings">
                    <i class="bi bi-gear-fill" style="padding-right: 7px"></i> Setting List
                </a>
            </div>

<!--            <div class="menu-item flex-column" onclick="toggleSubmenu(this)">
                <a><i class="bi bi-border-width" style="padding-right: 7px"></i> Sản Phẩm</a>

                <ul style="" class="submenu">
                    <li class="submenu-item"><a style="width: 120%" href="${pageContext.request.contextPath}/managerproduct"><i class="bi bi-bag-dash-fill" style="padding-right: 7px"></i>Danh sách Sản Phẩm</a></li>
                    <li class="submenu-item"><a style="width: 120%" href="${pageContext.request.contextPath}/managerbrand"><i class="bi bi-buildings" style="padding-right: 7px"></i>Danh Sách Thương Hiệu</a></li>
                    <li class="submenu-item"><a style="width: 120%" href="${pageContext.request.contextPath}/managersport"><i class="bi bi-cookie" style="padding-right: 7px" ></i>Môn thể thao</a></li>
                    <li class="submenu-item"><a style="width: 120%" href="${pageContext.request.contextPath}/managersize"><i class="bi bi-box2" style="padding-right: 7px" ></i>Kích cỡ</a></li>
                    <li class="submenu-item"><a style="width: 120%" href="${pageContext.request.contextPath}/managercolor"><i class="bi bi-palette" style="padding-right: 7px" ></i>Màu sắc</a></li>
                    <li class="submenu-item"><a style="width: 120%" href="${pageContext.request.contextPath}/managercategory"><i class="bi bi-journals" style="padding-right: 7px" ></i>Danh sách danh mục</a></li>
                    <li class="submenu-item"><a style="width: 120%" href="${pageContext.request.contextPath}/managersubcategory"><i class="bi bi-journal" style="padding-right: 7px" ></i>Danh sách thể loại</a></li>
                </ul>
            </div>-->
        </div>

<!--        <script>
            function toggleSubmenu(element) {
                element.classList.toggle('active');
            }
        </script>-->
    </body>
</html>
