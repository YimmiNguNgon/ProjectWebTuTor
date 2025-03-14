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
public class SessionReview {
    private int reviewId;
    private int studentId;
    private int sessionId;
    private int rating;  // 1 đến 5 sao
    private String comment;
    private String reviewStatus;  // "25%", "50%", "75%", "100%"
    private Date reviewDate;

    public SessionReview() {
    }

    public SessionReview(int reviewId, int studentId, int sessionId, int rating, String comment, String reviewStatus, Date reviewDate) {
        this.reviewId = reviewId;
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.rating = rating;
        this.comment = comment;
        this.reviewStatus = reviewStatus;
        this.reviewDate = reviewDate;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        return "SessionReview{" +
                "reviewId=" + reviewId +
                ", studentId=" + studentId +
                ", sessionId=" + sessionId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", reviewStatus='" + reviewStatus + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }
}
