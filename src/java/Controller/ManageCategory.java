/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CourseDAO;
import Models.Category;
import Models.Subject;
import Models.User;
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
public class ManageCategory extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        if (u.getRole() == 1) {
            List<Category> CategoryList = CourseDAO.INS.loadCategoryList();
            for (Category cate : CategoryList) {
                String Del = request.getParameter("DelCate" + cate.getCategoryID());
                String Upd = request.getParameter("UpdCate" + cate.getCategoryID());
                if (Del != null) {
                    CourseDAO.INS.deleteCategory(cate.getCategoryID());
                    response.sendRedirect(request.getContextPath() + "/AttributeManage");
                    break;
                }
                if (Upd != null) {
                    request.setAttribute("cate", cate); 
                    request.getRequestDispatcher("/Web/UpdateCategory.jsp").forward(request, response);
                    break;
                }                    
            }
            
        } else {
            response.sendRedirect(request.getContextPath() + "/404.html");
        }
    }

}
