package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDAO extends DBContext {
  public Connection connection = null;
  public PreparedStatement ps = null;
  public ResultSet rs = null;
  public String xSql = null;

  public MyDAO() {
      try {
          connection = getConnection();
      } catch (Exception ex) {
          Logger.getLogger(MyDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  public void finalize() {
     try {
        if(connection != null) connection.close();
     }
     catch(Exception e) {
        e.printStackTrace();
     }
  }
    
}
