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
               int QuestionID = rs.getInt("QuestionID");
               int AnswerID = rs.getInt("AnswerID");
               String Description = rs.getString("Description");
               int Role = rs.getInt("Role");
               al.add(new Answer(QuestionID, AnswerID, Description, Role));
            }
        } catch (Exception e) {
            status = "Error at load Answer " + e.getMessage();
        }
    }
    
    public Vector<Answer> loadAnswerByQuestionId(int ID) {
        String sql = "Select * From [Answer] Where QuestionID = ?";
        Vector<Answer> list = new Vector<Answer>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               int QuestionID = rs.getInt("QuestionID");
               int AnswerID = rs.getInt("AnswerID");
               String Description = rs.getString("Description");
               int Role = rs.getInt("Role");
               list.add(new Answer(QuestionID, AnswerID, Description, Role));
            }
        } catch (Exception e) {
            status = "Error at load loadAnswerByQuestionId " + e.getMessage();
        }
        return list;
    }
    
    public void addUserAnswer(int UserID, int QuestionID, int AnswerID){
        String sql = "Delete From [UserAnswer] Where UserID = ? And QuestionID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, QuestionID);            
            ps.execute();
        } catch (Exception e) {
            status = "Error at addUserAnswer" + e.getMessage();
        }
        
        sql = "Insert Into [UserAnswer] Values (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, QuestionID);
            ps.setInt(3, AnswerID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at addUserAnswer" + e.getMessage();
        }
    }
    
    public int getAnswerbyUserID(int UserID, int QuestionID) {
        String sql = "Select * From [UserAnswer] Where UserID = ? And QuestionID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, QuestionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AnswerID = rs.getInt("AnswerID");
                return AnswerID;
            }
        } catch (Exception e) {
            status = "Error at getAnswerbyUserID" + e.getMessage();
        }
        return -1;
    }
    
    public int getCorrectAnswer(int QuestionID) {
        String sql = "Select * From [Answer] Where QuestionID = ? and Role = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, QuestionID);            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AnswerID = rs.getInt("AnswerID");
                return AnswerID;
            }
        } catch (Exception e) {
            status = "Error at getCorrectAnswer " + e.getMessage();
        }
        return -1;
    }
    
    public static void main(String[] agrs) {
        INS.load();
        System.out.println(INS.loadAnswerByQuestionId(1).size());
        System.out.println(INS.getStatus());
    }
}
