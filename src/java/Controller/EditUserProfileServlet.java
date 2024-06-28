package Controller;

import DAO.SEQuestionDAO;
import DAO.UserDAO;
import Models.SEQuestion;
import Models.User;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editUserProfile")
public class EditUserProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        if (user == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        List<SEQuestion> securityQuestions = SEQuestionDAO.INS.getSEQuestion();
        request.setAttribute("securityQuestions", securityQuestions);
        request.setAttribute("user", user);

        request.getRequestDispatcher("editUserProfile.jsp").forward(request, response);
    }

    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("User");

    String mail = request.getParameter("mail");
    String fullname = request.getParameter("fullname");
    String dobStr = request.getParameter("dob");
    String securityQuestionIDStr = request.getParameter("securityQuestionID");
    String answer = request.getParameter("answer");

    try {    
        if (fullname.length() > 30) {
            throw new IllegalArgumentException("Fullname is too long");
        }      
        if (mail == null || !mail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Invalid email format");
        }      
        if (dobStr == null || dobStr.isEmpty() || securityQuestionIDStr == null || securityQuestionIDStr.isEmpty()) {
            throw new IllegalArgumentException("Missing or invalid parameters");
        }

        Date dob = Date.valueOf(dobStr);
        int securityQuestionID = Integer.parseInt(securityQuestionIDStr);
       
        user.setMail(mail);
        user.setFullName(fullname);
        user.setDoB(dob);
        user.setSecurityQuestionID(securityQuestionID);
        user.setAnswer(answer);
    
        boolean isUpdated = UserDAO.INS.updateUser(user);

        if (isUpdated) {
            session.setAttribute("User", user);
            request.setAttribute("successMessage", "Profile updated successfully");            
            request.getRequestDispatcher("editUserProfile.jsp").forward(request, response);
            return; 
        } else {
            throw new Exception("Failed to update user profile");
        }
    } catch (IllegalArgumentException e) {
        request.setAttribute("error", e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "Internal server error");
    }
    
    List<SEQuestion> securityQuestions = SEQuestionDAO.INS.getSEQuestion();
    request.setAttribute("securityQuestions", securityQuestions);
    request.setAttribute("user", user);
    request.getRequestDispatcher("editUserProfile.jsp").forward(request, response);
}

    @Override
    public String getServletInfo() {
        return "EditUserProfileServlet handles the edit user profile actions";
    }
}
