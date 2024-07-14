/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.*;
import DAO.*;
import java.util.*;

/**
 *
 * @author hi2ot
 */
public class UserManage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String statusa = request.getParameter("statusa");
        String statusi = request.getParameter("statusi");

        request.setAttribute("statusa", statusa);
        request.setAttribute("statusi", statusi);

        List<User> UserList = new ArrayList<>();

        if (statusa == null && statusi == null) {
            UserList = UserDAO.INS.getUserList();
        } else {
            for (User u : UserDAO.INS.getUserList()) {
                if (statusa != null && u.getStatus() == 1) {
                    UserList.add(u);
                } else if (statusi != null && u.getStatus() == 0) {
                    UserList.add(u);
                }
            }
        }

        String sort = request.getParameter("sort");
        if (sort == null) {
            sort = "ID";
        }

        if (sort.equals("UserName")) {
            for (int i = 0; i < UserList.size(); i++) {
                for (int j = i + 1; j < UserList.size(); j++) {
                    User a = UserList.get(i);
                    User b = UserList.get(j);
                    if (a.getUserName().compareTo(b.getUserName()) > 0) {
                        UserList.remove(i);
                        UserList.add(i, b);
                        UserList.remove(j);
                        UserList.add(j, a);
                    }
                }
            }
        } else if (sort.equals("DoB")) {
            for (int i = 0; i < UserList.size(); i++) {
                for (int j = i + 1; j < UserList.size(); j++) {
                    User a = UserList.get(i);
                    User b = UserList.get(j);
                    if (a.getDoB().compareTo(b.getDoB()) > 0) {
                        UserList.remove(i);
                        UserList.add(i, b);
                        UserList.remove(j);
                        UserList.add(j, a);
                    }
                }
            }
        }

        request.setAttribute("sort", sort);

        int index = -1;
        int total = -1;
        try {
            index = Integer.parseInt(request.getParameter("index"));
            total = Integer.parseInt(request.getParameter("total"));
        } catch (NumberFormatException e) {
            index = 0;
            total = 0;
        }
        String btn = request.getParameter("btn");
        if (btn != null) {
            try {
                int btnNum = Integer.parseInt(btn);
                index = btnNum;
            } catch (NumberFormatException e) {
                index = 0;
            }
        }

        String btnHome = request.getParameter("btnHome");
        String btnPre = request.getParameter("btnPre");
        String btnNext = request.getParameter("btnNext");
        String btnEnd = request.getParameter("btnEnd");

        if (btnHome != null) {
            index = 0;
        }

        if (btnPre != null) {
            index--;
        }

        if (btnNext != null) {
            index++;
        }

        if (btnEnd != null) {
            index = total - 1;
        }

        Paging paging = new Paging(UserList.size(), 6, index);
        paging.calc();

        request.setAttribute("paging", paging);
        request.setAttribute("UserList", UserList);
        request.getRequestDispatcher("/Web/UserManage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
