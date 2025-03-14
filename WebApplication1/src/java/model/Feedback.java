/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;
import java.lang.*;

/**
 *
 * @author ICBAdmin
 */
public class Feedback {

    private int feedbackId;
    private int studentId;
    private int tutorId;
    private int courseId;
    private double rating;
    private String comments;

    public Feedback() {
    }

    public Feedback(int studentId, int tutorId, int courseId, double rating, String comments) {
        this.studentId = studentId;
        this.tutorId = tutorId;
        this.courseId = courseId;
        this.rating = rating;
        this.comments = comments;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
}
