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
public class Feedback {
    private int UserID;
    private int CourseID;
    private int FeedbackID;
    private java.sql.Date CreateDate;
    private String Description;

    public Feedback() {
    }

    public Feedback(int UserID, int CourseID, int FeedbackID, Date CreateDate, String Description) {
        this.UserID = UserID;
        this.CourseID = CourseID;
        this.FeedbackID = FeedbackID;
        this.CreateDate = CreateDate;
        this.Description = Description;
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

    public int getFeedbackID() {
        return FeedbackID;
    }

    public void setFeedbackID(int FeedbackID) {
        this.FeedbackID = FeedbackID;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

   
    
    
}
