/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller_student;

import dal.SessionReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import model.SessionReview;

/**
 *
 * @author HP
 */
@WebServlet(name = "SessionReviewController", urlPatterns = {"/SessionReviewController"})
public class SessionReviewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int sessionId = Integer.parseInt(request.getParameter("sessionId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        SessionReviewDAO reviewDAO = new SessionReviewDAO();
        SessionReview review = reviewDAO.getReviewByStudentAndSession(studentId, sessionId);

        if (review != null) {
            request.setAttribute("review", review);
        } else {
            review = new SessionReview();
            review.setSessionId(sessionId);
            review.setStudentId(studentId);
            request.setAttribute("review", review);
        }

        request.getRequestDispatcher("reviewSession.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int sessionId = Integer.parseInt(request.getParameter("sessionId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");
        String reviewStatus = request.getParameter("reviewStatus");

        SessionReviewDAO reviewDAO = new SessionReviewDAO();
        SessionReview review = reviewDAO.getReviewByStudentAndSession(studentId, sessionId);

        if (review != null) {
            review.setRating(rating);
            review.setComment(comment);
            review.setReviewStatus(reviewStatus);
            review.setReviewDate(new Date(System.currentTimeMillis()));
            reviewDAO.updateReview(review);
        } else {
            review = new SessionReview();
            review.setSessionId(sessionId);
            review.setStudentId(studentId);
            review.setRating(rating);
            review.setComment(comment);
            review.setReviewStatus(reviewStatus);
            review.setReviewDate(new Date(System.currentTimeMillis()));
            reviewDAO.addReview(review);
        }

        response.sendRedirect("reviewConfirmation.jsp");
    }
}
