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
public class Lesson extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        if (u == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
        } else {
            int CourseID = -1;
            int LessonID = -1;
            try {
                CourseID = Integer.parseInt(request.getParameter("CourseID"));  
                LessonID = Integer.parseInt(request.getParameter("LessonID"));
            } catch (Exception e) {
                request.getRequestDispatcher("/404.html").forward(request, response);
            }
            if (CourseID > 0 && LessonID > 0) {
                List<LessonDoc> DocList = LessonDAO.INS.loadLessonDoc(CourseID, LessonID);
                List<Quiz> QuizList = QuizDAO.INS.loadQuizByLesson(CourseID, LessonID);
                request.setAttribute("DocList", DocList);
                request.setAttribute("QuizList", QuizList);
                request.setAttribute("CourseID", CourseID);
                request.setAttribute("LessonID", LessonID);
                request.getRequestDispatcher("/Web/Lesson.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/404.html").forward(request, response);
            }
        }
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
