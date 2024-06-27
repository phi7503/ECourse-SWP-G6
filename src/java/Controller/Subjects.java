package Controller;

import DAO.CategoryDAO;
import DAO.SubjectDAO;
import Models.Subject;
import Models.Category;
import java.io.IOException;
import java.util.Vector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Subjects", urlPatterns = {"/Subjects"})
public class Subjects extends HttpServlet {

    private final SubjectDAO subjectsdata = SubjectDAO.INS;
    private final CategoryDAO categorysdata = CategoryDAO.INS;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vector<Subject> subject = subjectsdata.getAllSubject();
        Vector<Category> category = categorysdata.getAllCategory();
        request.setAttribute("subject", subject);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/Web/subjectlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Subjects");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for managing subjects and categories";
    }

}
