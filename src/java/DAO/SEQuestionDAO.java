/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author hi2ot
 */
public class SEQuestionDAO {
    List<SEQuestion> qel;
    private Connection con;
    private String status = "OK";
    public static SEQuestionDAO INS = new SEQuestionDAO();

    public List<SEQuestion> getQel() {
        return qel;
    }

    public void setQel(List<SEQuestion> qel) {
        this.qel = qel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    private SEQuestionDAO() {
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
        String sql = "Select * From [SEQuestion]";
        qel = new Vector<SEQuestion>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               int SecurityQuestionID = rs.getInt("SecurityQuestionID");
               String Question = rs.getString("Question");
               qel.add(new SEQuestion(SecurityQuestionID, Question));
            }
        } catch (Exception e) {
            status = "Error at load SEQuestion " + e.getMessage();
        }
    }
    
    public static void main(String[] agrs) {
        SEQuestionDAO.INS.load();
        System.out.println(INS.getStatus());
    }
}
