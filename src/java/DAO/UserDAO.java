
package DAO;

import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class UserDAO extends DBContext{
    
    private List<User> ul;
    private Connection con;
    private String status = "OK";
    public static final UserDAO INSTANCE = new UserDAO();
    
    public List<User> getUl() {
        return ul;
    }

    
    public void setUl(List<User> ul) {
        this.ul = ul;
    }

    public UserDAO() {}
    
    public User getUserById(int id){
        try{
            String sql = """
                         SELECT [UserID]
                                 ,[UserName]
                                 ,[Password]
                                 ,[Mail]
                                 ,[FullName]
                                 ,[DoB]
                                 ,s.[Question]
                                 ,[Answer]
                                 ,[Role]
                                 ,[Status]
                             FROM [ECourse].[dbo].[User] le left join SEQuestion s on le.SecurityQuestionID = s.SecurityQuestionID 
                             where UserID = ?""";
            Connection connection = getConnection();
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, id);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setMail(rs.getString(4));
                user.setFullName(rs.getString(5));
                user.setDoB(rs.getDate(6));
                user.setQuestion(rs.getString(7));
                user.setAnswer(rs.getString(8));
                user.setRole(rs.getInt(9));
                user.setStatus(rs.getInt(10));
                return user;
            }
        }catch(Exception ex){
             Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public User check(String username, String password) {
        try {
            String sql = """    
                         SELECT [UserID]
                                 ,[UserName]
                                 ,[Password]
                                 ,[Mail]
                                 ,[FullName]
                                 ,[DoB]
                                 ,s.[Question]
                                 ,[Answer]
                                 ,[Role]
                                 ,[Status]
                             FROM [ECourse].[dbo].[User] le left join SEQuestion s on le.SecurityQuestionID = s.SecurityQuestionID 
                             where UserName = ? and Password = ?""";
            Connection connection = getConnection();
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, username);
            ptm.setString(2, password);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                user.setMail(rs.getString("Mail"));
                user.setFullName(rs.getString("FullName"));
                user.setDoB(rs.getDate("DoB"));
                user.setQuestion(rs.getString("Question"));
                user.setAnswer(rs.getString("Answer"));
                user.setRole(rs.getInt("Role"));
                user.setStatus(rs.getInt("Status"));
                return user;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void change(User user) {
        try {
            String sql = "UPDATE [ECourse].[dbo].[User] SET [Password] = ? WHERE [UserID] = ?";
            Connection connection = getConnection();
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, user.getPassword());
            ptm.setInt(2, user.getUserID());
            ptm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
