/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class CategoryDAO {
    List<Category> ql;
    private Connection con;
    private String status = "OK";
    public static CategoryDAO INS = new CategoryDAO();
    
    public static void main(String[] args) throws ParseException {
        CategoryDAO ud = INS;
        System.out.println(ud.getAllCategory());
    }
  
    
    private CategoryDAO() {
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
    
    public Vector<Category> getAllCategory() {
        String sql = "Select * From [Category]";
        Vector<Category> list = new Vector<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
               
                list.add(new Category(CategoryID, CategoryName));
            }
        } catch (Exception e) {
            status = "Error at load Quiz " + e.getMessage();
        }
        return list;
    }
    
    
    public void NewCategory(int id, String name) {
        String xSql = "INSERT INTO [dbo].[Category] ([CategoryID],[CategoryName]) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(xSql);
            ResultSet rs;
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public void updateCategory(int id, String name) {
        String sql = "UPDATE [dbo].[Category] SET CategoryName = ? WHERE CategoryID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error updating Category: " + e.getMessage();
        }
    }
    
    public void deleteCategory(int id) {
        String sql = "DELETE FROM [dbo].[Category] WHERE CategoryID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error deleting Category: " + e.getMessage();
        }
    }
}
