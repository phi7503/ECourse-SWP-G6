
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
public class QuizNavigate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/404.html").forward(req, resp);
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
        
        Attempt NewAttempt = UserDAO.INS.createNewUserQuizAttempt(u.getUserID(), CourseID, LessonID, QuizID);
        List<Question> QuestionList = UserDAO.INS.createNewQuestionList(u.getUserID(), CourseID, LessonID, QuizID, NewAttempt.getAttemptID());
        
        request.setAttribute("QuestionINS", QuestionDAO.INS);
        request.setAttribute("QuestionList", QuestionList);
        request.setAttribute("CourseID", CourseID);
        request.setAttribute("LessonID", LessonID);
        request.setAttribute("QuizID", QuizID);
        request.setAttribute("AttemptID", NewAttempt.getAttemptID());
        request.setAttribute("index", 0);
        request.setAttribute("Question", QuestionList.get(0));
        request.setAttribute("Time", 900);        
        request.getRequestDispatcher("/Web/Quizing.jsp").forward(request, response);
    }

}
