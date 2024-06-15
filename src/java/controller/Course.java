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
import jakarta.servlet.http.HttpSession;
import Models.*;
import DAO.*;
import java.util.*;

/**
 *
 * @author hi2ot
 */
public class Course extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        if (u == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
        } else {
            int CourseID = -1;
            try {
                CourseID = Integer.parseInt(request.getParameter("CourseID"));
            } catch (Exception e) {
                request.getRequestDispatcher("/404.html").forward(request, response);
            }
            if (CourseID < 0) {
                request.getRequestDispatcher("/404.html").forward(request, response);
            } else {
                if (UserDAO.INS.checkOwnCourse(u.getUserID(), CourseID) == 1) {
                    List<Models.Lesson> LessonList = LessonDAO.INS.loadLessonByCourseID(CourseID);
                    request.setAttribute("LessonList", LessonList);
                    request.setAttribute("CourseID", CourseID);
                    request.getRequestDispatcher("/Web/Course.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/Home");
                }                
            }            
        }
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
