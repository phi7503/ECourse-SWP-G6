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
import Models.*;
import DAO.*;
import jakarta.servlet.http.HttpSession;
import java.util.*;

/**
 *
 * @author hi2ot
 */
public class Quizing extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");

        int CourseID = -1;
        int LessonID = -1;
        int QuizID = -1;
        try {
            CourseID = Integer.parseInt(ses.getAttribute("CourseID") + "");
            LessonID = Integer.parseInt(ses.getAttribute("LessonID") + "");
            QuizID = Integer.parseInt(ses.getAttribute("QuizID") + "");
        } catch (Exception e) {
            CourseID = -1;
            LessonID = -1;
            QuizID = -1;
        }
        
        int index = 0;
        Attempt newAttempt = UserDAO.INS.getNewestAttempt(u.getUserID(), CourseID, LessonID, QuizID);
        int AttemptID = newAttempt.getAttemptID();
        Date date = new Date(newAttempt.getAttemptDate().getTime());
        List<Question> QuestionList = UserDAO.INS.getListQuestionOnAttempt(u.getUserID(), CourseID, LessonID, QuizID, AttemptID);
        
        request.setAttribute("QuestionINS", QuestionDAO.INS);
        request.setAttribute("QuestionList", QuestionList);
        request.setAttribute("CourseID", CourseID);
        request.setAttribute("LessonID", LessonID);
        request.setAttribute("QuizID", QuizID);
        request.setAttribute("AttemptID", AttemptID);
        request.setAttribute("index", index);
        request.setAttribute("date", date.getTime());
        request.setAttribute("limit", QuizDAO.INS.getQuizTimeLimt(CourseID, LessonID, QuizID));
        request.setAttribute("Question", QuestionList.get(index));

        if (request.getParameter("BtnFinish") != null && request.getParameter("BtnFinish").equals("Yes")) {
            request.getRequestDispatcher("/Web/Review.jsp").forward(request, response);
            return;
        } else {           
            request.getRequestDispatcher("/Web/Quizing.jsp").forward(request, response);
            return;
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

        ses.removeAttribute("CourseID");
        ses.removeAttribute("LessonID");
        ses.removeAttribute("QuizID");
        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
            LessonID = Integer.parseInt(request.getParameter("LessonID"));
            QuizID = Integer.parseInt(request.getParameter("QuizID"));
        } catch (Exception e) {
            request.getRequestDispatcher("/404.html").forward(request, response);
            return;
        }

        int index = 0;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
            index = 0;
        }

        int AnswerID = -1;
        try {
            AnswerID = Integer.parseInt(request.getParameter("AnswerID"));
        } catch (Exception e) {
            AnswerID = -1;
        }

        Attempt newAttempt = UserDAO.INS.getNewestAttempt(u.getUserID(), CourseID, LessonID, QuizID);
        
        if (newAttempt.getFinished() == 1) {
            response.sendRedirect(request.getContextPath() + "/Home");
            return;
        }
        
        int AttemptID = newAttempt.getAttemptID();
        Date date = new Date(newAttempt.getAttemptDate().getTime());
        List<Question> QuestionList = UserDAO.INS.getListQuestionOnAttempt(u.getUserID(), CourseID, LessonID, QuizID, AttemptID);
        UserDAO.INS.updateUserAnswer(u.getUserID(), AttemptID, CourseID, LessonID, QuizID, QuestionList.get(index).getQuestionID(), AnswerID);                

        if (request.getParameter("BtnPrev") != null) {
            index--;
        }
        if (request.getParameter("BtnNext") != null) {
            index++;
        }
        for (int i = 0; i < QuestionList.size(); i++) {
            if (request.getParameter("Btn" + i) != null) {
                index = i;
                break;
            }
        }

        request.setAttribute("QuestionINS", QuestionDAO.INS);
        request.setAttribute("QuestionList", QuestionList);
        request.setAttribute("CourseID", CourseID);
        request.setAttribute("LessonID", LessonID);
        request.setAttribute("QuizID", QuizID);
        request.setAttribute("AttemptID", AttemptID);
        request.setAttribute("index", index);
        request.setAttribute("date", date.getTime());
        request.setAttribute("limit", QuizDAO.INS.getQuizTimeLimt(CourseID, LessonID, QuizID));
        request.setAttribute("Question", QuestionList.get(index));                

        if (request.getParameter("BtnFinish") != null && request.getParameter("BtnFinish").equals("Yes")) {
            java.util.Date submitted = new java.util.Date();
            UserDAO.INS.updateSubmittedTime(u.getUserID(), CourseID, LessonID, QuizID, AttemptID, submitted);
            request.getRequestDispatcher("/Web/Review.jsp").forward(request, response);            
        } else {            
            request.getRequestDispatcher("/Web/Quizing.jsp").forward(request, response);            
        }
    }
}
