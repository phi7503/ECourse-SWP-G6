/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hi2ot
 */
public class SEQuestion {
    private int SecutiryQuestionID;
    private String Question;

    public SEQuestion(int SecutiryQuestionID, String Question) {
        this.SecutiryQuestionID = SecutiryQuestionID;
        this.Question = Question;
    }

    public int getSecutiryQuestionID() {
        return SecutiryQuestionID;
    }

    public void setSecutiryQuestionID(int SecutiryQuestionID) {
        this.SecutiryQuestionID = SecutiryQuestionID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    @Override
    public String toString() {
        return "SEQuestion{" + "SecutiryQuestionID=" + SecutiryQuestionID + ", Question=" + Question + '}';
    }
        
}
