
package DAO;


import java.sql.Connection;
import java.util.*;
import Models.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;




/**
 *
 * @author hi2ot
 */
public class UserDAO {

    private List<User> ul;
    private Connection con;
    private String status = "OK";
    public static UserDAO INS = new UserDAO();
    private String xSql;
   

    public List<User> getUl() {
        return ul;
    }

    public void setUl(List<User> ul) {
        this.ul = ul;
    }

    public UserDAO() {
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

    
    public Vector<Course> loadUserOwnCourse(int UserID) {
        String sql = "Select * From [OwnCourse] oc Join [Course] c On oc.CourseID = c.CourseID Where UserID = ?";
        Vector<Course> course = new Vector<Course>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
                int CourseID = rs.getInt("CourseID");
                String CourseName = rs.getString("CourseName");
                float Price = rs.getFloat("Price");
                String Description = rs.getString("Description");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                course.add(new Course(CourseID, CourseName, Price, Description, CreateDate));
            }
        } catch (Exception e) {
            status = "Error at loadUserOwnCourse " + e.getMessage();
        }
        return course;
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
    
    public User checkResetPass(String username, String maill, int Question, String answer) {
        String sql = "SELECT * FROM [User] WHERE UserName = ? AND Mail = ?"
                + " AND SecurityQuestionID = ? AND Answer = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, maill);
            ps.setInt(3, Question);
            ps.setString(4, answer);
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
    
    public boolean updateUser(User user) {
        String sql = "UPDATE [User] SET Mail = ?, FullName = ?, DoB = ?, Role = ? WHERE UserID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getMail());
            ps.setString(2, user.getFullName());
            ps.setDate(3, user.getDoB());
            ps.setInt(4, user.getRole());
            ps.setInt(5, user.getUserID());
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            String status = "Error at updateUser: " + e.getMessage();
            System.out.println(status);
            return false;
        }
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
                java.sql.Date xDate = rs.getDate(6);
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
        xSql = "INSERT INTO [User] ([UserID], [UserName], [Password], [Mail], [FullName], [DoB], [SecurityQuestionID], [Answer], [Role], [Status]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(xSql); 
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
        xSql = "  Select * from [SEQuestion]";

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
    
    public String findQuestionByID(int id) {
        String sql = " select * from [SEQuestion] where SecurityQuestionID = ?";
        String s = "";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s = rs.getString("question");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return s;
    }

    public int findQuestionByName(String name) {
        String sql = "select * from [SEQuestion] where Question = ?";
        int id = 1;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("SecurityQuestionID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
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

    


