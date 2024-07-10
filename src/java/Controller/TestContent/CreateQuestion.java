/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.TestContent;

import DAO.CourseDAO;
import DAO.LessonDAO;
import DAO.QuestionDAO;
import DAO.QuizDAO;
import Models.Answer;
import Models.Course;
import Models.Lesson;
import Models.Question;
import Models.Quiz;
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
public class CreateQuestion extends HttpServlet {

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
        request.getRequestDispatcher("/Web/CreateQuestion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int CourseID = Integer.parseInt(request.getParameter("CourseID"));
        int LessonID = Integer.parseInt(request.getParameter("LessonID"));
        int QuizID = Integer.parseInt(request.getParameter("QuizID"));        

        String Question = request.getParameter("Question");
        String Explain = request.getParameter("Explain");        
        
        int QuestionID = QuestionDAO.INS.getQuestionList(CourseID, LessonID, QuizID).size() + 1;
        
        QuestionDAO.INS.ImportQuestion(CourseID, LessonID, QuizID, QuestionID, Question, Explain);
        
        int Correct = Integer.parseInt(request.getParameter("Correct"));       

        for (int i = 1; i <= 4; i++) {
            String Description = request.getParameter("Answer" + i);
            if (Correct == i) {
                QuestionDAO.INS.ImportAnswer(CourseID, LessonID, QuizID, QuestionID, i, Description, 2);
            } else {
                QuestionDAO.INS.ImportAnswer(CourseID, LessonID, QuizID, QuestionID, i, Description, 1);
            }
        }

        Course curCourse = CourseDAO.INS.getCourseById(CourseID);
        Lesson curLesson = LessonDAO.INS.getLessonByID(CourseID, LessonID);
        Quiz curQuiz = QuizDAO.INS.getQuiz(CourseID, LessonID, QuizID);
        
        List<Question> QuestionList = QuestionDAO.INS.getQuestionList(CourseID, LessonID, QuizID);

        request.setAttribute("QuestionList", QuestionList);
        request.setAttribute("curCourse", curCourse);
        request.setAttribute("curLesson", curLesson);
        request.setAttribute("curQuiz", curQuiz);
        request.setAttribute("QuestionINS", QuestionDAO.INS);
        request.getRequestDispatcher("/QuestionList").forward(request, response);
    }
}
