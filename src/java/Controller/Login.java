/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;



import DAO.UserDAO;
import Models.User;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author hi2ot
 */
public class Login extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        if (ses.getAttribute("User") == null) {
            request.getRequestDispatcher("/Web/Login.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/Home");
        }
        
    }    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        HttpSession ses = request.getSession();
        if (ses.getAttribute("User") == null) {            
            String LoginSubmit = request.getParameter("LoginSubmit");
            if (LoginSubmit != null) {
                String Username = request.getParameter("Username");
                String Password = request.getParameter("Password");
                if (LoginCheck(Username, Password) == null) {                    
                    ses.setAttribute("User", UserDAO.INS.getUserByName(Username));
                    response.sendRedirect(request.getContextPath()+ "/Home");
                } else {
                    request.setAttribute("err", LoginCheck(Username, Password));
                    doGet(request, response);
                }
            } else {
                doGet(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath()+ "/Home");
        }
    }
    
    public String LoginCheck(String Username, String Password) {
        UserDAO.INS.load();
        for (User x : UserDAO.INS.getUl()) {
            if (Username.equals(x.getUserName()) & Password.equals(x.getPassword())) {
                return null;
            }
        }
        return "Wrong Info";
    }
    
}
