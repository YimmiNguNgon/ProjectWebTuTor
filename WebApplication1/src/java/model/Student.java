/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Huy
 */
public class Student {
    private int studentID;      // Khóa chính, tự động tăng giá trị
    private int userID;         // Khóa ngoại liên kết với bảng Users
    private int courseID;       // Khóa ngoại liên kết với bảng Courses
    private Date dateJoined;    // Thời điểm tham gia (mặc định là thời gian hiện tại)
    private Account account;
    private boolean Attended;

    public Student() {
    }

    public int getStudentID() {
        return studentID;
    }

    public int getUserID() {
        return userID;
    }

    public int getCourseID() {
        return courseID;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isAttended() {
        return Attended;
    }

    public void setAttended(boolean Attended) {
        this.Attended = Attended;
    }
    
    @Override
    public String toString() {
        return "Student{" + "studentID=" + studentID + ", userID=" + userID + ", courseID=" + courseID + ", dateJoined=" + dateJoined + '}';
    }
    
    
}
