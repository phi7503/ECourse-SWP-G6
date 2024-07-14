/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Lesson;

import DAO.LessonDAO;
import Models.Lesson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author hi2ot
 */
public class EditLesson extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int CourseID = -1;
        int LessonID = -1;
        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
            LessonID = Integer.parseInt(request.getParameter("LessonID"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/CourseManage");
            return;
        }
        
        Lesson curLesson = LessonDAO.INS.getLessonByID(CourseID, LessonID);
        
        request.setAttribute("CourseID", CourseID);
        request.setAttribute("curLesson", curLesson);
        request.getRequestDispatcher("/Web/EditLesson.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        int CourseID = Integer.parseInt(request.getParameter("CourseID"));
        int LessonID = Integer.parseInt(request.getParameter("LessonID"));
        String LessonName = request.getParameter("LessonName");
        String Description = request.getParameter("Description");                
        
        LessonDAO.INS.updateLesson(CourseID, LessonID, LessonName, Description);
                
        request.setAttribute("CourseID", CourseID);
        request.getRequestDispatcher("/EditContent").forward(request, response);
    }

}
