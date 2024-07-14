/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Lesson;

import DAO.*;
import Models.Course;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author hi2ot
 */
public class EditContent extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        int CourseID = -1;
        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
        } catch (NumberFormatException e) {
            CourseID = -1;
        }

        if (CourseID == -1) {
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }
        
        Course curCourse = CourseDAO.INS.getCourseById(CourseID);
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        
        if (u.getRole() != 1 && curCourse.getUserID() != u.getUserID()) {
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }
        
        request.setAttribute("curCourse", curCourse);
        request.setAttribute("UserINS", UserDAO.INS);
        request.setAttribute("QuizINS", QuizDAO.INS);
        request.setAttribute("LessonINS", LessonDAO.INS);
        request.getRequestDispatcher("/Web/EditContent.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {        
        doGet(request, response);
    }

}
