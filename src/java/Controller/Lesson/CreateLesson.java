/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Lesson;

import DAO.CourseDAO;
import DAO.LessonDAO;
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
public class CreateLesson extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int CourseID = -1;
        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/CourseManage");
            return;
        }
        
        request.setAttribute("CourseID", CourseID);
        request.getRequestDispatcher("/Web/CreateLesson.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        int CourseID = Integer.parseInt(request.getParameter("CourseID"));
        String LessonName = request.getParameter("LessonName");
        String Description = request.getParameter("Description");
        
        int LessonID = LessonDAO.INS.loadLessonByCourseID(CourseID).size() + 1;
        
        LessonDAO.INS.addLesson(CourseID, LessonID, LessonName, Description);
                
        request.setAttribute("CourseID", CourseID);
        request.getRequestDispatcher("/EditContent").forward(request, response);
    }

}
