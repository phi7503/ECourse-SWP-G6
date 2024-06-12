
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

import java.util.logging.Level;
import java.util.logging.Logger;

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
<<<<<<< HEAD
    
    public void loadLesson() {
=======

    public void load() {
>>>>>>> 55609f03a79a05168caa2a99f12537d0da970c95
        String sql = "Select * From [Lesson]";
        ll = new Vector<Lesson>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseID = rs.getInt("CourseID");
                int LessonID = rs.getInt("LessonID");
                String LessonName = rs.getString("LessonName");                              
                String Description = rs.getString("Description");                
                ll.add(new Lesson(CourseID, LessonID, LessonName, Description));
            }
        } catch (Exception e) {
            status = "Error at load Lesson " + e.getMessage();
        }
    }
<<<<<<< HEAD
    
    public Vector<LessonDoc> loadLessonDoc(int CourseID, int LessonID) {
        String sql = "Select * From [LessonDoc] Where CourseID = ? And LessonID = ?";
        Vector<LessonDoc> list = new Vector<LessonDoc>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, LessonID);           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int DocID = rs.getInt("DocID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Link = rs.getString("Link");
                list.add(new LessonDoc(CourseID, LessonID, DocID, Title, Description, Link));
            }
        } catch (Exception e) {
            status = "Error at load LessonDoc " + e.getMessage();            
        }
        return list;
    }
    
    public static void main(String[] args){        
        INS.loadLesson();
        System.out.println(INS.getLl().size());
=======

    public List<Lesson> loadByOrder(int orderId) {
        String sql = ""
                + "SELECT \n"
                + "l.LessonID, l.LessonName, l.Price, l.DiscountID,\n"
                + "l.Description, l.CreateDate, l.TagLine, l.Title, \n"
                + "l.Image\n"
                + "FROM [OrderLesson] ol \n"
                + "JOIN [Lesson] l ON l.LessonID = ol.LessonID\n"
                + "WHERE ol.OrderID = " + orderId;
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
                Lesson lesson = new Lesson(LessonID, LessonName, Price, DiscountID, Description, CreateDate);
           
                lesson.setImage(rs.getString("Image"));
                
                ll.add(lesson);
            }
        } catch (Exception e) {
            status = "Error at load Lesson " + e.getMessage();
        }

        return ll;
    }

    public static void main(String[] args) {
        INS.load();
        System.out.println(INS.getStatus());
    }   
    
    public Lesson getLessonByID(int id) {
        try {
            String sql = """
                         SELECT [LessonID]
                                 ,[LessonName]
                                 ,[Price]
                                 ,d.[Percentage]
                                 ,[Description]
                                 ,[CreateDate]
                                 ,[TagLine]
                                 ,[Title]
                                 ,[Image]
                             FROM [ECourse].[dbo].[Lesson] le left join Discount d on le.DiscountID = d.DiscountID 
                             where LessonID = ?""";
            Connection connection = con;
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, id);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt(1));
                lesson.setName(rs.getString(2));
                lesson.setPrice(rs.getDouble(3));
                lesson.setPercentage(rs.getDouble(4));
                lesson.setDescription(rs.getString(5));
                lesson.setCreatedDate(rs.getDate(6));
                lesson.setTagline(rs.getString(7));
                lesson.setTitle(rs.getString(8));
                lesson.setImage(rs.getString(9));
                return lesson;
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
>>>>>>> 55609f03a79a05168caa2a99f12537d0da970c95
    }

    public int insertSession(int userID) {
        try {
            String sql = """
                         INSERT INTO [dbo].[Session]
                                    ([UserID])
                              VALUES
                                    (?)
                         """;
            Connection connection = con;
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, userID);
            ptm.executeUpdate();
            String xSql = """
                          SELECT top 1 SessionID
                            FROM [ECourse].[dbo].[Session] order by SessionID desc""";
            PreparedStatement qtm = connection.prepareStatement(xSql);
            ResultSet rs = qtm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void registerLesson(int session, int lessonID) {
        try {
            String sql = """
                        INSERT INTO [dbo].[Cart]
                                               ([SessionID]
                                               ,[LessonID])
                                         VALUES
                                               (?,?)
                         """;
            Connection connection = con;
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, session);
            ptm.setInt(2, lessonID);
            ptm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean checkRegistered(int userID) {
        try {
            String sql = """
                         select * from Cart c join [Session] s on c.SessionID = s.SessionID where s.UserID = ?""";
            Connection connection = con;
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, userID);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


