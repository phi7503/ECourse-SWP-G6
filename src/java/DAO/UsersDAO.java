package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Vector;
import Models.Users;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class UsersDAO extends MyDAO {
    private PreparedStatement ps;
    private ResultSet rs;
    private String xSql;

    public static void main(String[] args) throws ParseException {
        UsersDAO ud = new UsersDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date a = sdf.parse("1999-12-02");
        Users x = new Users(3, "linhltd123","123","linh123@gmail.com",a, "Luong Dieu Linh", 3,"890",2,1);
        ud.Register(x);


        //System.out.println(ud.getAllUser());
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
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public void Register(Users u) {
         xSql = "INSERT INTO [User] ([UserID], [UserName], [Password], [Mail], [FullName], [DoB], [SecurityQuestionID], [Answer], [Role], [Status]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setInt(1, u.getID());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getFullname());
            Date dateOfBirth = u.getDateOfBirth();
            ps.setDate(6, new java.sql.Date(dateOfBirth.getTime()));
            ps.setInt(7, u.getSecurityid());
            ps.setString(8, u.getAnswer());
            ps.setInt(9, u.getRole());
            ps.setInt(10, u.getStatus());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    
}
