<<<<<<< HEAD

=======
>>>>>>> 55609f03a79a05168caa2a99f12537d0da970c95
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

<<<<<<< HEAD
import java.sql.Date;

/**
 *
 * @author hi2ot
 */
public class Order {
    private int UserID;
    private int OrderID;
    private java.sql.Date CreateDate;
    private float Price;
=======
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Order {

    private int id, UserId, Status;

    private List<Lesson> lessons = new ArrayList<>();
>>>>>>> 55609f03a79a05168caa2a99f12537d0da970c95

    public Order() {
    }

<<<<<<< HEAD
    public Order(int UserID, int OrderID, Date CreateDate, float Price) {
        this.UserID = UserID;
        this.OrderID = OrderID;
        this.CreateDate = CreateDate;
        this.Price = Price;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date CreateDate) {
        this.CreateDate = CreateDate;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }
    
    
=======
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public String getTotal() {

        double total = 0;
        for (Lesson lesson : this.lessons) {
            total += lesson.getPrice();
        }
        return total + "";
    }
>>>>>>> 55609f03a79a05168caa2a99f12537d0da970c95
}
