<%-- 
    Document   : ListSubject
    Created on : Jun 5, 2024, 9:47:34 PM
    Author     : Nhun Nhun
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List Subject</h1>
        <table>
            <tbody>
                <c:forEach var="sub" items="${subjects}">
                    <tr>
                        <td><a href="./lessionDetail?id=${sub.id}">${sub.name}</a></td>
                    </tr>

            </c:forEach>
            </tbody>
        </table>
    </body>
</html>
