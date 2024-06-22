
package DAO;

import Models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


public class QuizDAO {
    List<Quiz> ql;
    private Connection con;
    private String status = "OK";
    public static QuizDAO INS = new QuizDAO();

    public List<Quiz> getQl() {
        return ql;
    }

    public void setQl(List<Quiz> ql) {
        this.ql = ql;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }        
    
    private QuizDAO() {
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
        String sql = "Select * From [Quiz]";
        ql = new Vector<Quiz>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseID = rs.getInt("CourseID");
                int LessonID = rs.getInt("LessonID");
                int QuizID = rs.getInt("QuizID");                
                String QuizName = rs.getString("QuizName");
                int NoQ = rs.getInt("NoQ");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                ql.add(new Quiz(CourseID, LessonID, QuizID, QuizName, NoQ, CreateDate));
            }
        } catch (Exception e) {
            status = "Error at load Quiz " + e.getMessage();
        }
    }        
    
    public Vector<Quiz> loadQuizByLesson(int CourseID, int LessonID) {
        String sql = "Select * From [Quiz] Where CourseID = ? And LessonID = ?";
        Vector<Quiz> list = new Vector<Quiz>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, LessonID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int QuizID = rs.getInt("QuizID");
                String QuizName = rs.getString("QuizName");
                int NoQ = rs.getInt("NoQ");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                list.add(new Quiz(CourseID, LessonID, QuizID, QuizName, NoQ, CreateDate));
            }
        } catch (Exception e) {
            status = "Error at loadQuizByLesson " + e.getMessage();
        }
        return list;
    }
    
    public static void main(String[] agrs){
        INS.load();
        INS.loadQuizByLesson(1, 1);
        System.out.println(INS.getStatus());
    }
}
