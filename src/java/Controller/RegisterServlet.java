package Controller;

import DAO.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import Models.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.xml.bind.DatatypeConverter.parseDate;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})

public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsersDAO dao = new UsersDAO();
        Vector <String> listQuestion = dao.getAllQuestion();
        
        request.setAttribute("list", listQuestion);
        request.getRequestDispatcher("register.jsp").forward(request, response);
        
    }

    public static boolean checkUserExist(String username, Vector<Users> listU) {
        for (Users u : listU) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int userid ;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cfpass = request.getParameter("cfpassword");
        String mail = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String dob1 = "2002-11-02";//request.getParameter("dateOfBirth");
        int securityid = 1;
        String answer = request.getParameter("answer");
        String role1 = request.getParameter("role");
        int status = 1;

        
        int role=1;
        if(role1.equals("expert")){
            role=2;
        }else if(role1.equals("learner")){
            role=3;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = sdf.parse(dob1);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // lay ra tat ca nhung tai khoan da ton tai trong he thong
        UsersDAO ud = new UsersDAO();
        Vector<Users> listU = ud.getAllUser();
        userid = listU.size()+1;

        if (checkUserExist(username, listU)) {
            request.setAttribute("mess", "Username already exists");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!password.equals(cfpass)) {
            // kiem tra mat khau va nhap lai mat khau co khop nhau hay khong
            request.setAttribute("mess", "Password not match!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            // neu khong co van de gì -> dang ky tai khoan thành công
            Users u = new Users(userid, username, password, mail, dob, fullname, securityid, answer, role, status);
            ud.Register(u);
            request.getRequestDispatcher("index.html").forward(request, response);

        }

    }

}
