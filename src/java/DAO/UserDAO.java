/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.util.*;
import Models.*;
import java.sql.*;


/**
 *
 * @author hi2ot
 */
public class UserDAO {

    private List<User> ul;
    private Connection con;
    private String status = "OK";
    public static UserDAO INS = new UserDAO();

    public List<User> getUl() {
        return ul;
    }

    public void setUl(List<User> ul) {
        this.ul = ul;
    }

    private UserDAO() {
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
        } catch (SQLException e) {
            status = "Error at load User" + e.getMessage();
        }
    }

    public Vector<Course> loadUserOwnCourse(int UserID) {
        String sql = "Select c.CourseID, c.CourseName, c.Price, c.Description, c.CreateDate, c. From [OwnCourse] oc Join [Course] c On oc.CourseID = c.CourseID Where oc.UserID = ?";
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
                UserID = rs.getInt("UserID");
                course.add(new Course(CourseID, CourseName, Price, Description, CreateDate, UserID));
            }
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
                java.sql.Timestamp SubmittedDate = rs.getTimestamp("SubmittedDate");
                int Finished = rs.getInt("Finished");
                list.add(new Attempt(UserID, CourseID, LessonID, QuizID, AttemptID, AttemptDate, SubmittedDate, Finished));
            }
        } catch (SQLException e) {
            status = "Error at loadUserQuizAttempt " + e.getMessage();
        }
        return list;
    }

    public Attempt createNewUserQuizAttempt(int UserID, int CourseID, int LessonID, int QuizID) {
        String sql = "Insert Into [Attempt] Values (?,?,?,?,?,?,?,?)";
        Vector<Attempt> list = loadUserQuizAttempt(UserID, CourseID, LessonID, QuizID);
        java.util.Date date = new java.util.Date();
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, QuizDAO.INS.getQuizTimeLimt(CourseID, LessonID, QuizID));
        java.sql.Timestamp tmp = new java.sql.Timestamp(date.getTime());      
        java.util.Date date2 = calendar.getTime();
        java.sql.Timestamp tmp2 = new java.sql.Timestamp(date2.getTime());
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.setInt(3, LessonID);
            ps.setInt(4, QuizID);
            ps.setInt(5, list.size() + 1);
            ps.setTimestamp(6, tmp);
            ps.setTimestamp(7, tmp2);
            ps.setInt(8, 0);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at createUserQuizAttempt " + e.getMessage();
        }
        return new Attempt(UserID, CourseID, LessonID, QuizID, list.size() + 1, tmp, tmp2, 0);
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
        } catch (SQLException e) {
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
            } catch (SQLException e) {
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
        } catch (SQLException e) {
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
            } catch (SQLException e) {
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
    
    public Attempt getNewestAttempt(int UserID, int CourseID, int LessonID, int QuizID) {
        String sql = "Select Top 1 * From [Attempt] Where UserID = ? And CourseID = ? And LessonID = ? And QuizID = ? Order By AttemptDate desc";        
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
                java.sql.Timestamp SubmittedDate = rs.getTimestamp("SubmittedDate");
                int Finished = rs.getInt("Finished");
                return new Attempt(UserID, CourseID, LessonID, QuizID, AttemptID, AttemptDate, SubmittedDate, Finished);
            }
        } catch (SQLException e) {
            status = "Error at getNewestAttempt " + e.getMessage();
        }
        return null;
    }        
    
    public void updateSubmittedTime(int UserID, int CourseID, int LessonID, int QuizID, int AttemptID, java.util.Date Time) {
        Attempt atm = INS.getNewestAttempt(UserID, CourseID, LessonID, QuizID);        
        String sql = "Update [Attempt] Set SubmittedDate = ?, Finished = 1 Where UserID = ? And CourseID = ? And LessonID = ? And QuizID = ? And AttemptID = ?";
        java.sql.Timestamp tmp = new java.sql.Timestamp(Time.getTime());
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setTimestamp(1, tmp);
            ps.setInt(2, UserID);
            ps.setInt(3, CourseID);
            ps.setInt(4, LessonID);
            ps.setInt(5, QuizID);
            ps.setInt(6, AttemptID);    
            ps.execute();
        } catch (SQLException e) {
            status = "Error at updateSubmittedTime " + e.getMessage();
        }
        
    }

    public static void main(String agrs[]) {
        INS.createNewUserQuizAttempt(4, 1, 1, 1);
        System.out.println(INS.status);
    }
}
