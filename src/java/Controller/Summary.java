/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.*;
import Models.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author hi2ot
 */
public class Summary extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        if (u == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
        } else {
            int CourseID = -1;
            int LessonID = -1;
            int QuizID = -1;
            try {
                CourseID = Integer.parseInt(request.getParameter("CourseID"));
                LessonID = Integer.parseInt(request.getParameter("LessonID"));
                QuizID = Integer.parseInt(request.getParameter("QuizID"));
            } catch (Exception e) {
                request.getRequestDispatcher("/404.html").forward(request, response);
            }
            if (CourseID > 0 && LessonID > 0 && QuizID > 0) {
                Quiz quiz = QuizDAO.INS.getQuiz(CourseID, LessonID, QuizID);
                List<Attempt> AttemptList = UserDAO.INS.loadUserQuizAttempt(u.getUserID(), CourseID, LessonID, QuizID);
                request.setAttribute("Quiz", quiz);
                request.setAttribute("UserINS", UserDAO.INS);
                request.setAttribute("AttemptList", AttemptList);
                request.setAttribute("CourseID", CourseID);
                request.setAttribute("LessonID", LessonID);
                request.setAttribute("QuizID", QuizID);
                request.getRequestDispatcher("/Web/Summary.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/404.html").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        int CourseID = -1;
        int LessonID = -1;
        int QuizID = -1;
        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
            LessonID = Integer.parseInt(request.getParameter("LessonID"));
            QuizID = Integer.parseInt(request.getParameter("QuizID"));
        } catch (Exception e) {
            request.getRequestDispatcher("/404.html").forward(request, response);
        }

        Attempt atm = UserDAO.INS.getNewestAttempt(u.getUserID(), CourseID, LessonID, QuizID);
        if (atm == null || atm.getFinished() == 1) {
            Attempt NewAttempt = UserDAO.INS.createNewUserQuizAttempt(u.getUserID(), CourseID, LessonID, QuizID);
            List<Question> QuestionList = UserDAO.INS.createNewQuestionList(u.getUserID(), CourseID, LessonID, QuizID, NewAttempt.getAttemptID());
        }

        ses.setAttribute("CourseID", CourseID);
        ses.setAttribute("LessonID", LessonID);
        ses.setAttribute("QuizID", QuizID);
        response.sendRedirect(request.getContextPath() + "/Quizing");
    }

}
