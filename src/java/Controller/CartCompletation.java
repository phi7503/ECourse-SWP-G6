/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.OrderDAO;
import DAO.UserDAO;
import Models.Course;
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
public class CartCompletation extends HttpServlet {       

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {      
        response.sendRedirect(request.getContextPath() + "404.html");
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        if (u.getRole() == 4) {            
            List<Course> CourseList = UserDAO.INS.loadUserCart(u.getUserID());
            float total = 0;
            for (Course x : CourseList) {
                total += x.getPrice();
            }                  
            int OrderID = OrderDAO.INS.createOrder(u.getUserID(), total);
            for (Course x : CourseList) {                
                UserDAO.INS.addUserOwnCourse(u.getUserID(), x.getCourseID(), OrderID);
            }
            request.getRequestDispatcher("/Web/CartCompletation.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/Home");
        }        
    }

}
