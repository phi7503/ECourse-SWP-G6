/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author hi2ot
 */
public class QuestionDAO {
    List<Question> qul;
    private Connection con;
    private String status = "OK";
    public static QuestionDAO INS = new QuestionDAO();

    public List<Question> getQul() {
        return qul;
    }

    public void setQul(List<Question> qul) {
        this.qul = qul;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
            
    private QuestionDAO() {
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
        String sql = "Select * From [Question]";
        qul = new Vector<Question>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               int QuizID = rs.getInt("QuizID");
               int QuestionID = rs.getInt("QuestionID");
               String Question = rs.getString("Question");
               String Explaination = rs.getString("Explaination");
               qul.add(new Question(QuizID, QuestionID, Question, Explaination));
            }
        } catch (Exception e) {
            status = "Error at load Question " + e.getMessage();
        }
    }
    
    public Vector<Question> loadQuestionByQuizID(int ID) {
        String sql = "Select * From [Question] Where QuizID = ?";
        Vector<Question> list = new Vector<Question>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               int QuizID = rs.getInt("QuizID");
               int QuestionID = rs.getInt("QuestionID");
               String Question = rs.getString("Question");
               String Explaination = rs.getString("Explaination");
               list.add(new Question(QuizID, QuestionID, Question, Explaination));
            }
        } catch (Exception e) {
            status = "Error at load loadQuestionByQuizID " + e.getMessage();
        }
        return list;
    }
    
    public void addUserQuestionStatus(int QuestionID, int UserID){
        String sql = "Insert Into [QuestionStatus] Values(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, QuestionID);
            ps.setInt(2, UserID);
            ps.setInt(3, 1);
            ps.execute();
        } catch (Exception e) {
            
        }
    }
    
    public static void main(String[] agrs) {
        INS.load();
        System.out.println(INS.loadQuestionByQuizID(1).size());
        System.out.println(INS.getStatus());
    }
}
