/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.TestContent;

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
public class EditQuiz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int CourseID = -1;
        int LessonID = -1;
        int QuizID = -1;

        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
            LessonID = Integer.parseInt(request.getParameter("LessonID"));
            QuizID = Integer.parseInt(request.getParameter("QuizID"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }

        Course curCourse = CourseDAO.INS.getCourseById(CourseID);
        Lesson curLesson = LessonDAO.INS.getLessonByID(CourseID, LessonID);
        Quiz curQuiz = QuizDAO.INS.getQuiz(CourseID, LessonID, QuizID);

        request.setAttribute("curCourse", curCourse);
        request.setAttribute("curLesson", curLesson);
        request.setAttribute("curQuiz", curQuiz);
        request.getRequestDispatcher("/Web/EditQuiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int CourseID = -1;
        int LessonID = -1;
        int QuizID = -1;
        int NoQ = -1;
        int TimeLimit = -1;

        CourseID = Integer.parseInt(request.getParameter("CourseID"));
        LessonID = Integer.parseInt(request.getParameter("LessonID"));
        QuizID = Integer.parseInt(request.getParameter("QuizID"));
        try {

            NoQ = Integer.parseInt(request.getParameter("NoQ"));
            TimeLimit = Integer.parseInt(request.getParameter("TimeLimit"));
        } catch (NumberFormatException e) {
            Course curCourse = CourseDAO.INS.getCourseById(CourseID);
            Lesson curLesson = LessonDAO.INS.getLessonByID(CourseID, LessonID);
            Quiz curQuiz = QuizDAO.INS.getQuiz(CourseID, LessonID, QuizID);

            request.setAttribute("curCourse", curCourse);
            request.setAttribute("curLesson", curLesson);
            request.setAttribute("curQuiz", curQuiz);
            request.setAttribute("inform", "Input values are not valid!");
            request.getRequestDispatcher("/Web/EditQuiz.jsp").forward(request, response);
            return;
        }

        String QuizName = request.getParameter("QuizName");

        QuizDAO.INS.updateQuiz(CourseID, LessonID, QuizID, QuizName, NoQ, TimeLimit);
        
        Course curCourse = CourseDAO.INS.getCourseById(CourseID);
            Lesson curLesson = LessonDAO.INS.getLessonByID(CourseID, LessonID);
            Quiz curQuiz = QuizDAO.INS.getQuiz(CourseID, LessonID, QuizID);

            request.setAttribute("curCourse", curCourse);
            request.setAttribute("curLesson", curLesson);
            request.setAttribute("curQuiz", curQuiz);
            request.setAttribute("inform", "Quiz Update Successful!");
            request.getRequestDispatcher("/Web/EditQuiz.jsp").forward(request, response);
    }

}
