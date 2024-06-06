/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AnswerDAO;
import DAO.QuestionDAO;
import DAO.QuizDAO;
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
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        int QuizID = -1;
        try {
            QuizID = Integer.parseInt(request.getParameter("QuizID"));
        } catch (Exception e) {

        }
        if (QuizID > 0 && QuizDAO.INS.getUserQuizStatus(u.getUserID(), QuizID) == 1) {
            List<Question> qul = QuestionDAO.INS.loadQuestionByQuizID(QuizID);
            request.setAttribute("qul", qul);
            request.setAttribute("AnswerINS", AnswerDAO.INS);
            int index = 0;
            try {
                index = Integer.parseInt(request.getAttribute("index") + "");
            } catch (Exception e) {
            }
            if (request.getParameter("index") == null) {                
                request.setAttribute("index", index);
                request.setAttribute("QuizID", QuizID);                                
            }
            request.setAttribute("Question", qul.get(index));
            request.getRequestDispatcher("/Web/Review.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/404.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int Answer = -1;
        try {
            Answer = Integer.parseInt(request.getParameter("Answer"));
        } catch (Exception e) {

        }

        int QuizID = Integer.parseInt(request.getParameter("QuizID"));
        int index = Integer.parseInt(request.getParameter("index"));
        List<Question> qul = QuestionDAO.INS.loadQuestionByQuizID(1);

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
    }

}
