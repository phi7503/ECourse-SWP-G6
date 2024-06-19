package ControllerAdmin;

import Models.User;
import DAO.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import Models.*;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})

public class RegisterServlet extends HttpServlet {

    UserDAO dao = UserDAO.INS;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vector<String> listQuestion = dao.getAllQuestion();

        request.setAttribute("list", listQuestion);
        request.getRequestDispatcher("/register.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userid;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cfpass = request.getParameter("cfpassword");
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String dob = request.getParameter("dateOfBirth");
        int seQuestionID = 1;
        String answer = request.getParameter("answer");
        String role1 = request.getParameter("role");
        int status = 1;

        int role = 2;
        if (role1.equals("expert")) {
            role = 2;
        } else if (role1.equals("learner")) {
            role = 3;
        }

        Vector<User> listU = dao.getAllUser();
        userid = listU.size() + 1;

        if (!isValidFullname(fullname)) {
            request.setAttribute("mess", "Please enter the correct full name");
            doGet(request, response);
        } else if (checkUserExist(username, listU)) {
            request.setAttribute("mess", "Username already exists");
            doGet(request, response);
        } else if (!password.equals(cfpass)) {
            // kiem tra mat khau va nhap lai mat khau co khop nhau hay khong
            request.setAttribute("mess", "Password not match!");
            doGet(request, response);
        }else {
        dao.Register(userid, username, password, email, fullname, dob, seQuestionID, answer, role, status);
        request.getRequestDispatcher("/index.html").forward(request, response);
        }

    }

    public static boolean checkUserExist(String username, Vector<User> listU) {
        for (User u : listU) {
            if (u.getUserName().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

//    private boolean isValidEmail(String email) {
//        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
//        return email.matches(regex);
//    }
    
    private boolean isValidFullname(String fullname) {
        String regex = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$";
        return fullname.matches(regex);
    }

}
