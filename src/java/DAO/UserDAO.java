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
}