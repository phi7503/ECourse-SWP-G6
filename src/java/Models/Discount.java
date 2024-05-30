/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hi2ot
 */
public class Discount {
    private int LessonID;
    private float Percentage;

    public Discount() {
    }

    public Discount(int LessonID, float Percentage) {
        this.LessonID = LessonID;
        this.Percentage = Percentage;
    }

    public int getLessonID() {
        return LessonID;
    }

    public void setLessonID(int LessonID) {
        this.LessonID = LessonID;
    }

    public float getPercentage() {
        return Percentage;
    }

    public void setPercentage(float Percentage) {
        this.Percentage = Percentage;
    }

    
    
}
