
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AnswerDAO;
import DAO.QuestionDAO;
import DAO.QuizDAO;
import DAO.UserDAO;
import Models.Question;
import Models.User;
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
public class Review extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");

        int CourseID = -1;
        int LessonID = -1;
        int QuizID = -1;
        int AttemptID = -1;        
        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
            LessonID = Integer.parseInt(request.getParameter("LessonID"));
            QuizID = Integer.parseInt(request.getParameter("QuizID"));
            AttemptID = Integer.parseInt(request.getParameter("AttemptID"));            
        } catch (Exception e) {
            request.getRequestDispatcher("/404.html").forward(request, response);
        }
        
        List<Question> QuestionList = UserDAO.INS.getListQuestionOnAttempt(u.getUserID(), CourseID, LessonID, QuizID, AttemptID);             

        request.setAttribute("QuestionINS", QuestionDAO.INS);
        request.setAttribute("QuestionList", QuestionList);
        request.setAttribute("CourseID", CourseID);
        request.setAttribute("LessonID", LessonID);
        request.setAttribute("QuizID", QuizID);
        request.setAttribute("AttemptID", AttemptID);        
        
        request.getRequestDispatcher("/Web/Review.jsp").forward(request, response);

    }
}
