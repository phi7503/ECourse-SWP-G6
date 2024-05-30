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
    private int QuizID;
    private int LessonID;
    private String QuizName;
    private java.sql.Date CreateDate;

    public Quiz() {
    }

    public Quiz(int QuizID, int LessonID, String QuizName, Date CreateDate) {
        this.QuizID = QuizID;
        this.LessonID = LessonID;
        this.QuizName = QuizName;
        this.CreateDate = CreateDate;
    }

    public int getQuizID() {
        return QuizID;
    }

    public void setQuizID(int QuizID) {
        this.QuizID = QuizID;
    }

    public int getLessonID() {
        return LessonID;
    }

    public void setLessonID(int LessonID) {
        this.LessonID = LessonID;
    }

    public String getQuizName() {
        return QuizName;
    }

    public void setQuizName(String QuizName) {
        this.QuizName = QuizName;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date CreateDate) {
        this.CreateDate = CreateDate;
    }
    
    
}
