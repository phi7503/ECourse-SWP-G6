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
public class Course {
    private int CourseID;
    private String CourseName;
    private float Price;
    private String Description;
    private java.sql.Date CreateDate;
    private int UserID;

    public Course() {
    }

    public Course(int CourseID, String CourseName, float Price, String Description, Date CreateDate, int UserID) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.Price = Price;
        this.Description = Description;
        this.CreateDate = CreateDate;
        this.UserID = UserID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date CreateDate) {
        this.CreateDate = CreateDate;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
    
    
    
}
