package Models;

import java.util.Date;

public class User {

    private String UserName, Password, Mail, FullName,Question, Answer;
    private int UserID, Role, Status;
    private Date DoB;

    public User() {
    }

    public User(String UserName, String Password, String Mail, String FullName, String Question, String Answer, int UserID, int Role, int Status, Date DoB) {
        this.UserName = UserName;
        this.Password = Password;
        this.Mail = Mail;
        this.FullName = FullName;
        this.Question = Question;
        this.Answer = Answer;
        this.UserID = UserID;
        this.Role = Role;
        this.Status = Status;
        this.DoB = DoB;
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

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
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

    public Date getDoB() {
        return DoB;
    }

    public void setDoB(Date DoB) {
        this.DoB = DoB;
    }
    
    
}
