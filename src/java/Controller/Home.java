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

/**
 *
 * @author hi2ot
 */
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        if (u == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
        } else {
            QuizDAO.INS.load();
            request.setAttribute("QuizINS", QuizDAO.INS);
            request.getRequestDispatcher("/Web/Home.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Logout = request.getParameter("Logout");
        String goQuiz = request.getParameter("goQuiz");
        if (Logout != null) {
            HttpSession ses = request.getSession();
            ses.removeAttribute("User");
            response.sendRedirect(request.getContextPath() + "/Login");        
        } else {
            doGet(request, response);
        }
    }

}
