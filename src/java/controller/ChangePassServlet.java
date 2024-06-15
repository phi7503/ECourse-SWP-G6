
package controller;

import ControllerAdmin.*;
    import DAO.UserDAO;
    import java.io.IOException;
    import java.io.PrintWriter;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.servlet.http.HttpSession;
    import Models.User;

    public class ChangePassServlet extends HttpServlet {

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ChangePassServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet ChangePassServlet at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("User");
            if (currentUser != null) {
                String oPass = request.getParameter("opass");
                String pass = request.getParameter("pass");
                String re_pass = request.getParameter("re_pass");

                UserDAO userDAO = UserDAO.INS;

                if (currentUser.getPassword().equals(oPass)) {
                    if (pass.length() >= 8) {
                        if (pass.equals(re_pass)) {
                            boolean isChanged = userDAO.changePassword(currentUser.getUserID(), pass);
                            if (isChanged) {
                                currentUser.setPassword(pass);
                                request.setAttribute("mess", "Password changed successfully!");
                                session.setAttribute("account", currentUser);
                            } else {
                                request.setAttribute("mess", "Failed to change password!");
                            }
                        } else {
                            request.setAttribute("mess", "Re-entered password is incorrect");
                        }
                    } else {
                        request.setAttribute("mess", "Password too short!");
                    }
                } else {
                    request.setAttribute("mess", "Incorrect current password");
                }
            } else {
                response.sendRedirect("/Web/Login.jsp");
            }
            doGet(request, response);
        }

        @Override
        public String getServletInfo() {
            return "Short description";
        }
    }
