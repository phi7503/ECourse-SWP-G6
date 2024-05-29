/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllerAdmin;

import DAO.UsersDAO;
import Models.Users;
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
@WebServlet(name = "editUser", urlPatterns = {"/edituser"})
public class editUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));
        UsersDAO sd = new UsersDAO();
        Users s = sd.findUserByID(id);
        request.setAttribute("s", s);
        request.getRequestDispatcher("editUser.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id1 = request.getParameter("ID");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String dob = request.getParameter("dateOfBirth");
            String role1 = request.getParameter("role");
            String status1 = request.getParameter("status");

            int id = Integer.parseInt(id1);
            int role = Integer.parseInt(role1);
            int status = Integer.parseInt(status1);
            UsersDAO sd = new UsersDAO();
            sd.editUser(id, fullname, email, dob, role, status);
            response.sendRedirect("userslist"); // Ensure this is the correct URL
        } catch (NumberFormatException e) {
            // Handle NumberFormatException (e.g., invalid role or status)
            e.printStackTrace(); // Log the exception for debugging
            response.sendRedirect("error.jsp"); // Redirect to an error page
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace(); // Log the exception for debugging
            response.sendRedirect("error.jsp"); // Redirect to an error page
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
