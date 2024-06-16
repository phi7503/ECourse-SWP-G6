/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hi2ot
 */
public class LessonDoc {
    
    private int CourseID;
    private int LessonID;
    private int DocID;
    private String Title;
    private String Description;
    private String Link;

    public LessonDoc() {
    }

    public LessonDoc(int CourseID, int LessonID, int DocID, String Title, String Description, String Link) {
        this.CourseID = CourseID;
        this.LessonID = LessonID;
        this.DocID = DocID;
        this.Title = Title;
        this.Description = Description;
        this.Link = Link;
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

    public int getDocID() {
        return DocID;
    }

    public void setDocID(int DocID) {
        this.DocID = DocID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }
    
    
}