package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Vector;
import Models.Users;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class UsersDAO extends DBContext {

    private PreparedStatement ps;
    private ResultSet rs;
    private String xSql;

    public static void main(String[] args) throws ParseException {
        UsersDAO ud = new UsersDAO();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Date a = sdf.parse("1999-12-02");
        //Users x = new Users(4, "ohno","123","ohno123@gmail.com",a, "No No No", 3,"890",2,1);
        ud.Register(1, "admin","123","admin@gmail.com","Admin", "2000-01-01", 1,"0",1,1);
        //ud.editStudent(3, "BaBy Boo", "meomeo@gmail.com", "2009-02-03", 2, 1);

//        Vector<Users> uv = ud.searchUsers("linh");
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

    public Vector<Users> getAllUser() {
        Vector<Users> list = new Vector<>();
        xSql = "SELECT * FROM [User]";
        try {
            PreparedStatement ps = connection.prepareStatement(xSql);
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

                Users x = new Users(xId, xUsername, xPass, xEmail, xDate, xFullname, xSecurityid, xAnswer, xRole, xStatus);
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
            ps = connection.prepareStatement(xSql);
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
            PreparedStatement st = connection.prepareStatement(xSql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(2));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Users findUserByID(int id) {
        String sql = "select * from [dbo].[User] where userid = ?";
        Users s = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s = new Users();
                s.setID(rs.getInt("userid"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setEmail(rs.getString("mail"));
                s.setFullname(rs.getString("fullname"));
                s.setDateOfBirth(rs.getDate("dob"));
                s.setSecurityid(rs.getInt("securityquestionid"));
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
            PreparedStatement ps = connection.prepareStatement(sql);
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

    public Vector<Users> searchUsers(String search) {
        String sql = "SELECT * FROM [dbo].[User] WHERE fullname LIKE ? OR mail LIKE ?";
        Vector<Users> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + search + "%");
            st.setString(2, "%" + search + "%");

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Users s = new Users();
                s.setID(rs.getInt("userid"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setEmail(rs.getString("mail"));
                s.setFullname(rs.getString("fullname"));
                s.setDateOfBirth(rs.getDate("dob"));
                s.setSecurityid(rs.getInt("securityquestionid"));
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
