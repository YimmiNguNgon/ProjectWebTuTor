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
public class CourseSession {
    private int sessionId;
    private int studentId;
    private int courseId;
    private int sessionNumber;
    private boolean attended;
    private Date sessionDate;

    public CourseSession() {
    }

    public CourseSession(int sessionId, int studentId, int courseId, int sessionNumber, boolean attended, Date sessionDate) {
        this.sessionId = sessionId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.sessionNumber = sessionNumber;
        this.attended = attended;
        this.sessionDate = sessionDate;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    @Override
    public String toString() {
        return "CourseSession{" +
                "sessionId=" + sessionId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", sessionNumber=" + sessionNumber +
                ", attended=" + attended +
                ", sessionDate=" + sessionDate +
                '}';
    }
}
