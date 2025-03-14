/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRequest {

    private int requestId;
    private int studentId;
    private int tutorId;
    private int courseId;
    private Timestamp requestDate;
    private String status;
    private User user;
    private Schedule schedule;
    private Course course;

    public CourseRequest() {
    }

    public CourseRequest(int requestId, int studentId, int tutorId, int courseId, Timestamp requestDate, String status, User user, Schedule schedule, Course course) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.tutorId = tutorId;
        this.courseId = courseId;
        this.requestDate = requestDate;
        this.status = status;
        this.user = user;
        this.schedule = schedule;
        this.course = course;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
