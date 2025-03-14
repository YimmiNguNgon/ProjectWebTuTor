/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;

/**
 *
 * @author Huy
 */
public class Schedule {
    private int scheduleID;
    private int tutorID;
    private int studentID;
    private String dayOfWeek;
    private Time startTime;
    private Time endTime;
    private String courseName;
    private String hocSinh;

    // Constructor đầy đủ
    public Schedule(int scheduleID, int tutorID, int studentID, String dayOfWeek, 
                    Time startTime, Time endTime, String courseName, String hocSinh) {
        this.scheduleID = scheduleID;
        this.tutorID = tutorID;
        this.studentID = studentID;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseName = courseName;
        this.hocSinh = hocSinh;
    }

    public Schedule() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters và Setters
    public int getScheduleID() { return scheduleID; }
    public void setScheduleID(int scheduleID) { this.scheduleID = scheduleID; }

    public int getTutorID() { return tutorID; }
    public void setTutorID(int tutorID) { this.tutorID = tutorID; }

    public int getStudentID() { return studentID; }
    public void setStudentID(int studentID) { this.studentID = studentID; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public Time getStartTime() { return startTime; }
    public void setStartTime(Time startTime) { this.startTime = startTime; }

    public Time getEndTime() { return endTime; }
    public void setEndTime(Time endTime) { this.endTime = endTime; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getHocSinh() { return hocSinh; }
    public void setHocSinh(String hocSinh) { this.hocSinh = hocSinh; }
}