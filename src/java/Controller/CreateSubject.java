/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.*;
import DAO.*;
import java.util.*;

/**
 *
 * @author hi2ot
 */
public class CreateSubject extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String SubjectName = request.getParameter("SubjectName");
        if (SubjectName != null) {
            List<Subject> SubjectList = CourseDAO.INS.loadSubjectList();
            CourseDAO.INS.addSubject(SubjectList.size() + 1, SubjectName);
            response.sendRedirect(request.getContextPath() + "/Home");
        } else {
            request.getRequestDispatcher("/Web/CreateSubject.jsp").forward(request, response);
        }
    }

}
