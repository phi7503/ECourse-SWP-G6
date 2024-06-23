/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hi2ot
 */
public class Answer {
    private int CourseID;
    private int LessonID;
    private int QuizID;
    private int QuestionID;
    private int AnswerID;
    private String Description;
    private int Role;

    public Answer() {
    }

    public Answer(int CourseID, int LessonID, int QuizID, int QuestionID, int AnswerID, String Description, int Role) {
        this.CourseID = CourseID;
        this.LessonID = LessonID;
        this.QuizID = QuizID;
        this.QuestionID = QuestionID;
        this.AnswerID = AnswerID;
        this.Description = Description;
        this.Role = Role;
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

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int QuestionID) {
        this.QuestionID = QuestionID;
    }

    public int getAnswerID() {
        return AnswerID;
    }

    public void setAnswerID(int AnswerID) {
        this.AnswerID = AnswerID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int Role) {
        this.Role = Role;
    }
    
    
}
