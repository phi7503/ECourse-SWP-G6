package Controller;

import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Models.User;

public class UserProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("User");
        
        if (currentUser != null) {
            User user = UserDAO.INS.getUserByID(currentUser.getUserID());
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("userProfile.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + "/Web/Login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet that displays user profile";
    }
}
