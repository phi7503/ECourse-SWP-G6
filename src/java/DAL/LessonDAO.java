/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Lesson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class LessonDAO extends DBContext {

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
            Connection connection = getConnection();
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
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
