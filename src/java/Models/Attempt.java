
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author hi2ot
 */
public class Attempt {
    private int UserID;
    private int CourseID;
    private int LessonID;
    private int QuizID;
    private int AttemptID;
    private java.sql.Date AttemptDate;
    private java.sql.Date SubmittedDate;

    public Attempt() {
    }

    public Attempt(int UserID, int CourseID, int LessonID, int QuizID, int AttemptID, Date AttemptDate, Date SubmittedDate) {
        this.UserID = UserID;
        this.CourseID = CourseID;
        this.LessonID = LessonID;
        this.QuizID = QuizID;
        this.AttemptID = AttemptID;
        this.AttemptDate = AttemptDate;
        this.SubmittedDate = SubmittedDate;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getLessonID() {
        return LessonID;
    }

    public void setLessonID(int LessonID) {
        this.LessonID = LessonID;
    }

    public int getQuizID() {
        return QuizID;
    }

    public void setQuizID(int QuizID) {
        this.QuizID = QuizID;
    }

    public int getAttemptID() {
        return AttemptID;
    }

    public void setAttemptID(int AttemptID) {
        this.AttemptID = AttemptID;
    }

    public Date getAttemptDate() {
        return AttemptDate;
    }

    public void setAttemptDate(Date AttemptDate) {
        this.AttemptDate = AttemptDate;
    }

    public Date getSubmittedDate() {
        return SubmittedDate;
    }

    public void setSubmittedDate(Date SubmittedDate) {
        this.SubmittedDate = SubmittedDate;
    }
    
    
}
