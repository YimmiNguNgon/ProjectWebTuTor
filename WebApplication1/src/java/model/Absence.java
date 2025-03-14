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
public class Absence {
    private int absenceId;
    private int studentId;
    private int sessionId;
    private String reason;
    private String status;  // "Pending", "Approved", "Rejected"
    private Date requestDate;

    public Absence() {
    }

    public Absence(int absenceId, int studentId, int sessionId, String reason, String status, Date requestDate) {
        this.absenceId = absenceId;
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.reason = reason;
        this.status = status;
        this.requestDate = requestDate;
    }

    public int getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(int absenceId) {
        this.absenceId = absenceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "Absence{" +
                "absenceId=" + absenceId +
                ", studentId=" + studentId +
                ", sessionId=" + sessionId +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", requestDate=" + requestDate +
                '}';
    }
}
