/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
/**
 *
 * @author hi2ot
 */
public class OrderDAO {
    
    private Connection con;
    private String status = "OK";
    public static OrderDAO INS = new OrderDAO();
    
    private OrderDAO() {
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
    
    public int createOrder(int UserID, float Price) {
        String sql = "Select Top 1 OrderID From [Order] Where UserID = ? Order By CreateDate desc";
        int OrderID = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderID = rs.getInt("OrderID");
            }
        } catch (SQLException e) {
            status = "Error at createOrder " + e.getMessage();
        }
        OrderID++;
        java.util.Date Date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(Date.getTime());
        sql = "Insert Into [Order] Values(?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, OrderID);
            ps.setInt(2, UserID);
            ps.setDate(3, sqlDate);
            ps.setFloat(4, Price);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at createOrder " + e.getMessage();
        }
        return OrderID;
    }           
    
    public static void main(String[] args) {
        
        System.out.println(INS.status);
    }

}
