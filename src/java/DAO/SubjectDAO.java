/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class SubjectDAO {

    List<Subject> ql;
    private Connection con;
    private String status = "OK";
    public static SubjectDAO INS = new SubjectDAO();

    public static void main(String[] args) throws ParseException {
        SubjectDAO ud = INS;
        System.out.println(ud.getAllSubject());
    }

    private SubjectDAO() {
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

    public Vector<Subject> getAllSubject() {
        String sql = "Select * From [Subject]";
        Vector<Subject> list = new Vector<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int SubjectID = rs.getInt("SubjectID");
                String SubjectName = rs.getString("SubjectName");

                list.add(new Subject(SubjectID, SubjectName));
            }
        } catch (Exception e) {
            status = "Error at load Quiz " + e.getMessage();
        }
        return list;
    }

    public void NewSubject(int id, String name) {
        String xSql = "INSERT INTO [dbo].[Subject] ([SubjectID],[SubjectName]) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(xSql);
            ResultSet rs;
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSubject(int id, String name) {
        String sql = "UPDATE [dbo].[Subject] SET SubjectName = ? WHERE SubjectID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error updating subject: " + e.getMessage();
        }
    }
    
    public void deleteSubject(int id) {
        String sql = "DELETE FROM [dbo].[Subject] WHERE SubjectID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error deleting subject: " + e.getMessage();
        }
    }
}
