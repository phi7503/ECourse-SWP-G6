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
public class EditQuestion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int CourseID = -1;
        int LessonID = -1;
        int QuizID = -1;
        int QuestionID = -1;

        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
            LessonID = Integer.parseInt(request.getParameter("LessonID"));
            QuizID = Integer.parseInt(request.getParameter("QuizID"));
            QuestionID = Integer.parseInt(request.getParameter("QuestionID"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }

        Course curCourse = CourseDAO.INS.getCourseById(CourseID);
        Lesson curLesson = LessonDAO.INS.getLessonByID(CourseID, LessonID);
        Quiz curQuiz = QuizDAO.INS.getQuiz(CourseID, LessonID, QuizID);
        Question curQues = QuestionDAO.INS.getQuestion(CourseID, LessonID, QuizID, QuestionID);

        request.setAttribute("curCourse", curCourse);
        request.setAttribute("curLesson", curLesson);
        request.setAttribute("curQuiz", curQuiz);
        request.setAttribute("curQues", curQues);
        request.setAttribute("QuestionINS", QuestionDAO.INS);
        request.getRequestDispatcher("/Web/EditQuestion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int CourseID = Integer.parseInt(request.getParameter("CourseID"));
        int LessonID = Integer.parseInt(request.getParameter("LessonID"));
        int QuizID = Integer.parseInt(request.getParameter("QuizID"));
        int QuestionID = Integer.parseInt(request.getParameter("QuestionID"));

        String Question = request.getParameter("Question");
        String Explain = request.getParameter("Explain");
        int Status = Integer.parseInt(request.getParameter("Status"));

        QuestionDAO.INS.updateQuestion(CourseID, LessonID, QuizID, QuestionID, Question, Explain, Status);

        int Correct = Integer.parseInt(request.getParameter("Correct"));
        List<Answer> AnswerList = QuestionDAO.INS.loadQuestionAnswer(CourseID, LessonID, QuizID, QuestionID);

        for (Answer ans : AnswerList) {
            String Description = request.getParameter("Answer" + ans.getAnswerID());
            if (Correct == ans.getAnswerID()) {
                QuestionDAO.INS.updateAnswer(CourseID, LessonID, QuizID, QuestionID, ans.getAnswerID(), Description, 2);
            } else {
                QuestionDAO.INS.updateAnswer(CourseID, LessonID, QuizID, QuestionID, ans.getAnswerID(), Description, 1);
            }
        }

        request.setAttribute("CourseID", CourseID);
        request.setAttribute("LessonID", LessonID);
        request.setAttribute("QuizID", QuizID);
        request.getRequestDispatcher("/QuestionList").forward(request, response);
    }

}
