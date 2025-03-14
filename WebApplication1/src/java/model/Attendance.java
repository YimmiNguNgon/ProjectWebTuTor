/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Attendance {
    private int studentID;
    private int sessionNumber;
    private boolean attended;

    public Attendance(int studentID, int sessionNumber, boolean attended) {
        this.studentID = studentID;
        this.sessionNumber = sessionNumber;
        this.attended = attended;
    }

    public Attendance() {
    }
    
    

    public Attendance(int studentID, boolean attended) {
        this.studentID = studentID;
        this.attended = attended;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }
}
