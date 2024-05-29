
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.util.*;
import Models.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hi2ot
 */
public class UserDAO {
    
    private List<User> ul;
    private Connection con;
    private String status = "OK";
    public static UserDAO INS = new UserDAO();

    public List<User> getUl() {
        return ul;
    }

    public void setUl(List<User> ul) {
        this.ul = ul;
    }

    private UserDAO() {
        if (INS == null) {
            try {
                con = new DBContext().getConnection();
            } catch (Exception e) {
                status = "Error at Connection" + e.getMessage();
            }
        } else {
            INS = this;
        }
    }
    
    public void load() {
        String sql = "Select * From [User]";
        ul = new Vector<User>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int UserID = rs.getInt("UserID");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String Mail = rs.getString("Mail");
                String FullName = rs.getString("FullName");
                java.sql.Date DoB = rs.getDate("DoB");
                int SecurityQuestionID = rs.getInt("SecurityQuestionID");
                String Answer = rs.getString("Answer");
                int Role = rs.getInt("Role");
                ul.add(new User(UserID, UserName, Password, Mail, FullName, DoB, SecurityQuestionID, Answer, Role));
            }
        } catch (Exception e) {
            status = "Error at load User" + e.getMessage();
            System.out.println(status);
        }        
    }
    
    public User getUserByName(String UserName) {
        for (User x : ul) {
            if (x.getUserName().equals(UserName)) {
                return x;
            }
        }
        return null;
    }


     public User check(String username, String password) {
        String sql = "SELECT * FROM [User] WHERE UserName = ? AND Password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int UserID = rs.getInt("UserID");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String Mail = rs.getString("Mail");
                String FullName = rs.getString("FullName");
                java.sql.Date DoB = rs.getDate("DoB");
                int SecurityQuestionID = rs.getInt("SecurityQuestionID");
                String Answer = rs.getString("Answer");
                int Role = rs.getInt("Role");
                return new User(UserID, UserName, Password, Mail, FullName, DoB, SecurityQuestionID, Answer, Role);
            }
        } catch (Exception e) {
            status = "Error at check User" + e.getMessage();
            System.out.println(status);
        }
        return null;
    }

    
    public void change(User user) {
        String sql = "UPDATE [User] SET Password = ? WHERE UserID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setInt(2, user.getUserID());
            ps.executeUpdate();
        } catch (Exception e) {
            status = "Error at change Password" + e.getMessage();
            System.out.println(status);
        }
    }
    
    public User getUserByID(int userID) {
        String sql = "SELECT * FROM [User] WHERE UserID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String Mail = rs.getString("Mail");
                String FullName = rs.getString("FullName");
                java.sql.Date DoB = rs.getDate("DoB");
                int SecurityQuestionID = rs.getInt("SecurityQuestionID");
                String Answer = rs.getString("Answer");
                int Role = rs.getInt("Role");
                return new User(userID, UserName, Password, Mail, FullName, DoB, SecurityQuestionID, Answer, Role);
            }
        } catch (Exception e) {
            status = "Error at getUserByID: " + e.getMessage();
            System.out.println(status);
        }
        return null;
    }
    
     public boolean changePassword(int userID, String newPassword) {
        String sql = "UPDATE [User] SET Password = ? WHERE UserID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setInt(2, userID);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            status = "Error at changePassword: " + e.getMessage();
            System.out.println(status);
            return false;
        }
    }
}

