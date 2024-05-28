
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
public class DiscountDAO {
    List<Discount> dl;
    private Connection con;
    private String status = "OK";
    public static DiscountDAO INS = new DiscountDAO();

    public List<Discount> getDl() {
        return dl;
    }

    public void setDl(List<Discount> dl) {
        this.dl = dl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    private DiscountDAO() {
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
        String sql = "Select * From [Discount]";
        dl = new Vector<Discount>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               int DiscountID = rs.getInt("DiscountID");
               float Percentage = rs.getFloat("Percentage");
               dl.add(new Discount(DiscountID, Percentage));
            }
        } catch (Exception e) {
            status = "Error at load Discount " + e.getMessage();
        }
    }
    
    public static void main(String[] agrs){
        INS.load();
        System.out.println(INS.getStatus());
    }
}

