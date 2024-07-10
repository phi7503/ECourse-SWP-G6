/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Common;

import DAO.SEQuestionDAO;
import DAO.UserDAO;
import Models.Course;
import Models.SEQuestion;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hi2ot
 */
public class ChangePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        List<Course> CourseList = UserDAO.INS.loadUserOwnCourse(u.getUserID());

        request.setAttribute("CourseList", CourseList);
        request.getRequestDispatcher("/Web/ChangePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession ses = request.getSession();
            User u = (User) ses.getAttribute("User");

            String oldpass = request.getParameter("oldpass");
            String newpass = request.getParameter("newpass");
            String confirm = request.getParameter("confirm");

            String ans = UserDAO.INS.changePass(u.getUserID(), oldpass, newpass, confirm);

            if (ans == null) {
                List<Course> CourseList = UserDAO.INS.loadUserOwnCourse(u.getUserID());
                SEQuestionDAO.INS.load();
                List<SEQuestion> SEQuestionList = SEQuestionDAO.INS.getQel();

                java.sql.Date date = new java.sql.Date(new Date().getTime());

                request.setAttribute("maxDate", date);
                request.setAttribute("SEQuestionList", SEQuestionList);
                request.setAttribute("CourseList", CourseList);
                request.setAttribute("inform", "Password Changed Successfull!");
                request.getRequestDispatcher("/Web/Profile.jsp").forward(request, response);
            } else {
                request.setAttribute("inform", ans);
                doGet(request, response);
            }
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("inform", "Undefined Error!");
            doGet(request, response);
        }

    }

}
