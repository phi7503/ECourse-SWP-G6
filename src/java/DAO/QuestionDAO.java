
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
    
    public static void main(String[] agrs) {
        INS.load();
        System.out.println(INS.getStatus());
    }
}
