/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package ControllerAdmin;

<<<<<<<< HEAD:src/java/ControllerAdmin/SubjectDetailServlet.java
import DAO.LessonDAO;
import Models.Lesson;
========
import DAO.UserDAO;
>>>>>>>> 01c855e1d6039d1665043ed687ad7fa980f157a6:src/java/ControllerAdmin/DetailUser.java
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
<<<<<<<< HEAD:src/java/ControllerAdmin/SubjectDetailServlet.java
public class SubjectDetailServlet extends HttpServlet {
========
//@WebServlet(name="DetailUser", urlPatterns={"/detailuser"})
public class DetailUser extends HttpServlet {
>>>>>>>> 01c855e1d6039d1665043ed687ad7fa980f157a6:src/java/ControllerAdmin/DetailUser.java
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
<<<<<<<< HEAD:src/java/ControllerAdmin/SubjectDetailServlet.java
            User user = (User)request.getSession().getAttribute("User");
            String id = request.getParameter("id");
            LessonDAO lessonDAO = new LessonDAO();
            Lesson lesson = lessonDAO.getLessonByID(Integer.parseInt(id));
            Boolean check = false;
            if(user != null){
                check = lessonDAO.checkRegistered(user.getUserID());
            }
            request.setAttribute("lesson", lesson);
            request.setAttribute("check", check);
            request.getRequestDispatcher("LessonDetail.jsp").forward(request, response);
========
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DetailUser</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailUser at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
>>>>>>>> 01c855e1d6039d1665043ed687ad7fa980f157a6:src/java/ControllerAdmin/DetailUser.java
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));
        UserDAO sd = new UserDAO();
        User u = sd.findUserByID(id);
        request.setAttribute("u", u);
        request.getRequestDispatcher("detailUser.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
