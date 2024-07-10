/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.*;
import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hi2ot
 */
public class CourseDAO {
    
    private List<Course> cl;
    private Connection con;
    private String status = "OK";
    public static CourseDAO INS = new CourseDAO();
    
    public List<Course> getCl() {
        return cl;
    }
    
    public void setCl(List<Course> cl) {
        this.cl = cl;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    private CourseDAO() {
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
    
    public void loadCourse() {
        String sql = "Select * From [Course]";
        cl = new Vector<Course>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseID = rs.getInt("CourseID");
                String CourseName = rs.getString("CourseName");
                float Price = rs.getFloat("Price");
                String Description = rs.getString("Description");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                int UserID = rs.getInt("UserID");
                cl.add(new Course(CourseID, CourseName, Price, Description, CreateDate, UserID));
            }
        } catch (Exception e) {
            status = "Error at load Course " + e.getMessage();
        }
    }
    
    public void addSubject(int SubjectID, String SubjectName) {
        String sql = "Insert Into [Subject] Values (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, SubjectID);
            ps.setString(2, SubjectName);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at addSubject " + e.getMessage();
        }
    }
    
    public void addCategory(int CategoryID, String CategoryName) {
        String sql = "Insert Into [Category] Values (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CategoryID);
            ps.setString(2, CategoryName);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at addCategory " + e.getMessage();
        }
    }
    
    public Vector<Subject> loadSubjectList() {
        String sql = "Select * From [Subject]";
        Vector<Subject> SubjectList = new Vector<Subject>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int SubjectID = rs.getInt("SubjectID");
                String SubjectName = rs.getString("SubjectName");
                SubjectList.add(new Subject(SubjectID, SubjectName));
            }
        } catch (SQLException e) {
            status = "Error at loadSubjectList " + e.getMessage();
        }
        return SubjectList;
    }
    
    public ArrayList<Category> loadCategoryList() {
        String sql = "Select * From [Category]";
        ArrayList<Category> CategoryList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                CategoryList.add(new Category(CategoryID, CategoryName));
            }
        } catch (SQLException e) {
            status = "Error at loadCategoryList " + e.getMessage();
        }
        return CategoryList;
    }
    
    public void deleteCategory(int CategoryID) {
        String sql = "Delete From [CourseCategory] Where CategoryID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CategoryID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at deleteCourseCategory " + e.getMessage();
        }
        sql = "Delete From [Category] Where CategoryID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CategoryID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at deleteCourseCategory " + e.getMessage();
        }
    }
    
    public void updateCategory(int CategoryID, String CategoryName) {
        String sql = "Update [Category]"
                + "\n Set CategoryName = ?"
                + "\n Where CategoryID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, CategoryName);
            ps.setInt(2, CategoryID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at updateCategory " + e.getMessage();
        }
        
    }
    
    public void deleteSubject(int SubjectID) {
        String sql = "Delete From [CourseSubject] Where SubjectID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, SubjectID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at deleteCourseSubject " + e.getMessage();
        }
        sql = "Delete From [Subject] Where SubjectID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, SubjectID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at deleteCourseSubject " + e.getMessage();
        }
    }
    
    public void updateSubject(int SubjectID, String SubjectName) {
        String sql = "Update [Subject]"
                + "\n Set SubjectName = ?"
                + "\n Where SubjectID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, SubjectName);
            ps.setInt(2, SubjectID);
            ps.execute();
        } catch (Exception e) {
            status = "Error at updateSubject " + e.getMessage();
        }
        
    }
    
    public ArrayList<Attempt> getUnfinishedAttempt() {
        ArrayList<Attempt> AttemptList = new ArrayList<>();
        String sql = "Select * From [Attempt] Where Finished = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int UserID = rs.getInt("UserID");
                int CourseID = rs.getInt("CourseID");
                int LessonID = rs.getInt("LessonID");
                int QuizID = rs.getInt("QuizID");
                int AttemptID = rs.getInt("AttemptID");
                java.sql.Timestamp tmp1 = rs.getTimestamp("AttemptDate");
                java.sql.Timestamp tmp2 = rs.getTimestamp("SubmittedDate");
                int Finished = rs.getInt("Finished");
                AttemptList.add(new Attempt(UserID, CourseID, LessonID, QuizID, AttemptID, tmp1, tmp2, Finished));
            }
        } catch (SQLException e) {
            status = "Error at getUnfinishedAttempt " + e.getMessage();
        }
        return AttemptList;
    }
    
    public void updateAttempt(Attempt atm) {
        String sql = "Update [Attempt] Set Finished = 1 Where UserID = ? And CourseID = ? And LessonID = ? And QuizID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, atm.getUserID());
            ps.setInt(2, atm.getCourseID());
            ps.setInt(3, atm.getLessonID());
            ps.setInt(4, atm.getQuizID());
            ps.execute();
        } catch (SQLException e) {
            status = "Error at updateAttempt " + e.getMessage();
        }
    }
    
    public int loadCategoryPNumber(int categoryID) {
        String sql = "Select * From [CourseCategory] Where CategoryID = ?";
        int cnt = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cnt++;
            }
        } catch (SQLException e) {
            status = "Error at loadCategoryPNumber " + e.getMessage();
        }
        return cnt;
    }
    
    public ArrayList<Course> getCourseList(List<Category> checkedCategory, List<Subject> checkedSubject) {
        String sql = "Select distinct c.* From [Course] c"
                + "\n Join [CourseCategory] cc on c.CourseID = cc.CourseID"
                + "\n Join [CourseSubject] cs on c.CourseID = cs.CourseID";
        int firstInsert = 1;
        
        for (Category cat : checkedCategory) {
            if (firstInsert == 1) {
                firstInsert = 0;
                sql = sql.concat("\n Where cc.CategoryID = ?");
            } else {
                sql = sql.concat(" Or cc.CategoryID = ?");
            }
        }
        
        for (Subject suj : checkedSubject) {
            if (firstInsert == 1) {
                firstInsert = 0;
                sql = sql.concat("\n Where cs.SubjectID = ?");
            } else {
                sql = sql.concat(" Or cs.SubjectID = ?");
            }
        }
        ArrayList<Course> CourseList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            int cnt = 0;
            for (Category cat : checkedCategory) {
                ++cnt;
                ps.setInt(cnt, cat.getCategoryID());
            }
            
            for (Subject suj : checkedSubject) {
                ++cnt;
                ps.setInt(cnt, suj.getSubjectID());
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseID = rs.getInt("CourseID");
                String CourseName = rs.getString("CourseName");
                float Price = rs.getFloat("Price");
                String Description = rs.getString("Description");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                int UserID = rs.getInt("UserID");
                CourseList.add(new Course(CourseID, CourseName, Price, Description, CreateDate, UserID));
            }
        } catch (Exception e) {
            status = "Error at getCourseList " + e.getMessage();
        }
        return CourseList;
    }
    
    public Course getCourseById(int CourseID) {
        String sql = "Select * From [Course] Where CourseID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String CourseName = rs.getString("CourseName");
                float Price = rs.getFloat("Price");
                String Description = rs.getString("Description");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                int UserID = rs.getInt("UserID");
                return new Course(CourseID, CourseName, Price, Description, CreateDate, UserID);
            }
        } catch (SQLException e) {
            status = "Error at getCourseByID " + e.getMessage();
        }
        return null;
    }
    
    public String getCourseCategory(int CourseID) {
        String sql = "Select c.* From [CourseCategory] cc"
                + "\n Join [Category] c On c.CategoryID = cc.CategoryID"
                + "\n Where cc.CourseID = ?";
        String courseCategory = "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String CategoryName = rs.getString("CategoryName");
                courseCategory = courseCategory.concat(CategoryName + ", ");
            }
        } catch (SQLException e) {
            status = "Error at getCourseCategory " + e.getMessage();
        }
        return courseCategory;
    }
    
    public ArrayList<Feedback> getCourseFeedback(int CourseID) {
        String sql = "Select * From [Feedback] Where CourseID = ?";
        ArrayList<Feedback> courseFeedback = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int UserID = rs.getInt("UserID");
                int FeedbackID = rs.getInt("FeedbackID");
                java.sql.Date CreateDate = rs.getDate("CreateDate");
                String Description = rs.getString("Description");
                courseFeedback.add(new Feedback(UserID, CourseID, FeedbackID, CreateDate, Description));
            }
        } catch (SQLException e) {
            status = "Error at getCourseFeedback " + e.getMessage();
        }
        return courseFeedback;
    }
    
    public ArrayList<Course> getRelatedCourse(int CourseID) {
        List<Category> CategoryList = INS.loadCategoryList();
        List<Subject> SubjectList = INS.loadSubjectList();
        
        List<Category> checkedCategory = new ArrayList<>();
        List<Subject> checkedSubject = new ArrayList<>();
        
        String sql = "Select * From [CourseCategory] Where CourseID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                for (Category cat : CategoryList) {
                    if (categoryID == cat.getCategoryID()) {
                        checkedCategory.add(cat);
                    }
                }
            }
        } catch (SQLException e) {
            status = "Error at getRelatedCourse " + e.getMessage();
        }
        
        sql = "Select * From [SubjectCategory] Where CourseID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int subjectID = rs.getInt("SubjectID");
                for (Subject suj : SubjectList) {
                    if (subjectID == suj.getSubjectID()) {
                        checkedSubject.add(suj);
                    }
                }
            }
        } catch (SQLException e) {
            status = "Error at getRelatedCourse " + e.getMessage();
        }
        
        return INS.getCourseList(checkedCategory, checkedSubject);
    }
    
    public List<Category> getCategoryByCourse(int CourseID) {
        String sql = "Select c.* From [Category] c "
                + "\n Join [CourseCategory] cc On c.CategoryID = cc.CategoryID"
                + "\n Where cc.CourseID = ?";
        List<Category> CategoryList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                CategoryList.add(new Category(CategoryID, CategoryName));
            }
        } catch (SQLException e) {
            status = "Error at getCategoryByCourse " + e.getMessage();
        }
        return CategoryList;
    }
    
    public List<Subject> getSubjectByCourse(int CourseID) {
        String sql = "Select s.* From [Subject] s"
                + "\n Join [CourseSubject] cs On s.SubjectID = cs.SubjectID"
                + "\n Where cs.CourseID = ?";
        List<Subject> SubjectList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int SubjectID = rs.getInt("SubjectID");
                String SubjectName = rs.getString("SubjectName");
                SubjectList.add(new Subject(SubjectID, SubjectName));
            }
        } catch (SQLException e) {
            status = "Error at getSubjectByCourse " + e.getMessage();
        }
        return SubjectList;
    }
    
    public float getCourseDiscount(int CourseID) {
        String sql = "Select * From [Discount] Where CourseID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat("Percentage");
            }
        } catch (SQLException e) {
            status = "Error at getCourseDiscount " + e.getMessage();
        }
        return 0;
    }
    
    public int getNoP(int CourseID) {
        String sql = "Select Count(UserID) as NoP From [OwnCourse] Where CourseID = ?";
        int NoP = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NoP = rs.getInt("NoP");
            }
        } catch (SQLException e) {
            status = "Error at getNoP " + e.getMessage();
        }
        return NoP;
    }
    
    public int checkCourseCategory(int CourseID, int CategoryID) {
        String sql = "Select * From [CourseCategory] Where CourseID = ? And CategoryID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, CategoryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return 1;
            }
        } catch (SQLException e) {
            status = "Error at checkCourseCategory " + e.getMessage();
        }
        return 0;
    }
    
    public int checkCourseSubject(int CourseID, int SubjectID) {
        String sql = "Select * From [CourseSubject] Where CourseID = ? And SubjectID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setInt(2, SubjectID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return 1;
            }
        } catch (SQLException e) {
            status = "Error at checkCourseSubject " + e.getMessage();
        }
        return 0;
    }
    
    public void updateCourse(int CourseID, String CourseName, String Description, float Price, float Percentage, List<Category> CategoryList, List<Subject> SubjectList) {
        String sql = "Update [Course]"
                + "\n Set CourseName = ?, Description = ?, Price = ?"
                + "\n Where CourseID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, CourseName);
            ps.setString(2, Description);
            ps.setFloat(3, Price);
            ps.setInt(4, CourseID);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at updateCourse " + e.getMessage();
        }       
        
        sql = "Delete From [CourseCategory] Where CourseID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at updateCourse " + e.getMessage();
        }
        
        sql = "Delete From [CourseSubject] Where CourseID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at updateCourse " + e.getMessage();
        }
        
        sql = "Insert Into [CourseCategory] Values(?, ?)";
        for (Category cat : CategoryList) {            
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, CourseID);
                ps.setInt(2, cat.getCategoryID());
                ps.execute();
            } catch (SQLException e) {
                status = "Error at updateCourse " + e.getMessage();
            }
        }
        
        sql = "Insert Into [CourseSubject] Values(?, ?)";
        for (Subject sub : SubjectList) {            
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, CourseID);
                ps.setInt(2, sub.getSubjectID());
                ps.execute();
            } catch (SQLException e) {
                status = "Error at updateCourse " + e.getMessage();
            }            
        }
    }
    
    public Category getCategoryById(int CategoryID) {
        String sql = "Select * From [Category] Where CategoryID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CategoryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Category(CategoryID, rs.getString("CategoryName"));
            }
        } catch (SQLException e) {
            status = "Error at getCategoryById " + e.getMessage();
        }
        return null;
    }
    
    public Subject getSubjectById(int SubjectID) {
        String sql = "Select * From [Subject] Where SubjectID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, SubjectID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Subject(SubjectID, rs.getString("SubjectName"));
            }
        } catch (SQLException e) {
            status = "Error at getSubjectById " + e.getMessage();
        }
        return null;
    }
    
    public void createNewCourse(String CourseName, String Description, float Price, float Percentage, List<Category> CategoryList, List<Subject> SubjectList, int UserID) {
        INS.loadCourse();
        int CourseID = INS.cl.size() + 1;
        String sql = "Insert Into [Course] Values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, CourseID);
            ps.setString(2, CourseName);
            ps.setFloat(3, Price);
            ps.setString(4, Description);            
            java.util.Date date = new java.util.Date();
            ps.setDate(5, new java.sql.Date(date.getTime()));
            ps.setInt(6, UserID);
            ps.execute();
            
        } catch (SQLException e) {
            status = "Error at createNewCourse " + e.getMessage();
        }
        
        sql = "Insert Into [CourseCategory] Values (?,?)";
        try {
            for (Category cat : CategoryList) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, CourseID);
                ps.setInt(2, cat.getCategoryID());
                ps.execute();
            }            
            
        } catch (SQLException e) {
            status = "Error at createNewCourse " + e.getMessage();
        }
        
        sql = "Insert Into [CourseSubject] Values (?,?)";
        try {
            for (Subject sub : SubjectList) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, CourseID);
                ps.setInt(2, sub.getSubjectID());
                ps.execute();
            }
        } catch (SQLException e) {
            status = "Error at createNewCourse " + e.getMessage();
        }
    }        
    
    public static void main(String[] agrs) {
        System.out.println(INS.getCourseList(new ArrayList<>(), new ArrayList<>()).size());           
        System.out.println(INS.status);
    }
    
}
