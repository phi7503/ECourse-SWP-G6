
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

<<<<<<< HEAD
import java.sql.Date;
=======
//import java.sql.Date;
import java.util.Date;
>>>>>>> 01c855e1d6039d1665043ed687ad7fa980f157a6

/**
 *
 * @author hi2ot
 */
public class User {
    private int UserID;
    private String UserName;
    private String Password;
    private String Mail;
    private String FullName;
    private Date DoB;
    private int SecurityQuestionID;
    private String Answer;
    private int Role;
    private int Status;

<<<<<<< HEAD
=======
    public User() {
    }

>>>>>>> 01c855e1d6039d1665043ed687ad7fa980f157a6
    public User(int UserID, String UserName, String Password, String Mail, String FullName, Date DoB, int SecurityQuestionID, String Answer, int Role) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.Password = Password;
        this.Mail = Mail;
        this.FullName = FullName;
        this.DoB = DoB;
        this.SecurityQuestionID = SecurityQuestionID;
        this.Answer = Answer;
        this.Role = Role;
<<<<<<< HEAD
        this.Status = 0;
=======
    }

    public User(int UserID, String UserName, String Password, String Mail, String FullName, Date DoB, int SecurityQuestionID, String Answer, int Role, int Status) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.Password = Password;
        this.Mail = Mail;
        this.FullName = FullName;
        this.DoB = DoB;
        this.SecurityQuestionID = SecurityQuestionID;
        this.Answer = Answer;
        this.Role = Role;
        this.Status = Status;
>>>>>>> 01c855e1d6039d1665043ed687ad7fa980f157a6
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public Date getDoB() {
        return DoB;
    }

    public void setDoB(Date DoB) {
        this.DoB = DoB;
    }

    public int getSecurityQuestionID() {
        return SecurityQuestionID;
    }

    public void setSecurityQuestionID(int SecurityQuestionID) {
        this.SecurityQuestionID = SecurityQuestionID;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int Role) {
        this.Role = Role;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
<<<<<<< HEAD

    @Override
    public String toString() {
        return "User{" + "UserID=" + UserID + ", UserName=" + UserName + ", Password=" + Password + ", Mail=" + Mail + ", FullName=" + FullName + ", DoB=" + DoB + ", SecurityQuestionID=" + SecurityQuestionID + ", Answer=" + Answer + ", Role=" + Role + ", Status=" + Status + '}';
    }        
=======
    
    
>>>>>>> 01c855e1d6039d1665043ed687ad7fa980f157a6
    
}