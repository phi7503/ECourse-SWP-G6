/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Course;

import DAO.CourseDAO;
import Models.Category;
import Models.Subject;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hi2ot
 */
public class CreateCourse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("CourseINS", CourseDAO.INS);
        request.getRequestDispatcher("/Web/CreateCourse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        
        String CourseName = request.getParameter("CourseName");
        String Description = request.getParameter("Description");
        float Price = Float.parseFloat(request.getParameter("Price"));

        List<Category> CategoryList = new ArrayList<>();
        List<Subject> SubjectList = new ArrayList<>();

        for (Category cat : CourseDAO.INS.loadCategoryList()) {
            String req = request.getParameter("Category" + cat.getCategoryID());
            if (req != null) {
                CategoryList.add(cat);
            }
        }

        if (CategoryList.size() < 1) {
            request.setAttribute("inform", "Must have at least 1 category!");
            doGet(request, response);
            return;
        }

        for (Subject sub : CourseDAO.INS.loadSubjectList()) {
            String req = request.getParameter("Subject" + sub.getSubjectID());
            if (req != null) {
                SubjectList.add(sub);
            }
        }

        if (SubjectList.size() < 1) {
            request.setAttribute("inform", "Must have at least 1 subject!");
            doGet(request, response);
            return;
        }
        
        CourseDAO.INS.createNewCourse(CourseName, Description, Price, 0, CategoryList, SubjectList, u.getUserID());
        response.sendRedirect(request.getContextPath() + "/CourseManage");
    }

}
