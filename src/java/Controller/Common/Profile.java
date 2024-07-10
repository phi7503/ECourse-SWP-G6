/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Common;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import Models.*;
import DAO.*;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author hi2ot
 */
public class Profile extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        
        List<Course> CourseList = UserDAO.INS.loadUserOwnCourse(u.getUserID());
        SEQuestionDAO.INS.load();
        List<SEQuestion> SEQuestionList = SEQuestionDAO.INS.getQel();
        
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        
        request.setAttribute("maxDate", date);
        request.setAttribute("SEQuestionList", SEQuestionList);
        request.setAttribute("CourseList", CourseList);
        request.getRequestDispatcher("/Web/Profile.jsp").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        
        String Fullname = request.getParameter("FullName");
        String dob = request.getParameter("dob");
        int SQ = Integer.parseInt(request.getParameter("SQ"));
        String Answer = request.getParameter("Answer");
        
        UserDAO.INS.updateProfile(u.getUserID(), Fullname, dob, SQ, Answer);
        
        ses.setAttribute("User", UserDAO.INS.getUserByName(u.getUserName()));
        
        request.setAttribute("inform", "Profile Updated Successfully!");
        
        doGet(request, response);
    }

}
