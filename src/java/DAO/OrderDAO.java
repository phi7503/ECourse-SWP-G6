/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.LessonDAO.INS;
import Models.Lesson;
import Models.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class OrderDAO {

    List<Order> orders;
    private Connection con;
    private String status = "OK";
    public static OrderDAO INS = new OrderDAO();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderDAO() {
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

    public boolean deleteOrder(String orderId) {
        String sql = "   DELETE FROM [Order] WHERE OrderID = " + orderId;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
            status = "Error at load Lesson " + e.getMessage();
            System.out.println(status);
            return false;
        }

        return true;
    }
    
    public static void main(String[] args) {
        OrderDAO.INS.deleteOrder("2");
    }

    public List<Order> loadByUser(int userId) {
        String sql = ""
                + "SELECT DISTINCT\n"
                + "o.OrderID, o.Status\n"
                + "FROM [Order] o \n"
                + "JOIN [OrderLesson] ol ON o.OrderID = ol.OrderID\n"
                + "WHERE o.UserID = " + userId
                + " ORDER BY o.OrderID";
        orders = new Vector<Order>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("OrderID"));
                order.setStatus(rs.getInt("Status"));
                order.setLessons(LessonDAO.INS.loadByOrder(order.getId()));

                orders.add(order);
            }
        } catch (Exception e) {
            status = "Error at load Lesson " + e.getMessage();
        }

        return orders;
    }
}
