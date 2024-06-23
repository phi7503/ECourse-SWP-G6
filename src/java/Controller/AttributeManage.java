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
import jakarta.servlet.http.HttpSession;
import Models.*;
import java.util.*;
import DAO.*;

/**
 *
 * @author hi2ot
 */
public class AttributeManage extends HttpServlet {       
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        if (u.getRole() == 1) {            
            List<Category> CategoryList = CourseDAO.INS.loadCategoryList();
            List<Subject> SubjectList = CourseDAO.INS.loadSubjectList();
            request.setAttribute("CategoryList", CategoryList);
            request.setAttribute("SubjectList", SubjectList);
            request.getRequestDispatcher("/Web/AttributeManage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/404.html").forward(request, response);
        }
        
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("/Web/AttributeManage.jsp").forward(request, response);
    }

}
