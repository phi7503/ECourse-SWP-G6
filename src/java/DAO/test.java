///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package DAO;
//
//import Models.Users;
//import java.io.PrintWriter;
//import java.util.Date;
//import java.util.Vector;
//
///**
// *
// * @author Admin
// */
//public class test {
//    
//    // Method to get all users
//    
//public Vector<Users> getAllUser() {
//        Vector<Users> list = new Vector<>();
//        xSql = "SELECT * FROM users";
//        try {
//            ps = con.prepareStatement(xSql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int xId = rs.getInt("ID");
//                String xUsername = rs.getString("username");
//                String xPass = rs.getString("password");
//                Date dateOfBirth = rs.getDate("dateOfBirth");
//                int xRole = rs.getInt("role_id");
//
//                Users x = new Users(xId, xUsername, "", xPass, dateOfBirth, xRole);
//                list.add(x);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
//    // Method to register a new user
//    public void Register(String username, String pass) {
//        xSql = "INSERT INTO Users (username, password, role_id) VALUES (?, ?, ?)";
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setString(1, username);
//            ps.setString(2, pass);
//            ps.setInt(3, 2);
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    
//    response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//
//        // Gọi phương thức getAllQuestion() từ UsersDAO để lấy tất cả các câu hỏi bảo mật
//        UsersDAO usersDAO = new UsersDAO();
//        Vector<String> questions = usersDAO.getAllQuestion();
//
//        // Tạo mã HTML để hiển thị các câu hỏi bảo mật trong dropdown list
//        out.println("<select class='form-control' id='securityQuestion' required>");
//        for (String question : questions) {
//            out.println("<option value='" + question + "'>" + question + "</option>");
//        }
//        out.println("</select>");
//
//    // Login method
//    public Users Login(String username, String pass) {
//        xSql = "SELECT * FROM Users WHERE username = ? AND password = ?";
//        Users user = null;
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setString(1, username);
//            ps.setString(2, pass);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                int id = rs.getInt("ID");
//                String fullname = rs.getString("fullname");
//                String email = rs.getString("email");
//                String password = rs.getString("password");
//                Date dateOfBirth = rs.getDate("dateOfBirth");
//                int role = rs.getInt("role_id");
//                user = new Users(id, fullname, email, password, dateOfBirth, role);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return user;
//    }
//    
//    
//    xSql = "INSERT INTO User ([UserID], [UserName], [Password], [Mail], [FullName], [DoB], [SecurityQuestionID], [Answer], [Role], [Status]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try {
//            ps = connection.prepareStatement(xSql);
//            ps.setInt(1, u.getID());
//            ps.setString(2, u.getUsername());
//            ps.setString(3, u.getPassword());
//            ps.setString(4, u.getEmail());
//            ps.setString(5, u.getFullname());
//            ps.setDate(6, java.sql.Date.valueOf(u.getDateOfBirth()));
//            ps.setInt(7, u.getSecurityid());
//            ps.setString(8, u.getAnswer());
//            ps.setInt(9, u.getRole());
//            ps.setString(10, u.getStatus());
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ps != null) ps.close();
//                if (connection != null) connection.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
