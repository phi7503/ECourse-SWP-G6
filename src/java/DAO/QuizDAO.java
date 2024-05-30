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
                int QuizID = rs.getInt("QuizID");
                int LessonID = rs.getInt("LessonID");
                String QuizName = rs.getString("QuizName");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                ql.add(new Quiz(QuizID, LessonID, QuizName, CreateDate));
            }
        } catch (Exception e) {
            status = "Error at load Quiz " + e.getMessage();
        }
    }
    
    public void addUserQuizStatus(int UserID, int QuizID) {
        String sql = "Insert Into [QuizStatus] Values(?,?,0,0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, QuizID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at addUserQuizStatus " + e.getMessage();
        }
    }
    
    public int getUserQuizStatus(int UserID, int QuizID) {
        String sql = "Select * From [QuizStatus] Where UserID = ? And QuizID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, QuizID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return 1;
            }
        } catch (Exception e) {
            status = "Error at getUserQuizStatus " + e.getMessage();
        }
        return 0;
    }
    
    public static void main(String[] agrs){
        INS.load();
        System.out.println(INS.getStatus());
    }
}
