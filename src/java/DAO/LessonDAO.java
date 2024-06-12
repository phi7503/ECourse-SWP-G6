
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
    
    public static void main(String[] args){        
        INS.loadLesson();
        System.out.println(INS.getLl().size());
    }
}
