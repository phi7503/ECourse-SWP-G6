/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.SEQuestionDAO;
import DAO.UserDAO;
import Models.SEQuestion;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/ForgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SEQuestionDAO.INS.load();
        List<SEQuestion> sEQuestions = SEQuestionDAO.INS.getQel();
        request.setAttribute("SEQuestions", sEQuestions);

        request.getRequestDispatcher("/Web/ForgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("Username");
        String mail = request.getParameter("mail");
        String questionId = request.getParameter("question");
        String answer = request.getParameter("answer");
        String newPass = request.getParameter("newPass");

        try {
            User user = UserDAO.INS.checkResetPass(userName, mail, Integer.parseInt(questionId), answer);

            if (user == null) {
                throw new Exception();
            }

            user.setPassword(newPass);

            UserDAO.INS.change(user);

            request.setAttribute("err", "Password changed successfully");

        } catch (Exception e) {
            SEQuestionDAO.INS.load();

            request.setAttribute("suc", "The information does not match, please check again");
        }
        List<SEQuestion> sEQuestions = SEQuestionDAO.INS.getQel();
        request.setAttribute("SEQuestions", sEQuestions);

        request.getRequestDispatcher("/Web/ForgotPassword.jsp").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
