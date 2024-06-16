
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.*;
import Models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hi2ot
 */
public class AnswerDAO {

    private List<Answer> al;
    private Connection con;
    private String status = "OK";
    public static AnswerDAO INS = new AnswerDAO();

    public List<Answer> getAl() {
        return al;
    }

    public void setAl(List<Answer> al) {
        this.al = al;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AnswerDAO() {
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
        String sql = "Select * From [Answer]";
        al = new Vector<Answer>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseID = rs.getInt("CourseID");
                int LessonID = rs.getInt("LessonID");
                int QuizID = rs.getInt("QuizID");
                int QuestionID = rs.getInt("QuestionID");
                int AnswerID = rs.getInt("AnswerID");
                String Description = rs.getString("Description");
                int Role = rs.getInt("Role");
                al.add(new Answer(CourseID, LessonID, QuizID, QuestionID, AnswerID, Description, Role));
            }
        } catch (Exception e) {
            status = "Error at load Answer " + e.getMessage();
        }
    }            

    public static void main(String[] agrs) {
        INS.load();        
        System.out.println(INS.getStatus());        
    }
}
