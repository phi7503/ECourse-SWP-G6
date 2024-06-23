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
public class Quiz {
    private int CourseID;
    private int LessonID;
    private int QuizID;
    private String QuizName;
    private int NoQ;
    private java.sql.Date CreateDate;

    public Quiz() {
    }

    public Quiz(int CourseID, int LessonID, int QuizID, String QuizName, int NoQ, Date CreateDate) {
        this.CourseID = CourseID;
        this.LessonID = LessonID;
        this.QuizID = QuizID;
        this.QuizName = QuizName;
        this.NoQ = NoQ;
        this.CreateDate = CreateDate;
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

    public String getQuizName() {
        return QuizName;
    }

    public void setQuizName(String QuizName) {
        this.QuizName = QuizName;
    }

    public int getNoQ() {
        return NoQ;
    }

    public void setNoQ(int NoQ) {
        this.NoQ = NoQ;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date CreateDate) {
        this.CreateDate = CreateDate;
    }
    
    
}
