/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Learning;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.*;
import DAO.*;
import jakarta.servlet.http.HttpSession;
import java.util.*;

/**
 *
 * @author hi2ot
 */
public class CourseContent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        
        String sDocID = request.getParameter("DocID");

        int DocID = -1;
        int CourseID = -1;
        int LessonID = -1;
        try {
            DocID = Integer.parseInt(sDocID);
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
            LessonID = Integer.parseInt(request.getParameter("LessonID"));
        } catch (NumberFormatException e) {
            DocID = -1;
        }
        if (DocID != -1) {
            UserDAO.INS.updateDocProgress(u.getUserID(), CourseID, LessonID, DocID);
        }        

        CourseID = -1;
        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
        } catch (NumberFormatException e) {
            CourseID = -1;
        }

        if (CourseID == -1) {
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }

        if (UserDAO.INS.checkOwnCourse(u.getUserID(), CourseID) == 1) {
            Course curCourse = CourseDAO.INS.getCourseById(CourseID);
            request.setAttribute("curCourse", curCourse);
            request.setAttribute("UserINS", UserDAO.INS);
            request.setAttribute("QuizINS", QuizDAO.INS);
            request.setAttribute("LessonINS", LessonDAO.INS);
            request.getRequestDispatcher("/Web/CourseContent.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/404.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
