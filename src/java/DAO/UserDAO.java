/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.User;
import DAO.DBContext;
import DAO.DBContext;
import java.sql.Connection;
import java.util.*;
import Models.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author hi2ot
 */
public class UserDAO  extends DBContext{
    public static void main(String[] args) throws ParseException {
        UserDAO ud = new UserDAO();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Date a = sdf.parse("1999-12-02");
        //Users x = new Users(4, "ohno","123","ohno123@gmail.com",a, "No No No", 3,"890",2,1);
        //ud.Register(5, "admin","123","admin@gmail.com","Admin", "2000-01-01", 1,"0",1,1);
        //ud.editStudent(3, "BaBy Boo", "meomeo@gmail.com", "2009-02-03", 2, 1);
//
        Vector<User> uv = ud.getAllUser();
//        System.out.println(uv);
//        System.out.println(uv.getID());
//        System.out.println(uv.getDateOfBirth());
//        System.out.println(uv.getEmail());
//        System.out.println(uv.getFullname());
//        System.out.println(uv.getRole());
//        System.out.println(uv.getAnswer());
//        System.out.println(uv.getSecurityid());
//        System.out.println(uv.getUsername());

    }
    
    
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
    
    public Vector<User> getAllUser() {
        Vector<User> list = new Vector<>();
        String xSql = "SELECT * FROM [User]";
        try {
            PreparedStatement ps = con.prepareStatement(xSql);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt(1);
                String xUsername = rs.getString(2);
                String xPass = rs.getString(3);
                String xEmail = rs.getString(4);
                String xFullname = rs.getString(5);
                Date xDate = rs.getDate(6);
                int xSecurityid = rs.getInt(7);
                String xAnswer = rs.getString(8);
                int xRole = rs.getInt(9);
                int xStatus = rs.getInt(10);

                User x = new User(xId, xUsername, xPass, xEmail, xFullname, xDate, xSecurityid, xAnswer, xRole);
                list.add(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return list;
    }
    
    public void Register(int id, String username, String pass, String email, String fullname, String date, int securityid, String answer, int role, int status) {
        String xSql = "INSERT INTO [User] ([UserID], [UserName], [Password], [Mail], [FullName], [DoB], [SecurityQuestionID], [Answer], [Role], [Status]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(xSql);
            ResultSet rs;
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, pass);
            ps.setString(4, email);
            ps.setString(5, fullname);
            ps.setString(6, date);
            ps.setInt(7, securityid);
            ps.setString(8, answer);
            ps.setInt(9, role);
            ps.setInt(10, status);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public Vector<String> getAllQuestion() {
        Vector<String> list = new Vector<>();
        String xSql = "  Select * from [SEQuestion]";

        try {
            PreparedStatement st = con.prepareStatement(xSql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(2));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public User findUserByID(int id) {
        String sql = "select * from [dbo].[User] where userid = ?";
        User s = null;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s = new User();
                s.setUserID(rs.getInt("userid"));
                s.setUserName(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setMail(rs.getString("mail"));
                s.setFullName(rs.getString("fullname"));
                s.setDoB(rs.getDate("dob"));
                s.setSecurityQuestionID(rs.getInt("securityquestionid"));
                s.setAnswer(rs.getString("answer"));
                s.setRole(rs.getInt("role"));
                s.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return s;
    }

    public void editUser(int id, String fullname, String email, String dob, int role, int status) {
        String sql = "UPDATE [dbo].[User] SET fullname = ?, mail = ?, dob = ?, role = ?, status = ? WHERE userid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, dob);
            ps.setInt(4, role);
            ps.setInt(5, status);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Vector<User> searchUsers(String search) {
        String sql = "SELECT * FROM [dbo].[User] WHERE fullname LIKE ? OR mail LIKE ?";
        Vector<User> list = new Vector<>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + search + "%");
            st.setString(2, "%" + search + "%");

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User s = new User();
                s.setUserID(rs.getInt("userid"));
                s.setUserName(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setMail(rs.getString("mail"));
                s.setFullName(rs.getString("fullname"));
                s.setDoB(rs.getDate("dob"));
                s.setSecurityQuestionID(rs.getInt("securityquestionid"));
                s.setAnswer(rs.getString("answer"));
                s.setRole(rs.getInt("role"));
                s.setStatus(rs.getInt("status"));

                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
    
    
