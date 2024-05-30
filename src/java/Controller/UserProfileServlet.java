
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
        int userId = currentUser.getUserID();
        User user = UserDAO.INS.getUserByID(userId);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/Web/userProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/Web/Login.jsp"); // Redirect to login if user not found
        }
    } else {
        response.sendRedirect(request.getContextPath() + "/Web/Login.jsp"); // Redirect to login if not logged in
    }
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
