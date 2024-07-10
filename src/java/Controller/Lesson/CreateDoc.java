/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Lesson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.*;
import DAO.*;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.io.PrintWriter;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
/**
 *
 * @author hi2ot
 */
public class CreateDoc extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int CourseID = -1;
        int LessonID = -1;

        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
            LessonID = Integer.parseInt(request.getParameter("LessonID"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }

        Course curCourse = CourseDAO.INS.getCourseById(CourseID);
        Lesson curLesson = LessonDAO.INS.getLessonByID(CourseID, LessonID);

        request.setAttribute("curCourse", curCourse);
        request.setAttribute("curLesson", curLesson);
        request.getRequestDispatcher("/Web/CreateDoc.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int CourseID = Integer.parseInt(request.getParameter("CourseID"));
        int LessonID = Integer.parseInt(request.getParameter("LessonID"));
        String Title = request.getParameter("Title");
        String Description = request.getParameter("Description");
        
        Part filePart = request.getPart("Doc");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        String uploadPath = "E:\\PRJ301\\JPECourse\\web\\dwnlib";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        File file = new File(uploadPath + File.separator + fileName);
        filePart.write(file.getAbsolutePath());
        
        LessonDAO.INS.createNewDoc(CourseID, LessonID, Title, Description, fileName);
        response.sendRedirect(request.getContextPath() + "/CourseManage");
    }
}
