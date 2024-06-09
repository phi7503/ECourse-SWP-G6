/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.*;
import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author hi2ot
 */
public class CourseDAO {
    
    private List<Course> cl;
    private Connection con;
    private String status = "OK";
    public static CourseDAO INS = new CourseDAO();
    
    private CourseDAO() {
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
    
    public void loadCourse() {
        String sql = "Select * From [Course]";
        cl = new Vector<Course>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseID = rs.getInt("CourseID");
                String CourseName = rs.getString("CourseName");
                float Price = rs.getFloat("Price");
                String Description = rs.getString("Description");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                cl.add(new Course(CourseID, CourseName, Price, Description, CreateDate));
            }
        } catch (Exception e) {
            status = "Error at load Course " + e.getMessage();
        }
    }
    
}
