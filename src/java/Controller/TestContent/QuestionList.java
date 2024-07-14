/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.TestContent;

import DAO.*;
import Models.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author hi2ot
 */
public class QuestionList extends HttpServlet {

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
        
        List<Question> QuestionList = QuestionDAO.INS.getQuestionList(CourseID, LessonID, QuizID);

        int index = -1;
        int total = -1;
        try {
            index = Integer.parseInt(request.getParameter("index"));
            total = Integer.parseInt(request.getParameter("total"));
        } catch (NumberFormatException e) {
            index = 0;
            total = 0;
        }
        String btn = request.getParameter("btn");
        if (btn != null) {
            try {
                int btnNum = Integer.parseInt(btn);
                index = btnNum;
            } catch (NumberFormatException e) {
                index = 0;
            }            
        }
        
        String btnHome = request.getParameter("btnHome");
        String btnPre = request.getParameter("btnPre");
        String btnNext = request.getParameter("btnNext");
        String btnEnd = request.getParameter("btnEnd");
        
        if (btnHome != null) {
            index = 0;            
        } 

        if (btnPre != null) {
            index--;
        }
        
        if (btnNext != null) {
            index++;
        }
        
        if (btnEnd != null) {
            index = total - 1;
        }

        Paging paging = new Paging(QuestionList.size(), 6, index);
        paging.calc();

        request.setAttribute("paging", paging);
        request.setAttribute("QuestionList", QuestionList);
        request.setAttribute("curCourse", curCourse);
        request.setAttribute("curLesson", curLesson);
        request.setAttribute("curQuiz", curQuiz);
        request.setAttribute("QuestionINS", QuestionDAO.INS);
        request.getRequestDispatcher("/Web/QuestionList.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
