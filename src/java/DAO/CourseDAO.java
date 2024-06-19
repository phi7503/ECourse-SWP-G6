/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.*;
import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author hi2ot
 */
public class CourseDAO {
    
    private List<Course> cl;
    private Connection con;
    private String status = "OK";
    public static CourseDAO INS = new CourseDAO();

    public List<Course> getCl() {
        return cl;
    }

    public void setCl(List<Course> cl) {
        this.cl = cl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }       
    
    private CourseDAO() {
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
    
    public void loadCourse() {
        String sql = "Select * From [Course]";
        cl = new Vector<Course>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseID = rs.getInt("CourseID");
                String CourseName = rs.getString("CourseName");
                float Price = rs.getFloat("Price");
                String Description = rs.getString("Description");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                int UserID = rs.getInt("UserID");
                cl.add(new Course(CourseID, CourseName, Price, Description, CreateDate, UserID));
            }
        } catch (Exception e) {
            status = "Error at load Course " + e.getMessage();
        }
    }
    
    public void addSubject(int SubjectID, String SubjectName) {
        String sql = "Insert Into [Subject] Values (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, SubjectID);
            ps.setString(2, SubjectName);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at addSubject " + e.getMessage();
        }
    }
    
    public void addCategory(int CategoryID, String CategoryName) {
        String sql = "Insert Into [Category] Values (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CategoryID);
            ps.setString(2, CategoryName);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at addCategory " + e.getMessage();
        }
    }
    
    public Vector<Subject> loadSubjectList() {
        String sql = "Select * From [Subject]";
        Vector<Subject> SubjectList = new Vector<Subject>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int SubjectID = rs.getInt("SubjectID");
                String SubjectName = rs.getString("SubjectName");
                SubjectList.add(new Subject(SubjectID, SubjectName));
            }
        } catch (SQLException e) {
            status = "Error at loadSubjectList " + e.getMessage();
        }
        return SubjectList;
    }
    
    public Vector<Category> loadCategoryList() {
        String sql = "Select * From [Category]";
        Vector<Category> CategoryList = new Vector<Category>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                CategoryList.add(new Category(CategoryID, CategoryName));
            }
        } catch (SQLException e) {
            status = "Error at loadCategoryList " + e.getMessage();
        }
        return CategoryList;
    }
    
    public void deleteCategory(int CategoryID) {
        String sql = "Delete From [CourseCategory] Where CategoryID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CategoryID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at deleteCourseCategory " + e.getMessage();
        }
        sql = "Delete From [Category] Where CategoryID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CategoryID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at deleteCourseCategory " + e.getMessage();
        }
    }
    
    public void updateCategory(int CategoryID, String CategoryName) {                
        String sql = "Update [Category]"
                + "\n Set CategoryName = ?"
                + "\n Where CategoryID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, CategoryName);
            ps.setInt(2, CategoryID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at updateCategory " + e.getMessage();
        }        
        
    }
    
    public void deleteSubject(int SubjectID) {
        String sql = "Delete From [CourseSubject] Where SubjectID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, SubjectID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at deleteCourseSubject " + e.getMessage();
        }
        sql = "Delete From [Subject] Where SubjectID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, SubjectID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at deleteCourseSubject " + e.getMessage();
        }
    }
    
    public void updateSubject(int SubjectID, String SubjectName) {                
        String sql = "Update [Subject]"
                + "\n Set SubjectName = ?"
                + "\n Where SubjectID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, SubjectName);
            ps.setInt(2, SubjectID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at updateSubject " + e.getMessage();
        }        
        
    }
    
    public static void main (String[] agrs) {               
        System.out.println(INS.status);
    }
    
}
