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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})

public class RegisterServlet extends HttpServlet {

    UserDAO dao = new UserDAO();

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
        String seQuestion = request.getParameter("securityQuestion");
        String answer = request.getParameter("answer");
        String role1 = request.getParameter("role");
        int status = 1;

        int seQuestionID = dao.findQuestionByName(seQuestion);
        int role = 2;
        if (role1.equals("expert")) {
            role = 2;
        } else if (role1.equals("learner")) {
            role = 3;
        }

        Vector<User> listU = dao.getAllUser();
        userid = listU.size() + 1;

        if (!isValidFullname(fullname)) {
            request.setAttribute("mess", "Please enter the correct full name!!");
            doGet(request, response);
        } else if (!isValidUsername(username)) {
            request.setAttribute("mess", "Username must have a length greater than 8!!!");
            doGet(request, response);
            
        } else if (checkUserExist(username, listU)) {
            request.setAttribute("mess", "Username already exists!!");
            doGet(request, response);
        } else if (!isValidPassword(password)) {
            request.setAttribute("mess", "Password must contain all uppercase letters, lowercase letters, numbers and special characters!!!");
            doGet(request, response);
            
        } else if (!isValidAge(dob)) {
            request.setAttribute("mess", "Age must be at least 10 years old!!");
            doGet(request, response);
            
        } else if (!password.equals(cfpass)) {
            request.setAttribute("mess", "Password not match!");
            doGet(request, response);
        }else{
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

    private boolean isValidUsername(String username) {
        if (username.length() <= 8) {
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        return password.matches(regex);
    }

    private boolean isValidFullname(String fullname) {
        String regex = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$";
        return fullname.matches(regex);
    }

    private boolean isValidAge(String dob) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(dob);
        long age = ChronoUnit.YEARS.between(birthDate, currentDate);
        return age >= 10;
    }


}
