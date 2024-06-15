
package DAO;


import java.sql.Connection;
import java.util.*;
import Models.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;




/**
 *
 * @author hi2ot
 */
public class UserDAO {

    private List<User> ul;
    private Connection con;
    private String status = "OK";
    public static UserDAO INS = new UserDAO();
    private String xSql;
   

    public List<User> getUl() {
        return ul;
    }

    public void setUl(List<User> ul) {
        this.ul = ul;
    }

    public UserDAO() {
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
        String sql = "Select * From [User]";
        ul = new Vector<User>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int UserID = rs.getInt("UserID");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String Mail = rs.getString("Mail");
                String FullName = rs.getString("FullName");
                java.sql.Date DoB = rs.getDate("DoB");
                int SecurityQuestionID = rs.getInt("SecurityQuestionID");
                String Answer = rs.getString("Answer");
                int Role = rs.getInt("Role");
                
                ul.add(new User(UserID, UserName, Password, Mail, FullName, DoB, SecurityQuestionID, Answer, Role));
            }
        } catch (Exception e) {
            status = "Error at load User" + e.getMessage();
            System.out.println(status);
        }
        
    }

    public Vector<Course> loadUserOwnCourse(int UserID) {
        String sql = "Select * From [OwnCourse] oc Join [Course] c On oc.CourseID = c.CourseID Where UserID = ?";
        Vector<Course> course = new Vector<Course>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseID = rs.getInt("CourseID");
                String CourseName = rs.getString("CourseName");
                float Price = rs.getFloat("Price");
                String Description = rs.getString("Description");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                course.add(new Course(CourseID, CourseName, Price, Description, CreateDate));
            }
        } catch (Exception e) {
            status = "Error at loadUserOwnCourse " + e.getMessage();
        }
        return course;
    }

    public int checkOwnCourse(int UserID, int CourseID) {
        String sql = "Select * From [OwnCourse] Where UserID = ? And CourseID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return 1;
            }
        } catch (Exception e) {
            status = "Error at checkOwnCourse " + e.getMessage();
        }
        return 0;
    }

    public Vector<Attempt> loadUserQuizAttempt(int UserID, int CourseID, int LessonID, int QuizID) {
        String sql = "Select * From [Attempt] Where UserID = ? And CourseID = ? And LessonID = ? And QuizID = ?";
        Vector<Attempt> list = new Vector<Attempt>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.setInt(3, LessonID);
            ps.setInt(4, QuizID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AttemptID = rs.getInt("AttemptID");
                java.sql.Timestamp AttemptDate = rs.getTimestamp("AttemptDate");
                list.add(new Attempt(UserID, CourseID, LessonID, QuizID, AttemptID, AttemptDate));
            }
        } catch (Exception e) {
            status = "Error at loadUserQuizAttempt " + e.getMessage();
        }
        return list;
    }

    public Attempt createNewUserQuizAttempt(int UserID, int CourseID, int LessonID, int QuizID) {
        String sql = "Insert Into [Attempt] Values (?,?,?,?,?,?,?)";
        Vector<Attempt> list = loadUserQuizAttempt(UserID, CourseID, LessonID, QuizID);
        java.util.Date date = new java.util.Date();
        java.sql.Timestamp tmp = new java.sql.Timestamp(date.getTime());
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.setInt(3, LessonID);
            ps.setInt(4, QuizID);
            ps.setInt(5, list.size() + 1);
            ps.setTimestamp(6, tmp);
            ps.setTimestamp(7, null);
            ps.execute();
        } catch (Exception e) {
            status = "Error at createUserQuizAttempt " + e.getMessage();
        }
        return new Attempt(UserID, CourseID, LessonID, QuizID, list.size() + 1, tmp);
    }

    public Vector<Question> createNewQuestionList(int UserID, int CourseID, int LessonID, int QuizID, int AttemptID) {
        String sql = "Select * From [Question] Where CourseID = ? And LessonID = ? And QuizID = ?";
        Vector<Question> QuestionList = new Vector<Question>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, LessonID);
            ps.setInt(3, QuizID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int QuestionID = rs.getInt("QuestionID");
                String Question = rs.getString("Question");
                String Explaination = rs.getString("Explaination");
                QuestionList.add(new Question(CourseID, LessonID, QuizID, QuestionID, Question, Explaination));
            }
        } catch (Exception e) {
            status = "Error at createNewQuestionList " + e.getMessage();
        }
        for (Question x : QuestionList) {
            sql = "Insert Into [UserAnswer] Values (?,?,?,?,?,?,-1)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, UserID);
                ps.setInt(2, AttemptID);
                ps.setInt(3, CourseID);
                ps.setInt(4, LessonID);
                ps.setInt(5, QuizID);
                ps.setInt(6, x.getQuestionID());
                ps.execute();
            } catch (Exception e) {
                status = "Error at addNewUserAnswer " + e.getMessage();
            }
        }
        return QuestionList;
    }

    public Vector<Question> getListQuestionOnAttempt(int UserID, int CourseID, int LessonID, int QuizID, int AttemptID) {
        String sql = "Select * From [UserAnswer] ua"                
                + "\nWhere ua.UserID = ? and ua.CourseID = ? and ua.LessonID = ? and ua.QuizID = ? and ua.AttemptID = ?";
        List<Number> question = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.setInt(3, LessonID);
            ps.setInt(4, QuizID);
            ps.setInt(5, AttemptID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int QuestionID = rs.getInt("QuestionID");
                question.add(QuestionID);
            }
        } catch (Exception e) {
            status = "Error at getListQuestionOnAttempt " + e.getMessage();
        }

        sql = "Select * From [Question] Where QuestionID = ? And CourseID = ? And LessonID = ? And QuizID = ?";
        Vector<Question> QuestionList = new Vector<Question>();
        for (Number num : question) {
            int x = (int) num;
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, x);
                ps.setInt(2, CourseID);
                ps.setInt(3, LessonID);
                ps.setInt(4, QuizID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int QuestionID = rs.getInt("QuestionID");
                    String Question = rs.getString("Question");
                    String Explaination = rs.getString("Explaination");
                    QuestionList.add(new Question(CourseID, LessonID, QuizID, QuestionID, Question, Explaination));
                }
            } catch (Exception e) {
                status = "Error at getListQuestionOnAttempt " + e.getMessage();
            }
        }
        return QuestionList;
    }

    public int getAttemptMark(int UserID, int CourseID, int LessonID, int QuizID, int AttemptID) {
        String sql = "Select * From [UserAnswer] ua"
                + "\nJoin [Answer] a On ua.CourseID = a.CourseID and ua.LessonID = a.LessonID and ua.QuizID = a.QuizID and ua.QuestionID = a.QuestionID And ua.AnswerID = a.AnswerID"
                + "\nWhere a.[Role] = 2 And ua.UserID = ? and ua.CourseID = ? and ua.LessonID = ? and ua.QuizID = ? and ua.AttemptID = ?";
        int cnt = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.setInt(3, LessonID);
            ps.setInt(4, QuizID);
            ps.setInt(5, AttemptID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cnt++;
            }
        } catch (Exception e) {
            status = "Error at getAttemptMark " + e.getMessage();
        }
        return cnt;
    }

    public void updateUserAnswer(int UserID, int AttemptID, int CourseID, int LessonID, int QuizID, int QuestionID, int AnswerID) {
        String sql = "Update [UserAnswer]"
                + "\nSet AnswerID = ?"
                + "\nWhere UserID = ? And AttemptID = ? And CourseID = ? And LessonID = ? And QuizID = ? And QuestionID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, AnswerID);
            ps.setInt(2, UserID);
            ps.setInt(3, AttemptID);
            ps.setInt(4, CourseID);
            ps.setInt(5, LessonID);
            ps.setInt(6, QuizID);
            ps.setInt(7, QuestionID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at updateUserAnswer " + e.getMessage();
        }
    }

    public User getUserByName(String UserName) {
        for (User x : ul) {
            if (x.getUserName().equals(UserName)) {
                return x;
            }
        }
        return null;
    }

    public static void main(String agrs[]) {
        INS.updateUserAnswer(1, 1, 1, 2, 1, 1, 2);
        System.out.println(INS.status);
    }







   
    

    
    public Vector<User> getAllUser() {
        Vector<User> list = new Vector<>();
        String xSql = "SELECT * FROM [User]";
        try {
            PreparedStatement ps = con.prepareStatement(xSql);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt(1);
                String xUsername = rs.getString(2);
                String xPass = rs.getString(3);
                String xEmail = rs.getString(4);
                String xFullname = rs.getString(5);
                java.sql.Date xDate = rs.getDate(6);
                int xSecurityid = rs.getInt(7);
                String xAnswer = rs.getString(8);
                int xRole = rs.getInt(9);
                int xStatus = rs.getInt(10);

                User x = new User(xId, xUsername, xPass, xEmail, xFullname, xDate, xSecurityid, xAnswer, xRole);
                list.add(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return list;
    }
    
    public void Register(int id, String username, String pass, String email, String fullname, String date, int securityid, String answer, int role, int status) {
        xSql = "INSERT INTO [User] ([UserID], [UserName], [Password], [Mail], [FullName], [DoB], [SecurityQuestionID], [Answer], [Role], [Status]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(xSql); 
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, pass);
            ps.setString(4, email);
            ps.setString(5, fullname);
            ps.setString(6, date);
            ps.setInt(7, securityid);
            ps.setString(8, answer);
            ps.setInt(9, role);
            ps.setInt(10, status);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public Vector<String> getAllQuestion() {
        Vector<String> list = new Vector<>();
        xSql = "  Select * from [SEQuestion]";

        try {
            PreparedStatement st = con.prepareStatement(xSql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(2));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public String findQuestionByID(int id) {
        String sql = " select * from [SEQuestion] where SecurityQuestionID = ?";
        String s = "";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s = rs.getString("question");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return s;
    }

    public int findQuestionByName(String name) {
        String sql = "select * from [SEQuestion] where Question = ?";
        int id = 1;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("SecurityQuestionID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }
    
    public User findUserByID(int id) {
        String sql = "select * from [dbo].[User] where userid = ?";
        User s = null;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s = new User();
                s.setUserID(rs.getInt("userid"));
                s.setUserName(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setMail(rs.getString("mail"));
                s.setFullName(rs.getString("fullname"));
                s.setDoB(rs.getDate("dob"));
                s.setSecurityQuestionID(rs.getInt("securityquestionid"));
                s.setAnswer(rs.getString("answer"));
                s.setRole(rs.getInt("role"));
                s.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return s;
    }

    public void editUser(int id, String fullname, String email, String dob, int role, int status) {
        String sql = "UPDATE [dbo].[User] SET fullname = ?, mail = ?, dob = ?, role = ?, status = ? WHERE userid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, dob);
            ps.setInt(4, role);
            ps.setInt(5, status);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Vector<User> searchUsers(String search) {
        String sql = "SELECT * FROM [dbo].[User] WHERE fullname LIKE ? OR mail LIKE ?";
        Vector<User> list = new Vector<>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + search + "%");
            st.setString(2, "%" + search + "%");

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User s = new User();
                s.setUserID(rs.getInt("userid"));
                s.setUserName(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setMail(rs.getString("mail"));
                s.setFullName(rs.getString("fullname"));
                s.setDoB(rs.getDate("dob"));
                s.setSecurityQuestionID(rs.getInt("securityquestionid"));
                s.setAnswer(rs.getString("answer"));
                s.setRole(rs.getInt("role"));
                s.setStatus(rs.getInt("status"));

                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}

    


