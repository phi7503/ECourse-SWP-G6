/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.util.*;
import Models.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

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
                String salt = rs.getString("salt");
                String Password = rs.getString("Password");
                String Mail = rs.getString("Mail");
                String FullName = rs.getString("FullName");
                java.sql.Date DoB = rs.getDate("DoB");
                int SecurityQuestionID = rs.getInt("SecurityQuestionID");
                String Answer = rs.getString("Answer");
                int Role = rs.getInt("Role");
                ul.add(new User(UserID, UserName, salt, Password, Mail, FullName, DoB, SecurityQuestionID, Answer, Role));
            }
        } catch (SQLException e) {
            status = "Error at load User" + e.getMessage();
        }
    }

    public Vector<Course> loadUserOwnCourse(int UserID) {
        String sql = "Select c.* From [OwnCourse] oc Join [Course] c On oc.CourseID = c.CourseID Where oc.UserID = ?";
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

    public void createNewQuestionList(int UserID, int CourseID, int LessonID, int QuizID, int AttemptID) {
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
                int Status = rs.getInt("Status");
                QuestionList.add(new Question(CourseID, LessonID, QuizID, QuestionID, Question, Explaination, Status));
            }
        } catch (SQLException e) {
            status = "Error at createNewQuestionList " + e.getMessage();
        }
        SecureRandom random = new SecureRandom();
        int[] NumList = new int[QuestionList.size() + 2];
        for (int i = 0; i <= QuestionList.size(); i++) {
            NumList[i] = 0;
        }

        Quiz quiz = QuizDAO.INS.getQuiz(CourseID, LessonID, QuizID);

        for (int i = 0; i < quiz.getNoQ(); i++) {
            int num = random.nextInt() % (QuestionList.size() + 1);
            if (num < 0) {
                num *= -1;
            }
            while (NumList[num] == 1) {
                num = random.nextInt() % (QuestionList.size() + 1);
                if (num < 0) {
                    num *= -1;
                }
            }
            NumList[num] = 1;
            Question x = QuestionList.get(num);
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
                    int Status = rs.getInt("Status");
                    QuestionList.add(new Question(CourseID, LessonID, QuizID, QuestionID, Question, Explaination, Status));
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
        INS.load();
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

    public String LoginCheck(String UserName, String Password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String sql = "Select * From [User] Where UserName = ?";
        String ssalt = "";
        String uPassword = "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, UserName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ssalt = rs.getString("salt");
                uPassword = rs.getString("Password");
            }
        } catch (Exception e) {
            status = "Error at loadSalt " + e.getMessage();
        }

        if (ssalt == null) {
            return "No Username Exist!";
        } else {
            Base64.Decoder dnc = Base64.getDecoder();
            byte[] salt = dnc.decode(ssalt);
            KeySpec spec = new PBEKeySpec(Password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            if (!enc.encodeToString(hash).equals(uPassword)) {
                return "Wrong Password!";
            }
            return null;
        }
    }

    public void addUser(String username, String password, String salt, String mail, String fullname, String dob, int sq, String answer, int role) {
        INS.load();
        String sql = "Insert Into [User] Values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, INS.getUl().size() + 1);
            ps.setString(2, username);
            ps.setString(4, password);
            ps.setString(3, salt);
            ps.setString(5, mail);
            ps.setString(6, fullname);
            ps.setString(7, dob);
            ps.setInt(8, sq);
            ps.setString(9, answer);
            ps.setInt(10, role);
            ps.setInt(11, 1);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at addUser " + e.getMessage();
        }
    }

    public ArrayList<Course> loadUserCart(int UserID) {
        ArrayList<Course> CourseList = new ArrayList<>();
        String sql = "Select ce.* From [Cart] c  Join Course ce On c.CourseID = ce.CourseID Where c.UserID = ?";
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
                CourseList.add(new Course(CourseID, CourseName, Price, Description, CreateDate, UserID));
            }

        } catch (SQLException e) {
            status = "Error at loadUserCart " + e.getMessage();
        }
        return CourseList;
    }

    public void deleteCartCourse(int UserID, int CourseID) {
        String sql = "Delete From [Cart] Where UserID = ? And CourseID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at deleteCartCourse " + e.getMessage();
        }
    }

    public void addUserOwnCourse(int UserID, int CourseID, int OrderID) {
        INS.deleteCartCourse(UserID, CourseID);
        String sql = "Insert Into [OwnCourse] Values(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, UserID);
            ps.setInt(3, OrderID);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at addUserOwnCourse " + e.getMessage();
        }
    }

    public void addUserCart(int UserID, int CourseID) {
        String sql = "Insert Into [Cart] Values(?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at addUserCart " + e.getMessage();
        }
    }

    public int checkIfCourseinCart(int UserID, int CourseID) {
        String sql = "Select * From [Cart] Where UserID = ? And CourseID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return 1;
            }
        } catch (SQLException e) {
            status = "Error at checkIfCourseinCart " + e.getMessage();
        }
        return 0;
    }

    public String getNameById(int UserID) {
        String sql = "Select * From [User] Where UserID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("FullName");
            }
        } catch (SQLException e) {
            status = "Error at getNameById " + e.getMessage();
        }
        return null;
    }

    public void addFeedback(int UserID, int CourseID, String Description) {
        String sql = "Select * From [Feedback] Where CourseID = ?";
        int cnt = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cnt++;
            }
        } catch (SQLException e) {
            status = "Error at addFeedback " + e.getMessage();
        }

        java.util.Date date = new java.util.Date();
        java.sql.Date CreateDate = new java.sql.Date(date.getTime());
        sql = "Insert Into [Feedback] Values(?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.setInt(3, cnt + 1);
            ps.setDate(4, CreateDate);
            ps.setString(5, Description);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at addFeedback " + e.getMessage();
        }
    }

    public void updateProfile(int UserID, String Fullname, String dob, int SQ, String Answer) {
        String sql = "Update [User]"
                + "\n Set FullName = ?, DoB = ?, SecurityQuestionID = ?, Answer = ?"
                + "\n Where UserID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Fullname);
            ps.setString(2, dob);
            ps.setInt(3, SQ);
            ps.setString(4, Answer);
            ps.setInt(5, UserID);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at updateProfile " + e.getMessage();
        }
    }

    public String changePass(int UserID, String oldpass, String newpass, String confirm) throws InvalidKeySpecException {
        try {
            String sql = "Select * From [User] Where UserID = ?";
            String pass = "";
            String ssalt = "";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, UserID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    pass = rs.getString("Password");
                    ssalt = rs.getString("salt");
                }
            } catch (SQLException e) {
                status = "Error at changePass " + e.getMessage();
            }

            Base64.Decoder dnc = Base64.getDecoder();
            byte[] salt = dnc.decode(ssalt);
            KeySpec spec = new PBEKeySpec(oldpass.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            if (!enc.encodeToString(hash).equals(pass)) {
                return "Wrong Password!";
            } else if (!newpass.equals(confirm)) {
                return "Confirm Password Not Match!";
            }

            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);
            spec = new PBEKeySpec(newpass.toCharArray(), salt, 65536, 128);
            hash = factory.generateSecret(spec).getEncoded();

            sql = "Update [User]"
                    + "\n Set Password = ?, salt = ?"
                    + "\n Where UserID = ?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, enc.encodeToString(hash));
                ps.setString(2, enc.encodeToString(salt));
                ps.setInt(3, UserID);
                ps.execute();
                return null;
            } catch (SQLException e) {
                status = "Error at changePassword " + e.getMessage();
            }

            return "Undefined Error!";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "Undefined Error!";
        }
    }

    public float getCourseProgress(int UserID, int CourseID) {
        float inum = 0;
        float cnum = 0;
        List<Lesson> LessonList = LessonDAO.INS.loadLessonByCourseID(CourseID);
        for (Lesson x : LessonList) {
            List<LessonDoc> DocList = LessonDAO.INS.loadLessonDoc(CourseID, x.getLessonID());
            for (LessonDoc y : DocList) {
                String sql = "Select * From [DocProgress] Where UserID = ? And CourseID = ? And LessonID = ? And DocID = ?";
                try {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, UserID);
                    ps.setInt(2, CourseID);
                    ps.setInt(3, x.getLessonID());
                    ps.setInt(4, y.getDocID());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        inum++;
                        if (rs.getInt("Status") == 1) {
                            cnum++;
                        }
                    }
                } catch (SQLException e) {
                    status = "Error at getCourseProgress " + e.getMessage();
                    return 0;
                }
            }

            List<Quiz> QuizList = QuizDAO.INS.loadQuizByLesson(CourseID, x.getLessonID());
            for (Quiz y : QuizList) {
                String sql = "Select * From [QuizProgress] Where UserID = ? And CourseID = ? And LessonID = ? And QuizID = ?";
                try {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, UserID);
                    ps.setInt(2, CourseID);
                    ps.setInt(3, x.getLessonID());
                    ps.setInt(4, y.getQuizID());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        inum++;
                        if (rs.getInt("Status") == 1) {
                            cnum++;
                        }
                    }
                } catch (SQLException e) {
                    status = "Error at getCourseProgress " + e.getMessage();
                    return 0;
                }
            }
        }
        System.out.println(inum);
        if (inum != 0) {
            return (float) (cnum / inum) * 100;
        } else {
            return 0;
        }
    }

    public void addDocProgress(int UserID, int CourseID, int LessonID) {
        List<LessonDoc> DocList = LessonDAO.INS.loadLessonDoc(CourseID, LessonID);
        String sql = "Insert Into [DocProgress] Values(?,?,?,?,?)";
        for (LessonDoc x : DocList) {
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, UserID);
                ps.setInt(2, CourseID);
                ps.setInt(3, LessonID);
                ps.setInt(4, x.getDocID());
                ps.setInt(5, 0);
                ps.execute();
            } catch (SQLException e) {
                status = "Error at addDocProgress " + e.getMessage();
            }
        }
    }

    public void addQuizProgress(int UserID, int CourseID, int LessonID) {
        List<Quiz> QuizList = QuizDAO.INS.loadQuizByLesson(CourseID, LessonID);
        String sql = "Insert Into [QuizProgress] Values(?,?,?,?,?)";
        for (Quiz x : QuizList) {
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, UserID);
                ps.setInt(2, CourseID);
                ps.setInt(3, LessonID);
                ps.setInt(4, x.getQuizID());
                ps.setInt(5, 0);
                ps.execute();
            } catch (SQLException e) {
                status = "Error at addQuizProgress " + e.getMessage();
            }
        }
    }

    public int getQuizProgress(int UserID, int CourseID, int LessonID, int QuizID) {
        String sql = "Select * From [QuizProgress] Where UserID = ? And CourseID = ? And LessonID = ? And QuizID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.setInt(3, LessonID);
            ps.setInt(4, QuizID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Status");
            }
        } catch (SQLException e) {
            status = "Error at getQuizProgress " + e.getMessage();
        }
        return 0;
    }

    public int getDocProgress(int UserID, int CourseID, int LessonID, int DocID) {
        String sql = "Select * From [DocProgress] Where UserID = ? And CourseID = ? And LessonID = ? And DocID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.setInt(3, LessonID);
            ps.setInt(4, DocID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Status");
            }
        } catch (SQLException e) {
            status = "Error at getDocProgress " + e.getMessage();
        }
        return 0;
    }

    public void updateDocProgress(int UserID, int CourseID, int LessonID, int DocID) {
        String sql = "Update [DocProgress]"
                + "\n Set Status = 1"
                + "\n Where UserID = ? And CourseID = ? And LessonID = ? And DocID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.setInt(3, LessonID);
            ps.setInt(4, DocID);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at updateDocProgress " + e.getMessage();
        }
    }

    public void updateQuizProgress(int UserID, int CourseID, int LessonID, int QuizID) {
        String sql = "Update [QuizProgress]"
                + "\n Set Status = 1"
                + "\n Where UserID = ? And CourseID = ? And LessonID = ? And QuizID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ps.setInt(2, CourseID);
            ps.setInt(3, LessonID);
            ps.setInt(4, QuizID);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at updateQuizProgress " + e.getMessage();
        }
    }

    public static void main(String agrs[]) throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println(INS.getDocProgress(4, 1, 1, 1));
        System.out.println(INS.status);
    }
}
