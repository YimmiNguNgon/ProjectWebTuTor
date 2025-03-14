/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;
import java.lang.*;

/**
 *
 * @author Huy
 */
public class Course {

    private int courseId;         // ID khóa học
    private String courseName;    // Tên khóa học
    private String description;   // Mô tả khóa học
    private String level;         // Cấp độ (Beginner, Intermediate, Advanced)
    private double price;         // Giá khóa học
    private float rating;         // Đánh giá trung bình
    private int totalSessions;    // Số buổi học
    private String courseStatus;  // Trạng thái khóa học (Active, Inactive, Pending)
    private int studentId;
    

    // Constructor không tham số
    public Course() {
    }

    // Constructor đầy đủ thông tin (Đã bỏ category)
    public Course(int courseId, String courseName, String description, String level, double price, float rating, 
                  int totalSessions, String courseStatus) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.level = level;
        this.price = price;
        this.rating = rating;
        this.totalSessions = totalSessions;
        this.courseStatus = courseStatus;
    }

    // Constructor tối giản (ID và tên)
    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    // Getter và Setter cho tất cả các thuộc tính
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotalSessions() {
        return totalSessions;
    }

    public void setTotalSessions(int totalSessions) {
        this.totalSessions = totalSessions;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", level='" + level + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", totalSessions=" + totalSessions +
                ", courseStatus='" + courseStatus + '\'' +
                '}';
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    
}
