/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Lesson;

import Models.*;
import DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
/**
 *
 * @author hi2ot
 */
public class EditDoc extends HttpServlet {     

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int CourseID = -1;
        int LessonID = -1;
        int DocID = -1;

        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
            LessonID = Integer.parseInt(request.getParameter("LessonID"));
            DocID = Integer.parseInt(request.getParameter("DocID"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }

        Course curCourse = CourseDAO.INS.getCourseById(CourseID);
        Lesson curLesson = LessonDAO.INS.getLessonByID(CourseID, LessonID);
        LessonDoc curDoc = LessonDAO.INS.getDocByID(CourseID, LessonID, DocID);

        request.setAttribute("curCourse", curCourse);
        request.setAttribute("curLesson", curLesson);
        request.setAttribute("curDoc", curDoc);
        request.getRequestDispatcher("/Web/EditDoc.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int CourseID = Integer.parseInt(request.getParameter("CourseID"));
        int LessonID = Integer.parseInt(request.getParameter("LessonID"));
        int DocID = Integer.parseInt(request.getParameter("DocID"));
        String Title = request.getParameter("Title");
        String Description = request.getParameter("Description");
        
        Part filePart = request.getPart("Doc");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = "E:\\PRJ301\\JPECourse\\web\\dwnlib";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        File file = new File(uploadPath + File.separator + fileName);
        filePart.write(file.getAbsolutePath());
        
        LessonDAO.INS.updateDoc(CourseID, LessonID, DocID, Title, Description, fileName);
        response.sendRedirect(request.getContextPath() + "/CourseManage");
    }

}
