/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CourseDAO;
import Models.Category;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author hi2ot
 */
public class CreateCategory extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        String CategoryName = request.getParameter("CategoryName");
        if (CategoryName != null) {
            List<Category> CategoryList = CourseDAO.INS.loadCategoryList();
            CourseDAO.INS.addCategory(CategoryList.size() + 1, CategoryName);
            response.sendRedirect(request.getContextPath() + "/Home");
        } else {
            request.getRequestDispatcher("/Web/CreateCategory.jsp").forward(request, response);
        }
        
    }
    
}
