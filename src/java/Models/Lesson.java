
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Lesson {
<<<<<<< HEAD
    
    private int CourseID;
    private int LessonID;
    private String LessonName;
    private String Description;
=======

    private int id;
    private String name;
    private double price;
    private double percentage;
    private String description;
    private String tagline;
    private String image;
    private String title;
    private Date createdDate;
>>>>>>> 55609f03a79a05168caa2a99f12537d0da970c95

    public Lesson() {
    }

    public Lesson(int CourseID, int LessonID, String LessonName, String Description) {
        this.CourseID = CourseID;
        this.LessonID = LessonID;
        this.LessonName = LessonName;
        this.Description = Description;
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

    public String getLessonName() {
        return LessonName;
    }

    public void setLessonName(String LessonName) {
        this.LessonName = LessonName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
