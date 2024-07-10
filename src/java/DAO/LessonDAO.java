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
import java.sql.SQLException;
/**
 *
 * @author hi2ot
 */
public class LessonDAO {
    List<Lesson> ll;
    private Connection con;
    private String status = "OK";
    public static LessonDAO INS = new LessonDAO();

    public List<Lesson> getLl() {
        return ll;
    }

    public void setLl(List<Lesson> ll) {
        this.ll = ll;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
            
    private LessonDAO() {
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
    
    public void loadLesson() {
        String sql = "Select * From [Lesson]";
        ll = new Vector<Lesson>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseID = rs.getInt("CourseID");
                int LessonID = rs.getInt("LessonID");
                String LessonName = rs.getString("LessonName");                              
                String Description = rs.getString("Description");                
                ll.add(new Lesson(CourseID, LessonID, LessonName, Description));
            }
        } catch (Exception e) {
            status = "Error at load Lesson " + e.getMessage();
        }
    }
    
    public Vector<LessonDoc> loadLessonDoc(int CourseID, int LessonID) {
        String sql = "Select * From [LessonDoc] Where CourseID = ? And LessonID = ?";
        Vector<LessonDoc> list = new Vector<LessonDoc>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, LessonID);           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int DocID = rs.getInt("DocID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Link = rs.getString("Link");
                list.add(new LessonDoc(CourseID, LessonID, DocID, Title, Description, Link));
            }
        } catch (Exception e) {
            status = "Error at load LessonDoc " + e.getMessage();            
        }
        return list;
    }
    
    public Vector<Lesson> loadLessonByCourseID(int CourseID) {
        String sql = "Select * From [Lesson] Where CourseID = ?";
        Vector<Lesson> list = new Vector<Lesson>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int LessonID = rs.getInt("LessonID");
                String LessonName = rs.getString("LessonName");
                String Description = rs.getString("Description");
                list.add(new Lesson(CourseID, LessonID, LessonName, Description));
            }
        } catch (Exception e) {
            status = "Error at loadLessonByCourseID " + e.getMessage();
        }
        return list;
    }
    
    public Lesson getLessonByID(int CourseID, int LessonID) {
        String sql = "Select * From [Lesson] Where CourseID = ? And LessonID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, LessonID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String LessonName = rs.getString("LessonName");
                String Description = rs.getString("Description");
                return new Lesson(CourseID, LessonID, LessonName, Description);
            }
        } catch (SQLException e) {
            status = "Error at getLessonByID " + e.getMessage();
        }
        return null;
    }
    
    public void createNewDoc(int CourseID, int LessonID, String Title, String Description, String fileName) {
        String sql = "Insert Into [LessonDoc] Values(?,?,?,?,?,?)";
        int DocID = INS.loadLessonDoc(CourseID, LessonID).size() + 1;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, LessonID);
            ps.setInt(3, DocID);
            ps.setString(4, Title);
            ps.setString(5, Description);
            ps.setString(6, fileName);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at createNewDoc " + e.getMessage();
        }
    }
    
    public void updateDoc(int CourseID, int LessonID, int DocID, String Title, String Description, String fileName) {
        String sql = "Update [LessonDoc]"
                + "\n Set Title = ?, Description = ?, Link = ?"
                + "\n Where CourseID = ? And LessonID = ? And DocID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Title);
            ps.setString(2, Description);
            ps.setString(3, fileName);
            ps.setInt(4, CourseID);
            ps.setInt(5, LessonID);
            ps.setInt(6, DocID);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at updateDoc " + e.getMessage();
        }
    }
    
    public LessonDoc getDocByID(int CourseID, int LessonID, int DocID) {
        String sql = "Select * From [LessonDoc] Where CourseID = ? And LessonID = ? And DocID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, LessonID);
            ps.setInt(3, DocID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Link = rs.getString("Link");
                return new LessonDoc(CourseID, LessonID, DocID, Title, Description, Link);
            }
        } catch (SQLException e) {
            status = "Error at getDocByID " + e.getMessage();
        }
        return null;
    }
    
    public static void main(String[] args){        
        INS.updateDoc(1, 1, 1, "Math", "Math", "b.img");
        System.out.println(INS.status);
    }
}
