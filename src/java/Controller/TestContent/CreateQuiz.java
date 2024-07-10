/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.TestContent;

import DAO.CourseDAO;
import DAO.LessonDAO;
import DAO.QuizDAO;
import Models.Course;
import Models.Lesson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author hi2ot
 */
public class CreateQuiz extends HttpServlet {
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int CourseID = -1;
        int LessonID = -1;

        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
            LessonID = Integer.parseInt(request.getParameter("LessonID"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }

        Course curCourse = CourseDAO.INS.getCourseById(CourseID);
        Lesson curLesson = LessonDAO.INS.getLessonByID(CourseID, LessonID);

        request.setAttribute("curCourse", curCourse);
        request.setAttribute("curLesson", curLesson);
        request.getRequestDispatcher("/Web/CreateQuiz.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int CourseID = Integer.parseInt(request.getParameter("CourseID"));
        int LessonID = Integer.parseInt(request.getParameter("LessonID"));
        String QuizName = request.getParameter("QuizName");
        int NoQ = Integer.parseInt(request.getParameter("NoQ"));
        int TimeLimit = Integer.parseInt(request.getParameter("TimeLimit"));
        
        QuizDAO.INS.createNewQuiz(CourseID, LessonID, QuizName, NoQ, TimeLimit);
        
        request.setAttribute("CourseID", CourseID);
        request.setAttribute("LessonID", LessonID);
        request.getRequestDispatcher("/EditContent").forward(request, response);
    }

}
