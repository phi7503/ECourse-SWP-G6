/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Public;

import DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import Models.*;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author hi2ot
 */
public class CourseShop extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        
        List<Category> CategoryList = CourseDAO.INS.loadCategoryList();
        List<Subject> SubjectList = CourseDAO.INS.loadSubjectList();

        List<Category> checkedCategory = new ArrayList<>();
        List<Subject> checkedSubject = new ArrayList<>();

        List<Integer> catNum = new ArrayList<>();
        List<Integer> sujNum = new ArrayList<>();

        for (Category cat : CategoryList) {
            if (request.getParameter("Category" + cat.getCategoryID()) != null) {
                catNum.add(cat.getCategoryID());
                checkedCategory.add(cat);
            }
        }

        for (Subject suj : SubjectList) {
            if (request.getParameter("Subject" + suj.getSubjectID()) != null) {
                sujNum.add(suj.getSubjectID());
                checkedSubject.add(suj);
            }
        }

        List<Course> CourseList = new ArrayList<>();
        for(Course x : CourseDAO.INS.getCourseList(checkedCategory, checkedSubject)) {
            if (UserDAO.INS.checkOwnCourse(u.getUserID(), x.getCourseID()) == 0) {
                CourseList.add(x);
            }                                
        }

        int index = -1;
        int total = -1;
        try {
            index = Integer.parseInt(request.getParameter("index"));
            total = Integer.parseInt(request.getParameter("total"));
        } catch (NumberFormatException e) {
            index = 0;
            total = 0;
        }
        String btn = request.getParameter("btn");
        if (btn != null) {
            try {
                int btnNum = Integer.parseInt(btn);
                index = btnNum;
            } catch (NumberFormatException e) {
                index = 0;
            }            
        }
        
        String btnHome = request.getParameter("btnHome");
        String btnPre = request.getParameter("btnPre");
        String btnNext = request.getParameter("btnNext");
        String btnEnd = request.getParameter("btnEnd");
        
        if (btnHome != null) {
            index = 0;            
        } 

        if (btnPre != null) {
            index--;
        }
        
        if (btnNext != null) {
            index++;
        }
        
        if (btnEnd != null) {
            index = total - 1;
        }

        Paging paging = new Paging(CourseList.size(), 6, index);
        paging.calc();

        request.setAttribute("paging", paging);
        request.setAttribute("UserINS", UserDAO.INS);
        request.setAttribute("catNum", catNum);
        request.setAttribute("sujNum", sujNum);
        request.setAttribute("SubjectList", SubjectList);
        request.setAttribute("CourseList", CourseList);
        request.setAttribute("CategoryList", CategoryList);
        request.setAttribute("CourseINS", CourseDAO.INS);
        request.getRequestDispatcher("/Web/CourseShop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int buyId = -1;
        try {
            buyId = Integer.parseInt(request.getParameter("buyId"));
        } catch (NumberFormatException e) {
            doGet(request, response);
            return;
        }

        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");

        UserDAO.INS.addUserCart(u.getUserID(), buyId);
                       
        doGet(request, response);
    }

}
