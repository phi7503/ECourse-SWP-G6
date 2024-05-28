
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hi2ot
 */
public class Question {
    private int QuizID;
    private int QuestionID;
    private String Question;
    private String Explaination;

    public Question() {
    }

    public Question(int QuizID, int QuestionID, String Question, String Explaination) {
        this.QuizID = QuizID;
        this.QuestionID = QuestionID;
        this.Question = Question;
        this.Explaination = Explaination;
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

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getExplaination() {
        return Explaination;
    }

    public void setExplaination(String Explaination) {
        this.Explaination = Explaination;
    }
    
}
