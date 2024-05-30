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
        int QuizID = -1;
        try {
            QuizID = Integer.parseInt(request.getParameter("QuizID"));
        } catch (Exception e) {

        }
        if (QuizID > 0) {
            List<Question> qul = QuestionDAO.INS.loadQuestionByQuizID(QuizID);
            request.setAttribute("qul", qul);
            request.setAttribute("AnswerINS", AnswerDAO.INS);
            int index = 0;
            try {
                index = Integer.parseInt(request.getAttribute("index") + "");
            } catch (Exception e) {
            }
            if (request.getParameter("index") == null) {                                
                HttpSession ses = request.getSession();
                request.setAttribute("index", index);
                request.setAttribute("QuizID", QuizID);                
                User u = (User) ses.getAttribute("User");
                QuizDAO.INS.addUserQuizStatus(u.getUserID(), QuizID);
            }
            request.setAttribute("Question", qul.get(index));
            request.getRequestDispatcher("/Web/Quizing.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/404.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("BtnFinish") == null) {

            int Answer = -1;
            try {
                Answer = Integer.parseInt(request.getParameter("Answer"));
            } catch (Exception e) {

            }

            int QuizID = Integer.parseInt(request.getParameter("QuizID"));
            int index = Integer.parseInt(request.getParameter("index"));
            List<Question> qul = QuestionDAO.INS.loadQuestionByQuizID(1);
            if (Answer != -1) {
                HttpSession ses = request.getSession();
                User u = (User) ses.getAttribute("User");
                AnswerDAO.INS.addUserAnswer(u.getUserID(), qul.get(index).getQuestionID(), Answer);
                QuestionDAO.INS.addUserQuestionStatus(qul.get(index).getQuestionID(), u.getUserID());
            }

            if (request.getParameter("BtnPrev") != null) {
                index--;
            }
            if (request.getParameter("BtnNext") != null) {
                index++;
            }
            for (int i = 0; i < qul.size(); i++) {
                if (request.getParameter("Btn" + i) != null) {
                    index = i;
                    break;
                }
            }
            request.setAttribute("QuizID", QuizID);
            request.setAttribute("index", index);
            doGet(request, response);
        } else {
            int QuizID = Integer.parseInt(request.getParameter("QuizID"));
            request.setAttribute("QuizID", QuizID);
            request.getRequestDispatcher("/Web/Summary.jsp").forward(request, response);
        }
    }
}
