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
                int CourseID = rs.getInt("CourseID");
                int LessonID = rs.getInt("LessonID");
                int QuizID = rs.getInt("QuizID");
                int QuestionID = rs.getInt("QuestionID");
                String Question = rs.getString("Question");
                String Explaination = rs.getString("Explaination");
                qul.add(new Question(CourseID, LessonID, QuizID, QuestionID, Question, Explaination));
            }
        } catch (Exception e) {
            status = "Error at load Question " + e.getMessage();
        }
    }

    public Vector<Answer> loadQuestionAnswer(int CourseID, int LessonID, int QuizID, int QuestionID) {
        String sql = "Select * From [Answer] Where CourseID = ? And LessonID = ? And QuizID = ? And QuestionID = ?";
        Vector<Answer> AnswerList = new Vector<Answer>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, LessonID);
            ps.setInt(3, QuizID);
            ps.setInt(4, QuestionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AnswerID = rs.getInt("AnswerID");
                String Description = rs.getString("Description");
                int Role = rs.getInt("Role");
                AnswerList.add(new Answer(CourseID, LessonID, QuizID, QuestionID, AnswerID, Description, Role));
            }
        } catch (Exception e) {
            status = "Error at loadQuestionAnswer " + e.getMessage();
        }
        return AnswerList;
    }

    public Answer loadUserQuestionAnswer(int CourseID, int LessonID, int QuizID, int QuestionID, int UserID, int AttemptID) {
        String sql = "Select * From [UserAnswer] ua "
                + "\n Join [Answer] a On ua.CourseID = a.CourseID And ua.LessonID = a.LessonID And ua.QuizID = a.QuizID And ua.QuestionID = a.QuestionID"
                + "\n Where ua.CourseID = ? And ua.LessonID = ? And ua.QuizID = ? And ua.QuestionID = ? And ua.UserID = ? And ua.AttemptID = ?";
        Answer ans = new Answer();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, LessonID);
            ps.setInt(3, QuizID);
            ps.setInt(4, QuestionID);
            ps.setInt(5, UserID);
            ps.setInt(6, AttemptID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int AnswerID = rs.getInt("AnswerID");
                String Description = rs.getString("Description");
                int Role = rs.getInt("Role");
                ans = new Answer(CourseID, LessonID, QuizID, QuestionID, AnswerID, Description, Role);
            }
        } catch (Exception e) {
            status = "Error at loadUserQuestionAnswer " + e.getMessage();
        }
        return ans;
    }
    
    public Answer loadQuestionCorrectAnswer(int UserID, int AttemptID, int CourseID, int LessonID, int QuizID, int QuestionID) {
        String sql = "Select a.AnswerID, a.[Description], a.[Role] From [UserAnswer] ua"
                + "\nJoin [Answer] a On ua.CourseID = a.CourseID and ua.LessonID = a.LessonID and ua.QuizID = a.QuizID and ua.QuestionID = a.QuestionID"
                + "\nWhere a.[Role] = 2 And ua.UserID = ? and ua.CourseID = ? and ua.LessonID = ? and ua.QuizID = ? and ua.AttemptID = ? And ua.QuestionID = ?";
        Answer ans = new Answer();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.setInt(3, LessonID);
            ps.setInt(4, QuizID);
            ps.setInt(5, AttemptID);
            ps.setInt(6, QuestionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AnswerID = rs.getInt("AnswerID");
                String Description = rs.getString("Description");
                int Role = rs.getInt("Role");
                ans = new Answer(CourseID, LessonID, QuizID, QuestionID, AnswerID, Description, Role);
            }
        } catch (Exception e) {
            status = "Error at loadQuestionCorrectAnswer " + e.getMessage();
        }
        return ans;
    }

    public static void main(String[] agrs) {        
        System.out.println(INS.loadQuestionCorrectAnswer(1, 2, 1, 2, 1, 1).getAnswerID());
        System.out.println(INS.status);
    }
}
