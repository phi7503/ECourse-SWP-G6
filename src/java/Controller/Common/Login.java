/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Common;

import DAO.UserDAO;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hi2ot
 */
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("User");
        if (u != null) {
            response.sendRedirect(request.getContextPath() + "/Home");
        } else {
            request.getRequestDispatcher("Web/Login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        if (ses.getAttribute("User") != null) {
            response.sendRedirect(request.getContextPath() + "/Home");
        } else {
            String LoginSubmit = request.getParameter("LoginSubmit");
            if (LoginSubmit != null) {
                String Username = request.getParameter("Username");
                String Password = request.getParameter("Password");
                try {
                    if (UserDAO.INS.LoginCheck(Username, Password) == null) {
                        UserDAO.INS.load();
                        User u = UserDAO.INS.getUserByName(Username);
                        if (u.getStatus() == 0){
                            request.setAttribute("err", "Your Account Have Been Inactivated, Please Contact Admin For More Information!");
                            doGet(request, response);
                            return;
                        }
                        ses.setAttribute("User", u);
                        response.sendRedirect(request.getContextPath() + "/CourseShop");
                    } else {
                        request.setAttribute("err", UserDAO.INS.LoginCheck(Username, Password));
                        doGet(request, response);
                    }
                } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                doGet(request, response);
            }
        }
    }

}
