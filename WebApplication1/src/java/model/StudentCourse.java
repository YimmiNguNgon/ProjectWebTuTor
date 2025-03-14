/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Huy
 */
public class StudentCourse {
    private int studentID;           // ID học viên
    private int courseID;            // ID khóa học
    private int completedSessions;   // Số buổi học đã hoàn thành
    private String status;           // Trạng thái (Đang diễn ra / Hoàn thành)

    // Constructor
    public StudentCourse(int studentID, int courseID, int completedSessions, String status) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.completedSessions = completedSessions;
        this.status = status;
    }

    // Getters and Setters
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCompletedSessions() {
        return completedSessions;
    }

    public void setCompletedSessions(int completedSessions) {
        this.completedSessions = completedSessions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


