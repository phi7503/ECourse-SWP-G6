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
    
    public void load() {
        String sql = "Select * From [Lesson]";
        ll = new Vector<Lesson>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int LessonID = rs.getInt("LessonID");
                String LessonName = rs.getString("LessonName");
                float Price = rs.getFloat("Price");
                int DiscountID = rs.getInt("DiscountID");
                String Description = rs.getString("Description");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                ll.add(new Lesson(LessonID, LessonName, Price, DiscountID, Description, CreateDate));
            }
        } catch (Exception e) {
            status = "Error at load Lesson " + e.getMessage();
        }
    }
    
    public static void main(String[] args){
        INS.load();
        System.out.println(INS.getStatus());
    }
}
