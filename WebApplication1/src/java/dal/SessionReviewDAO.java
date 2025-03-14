/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import model.SessionReview;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionReviewDAO extends DBContext {

    public SessionReview getReviewByStudentAndSession(int studentId, int sessionId) {
        SessionReview review = null;
        String query = "SELECT * FROM SessionReviews WHERE StudentID = ? AND SessionID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, sessionId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    review = new SessionReview(
                            rs.getInt("ReviewID"),
                            rs.getInt("StudentID"),
                            rs.getInt("SessionID"),
                            rs.getInt("Rating"),
                            rs.getString("Comment"),
                            rs.getString("ReviewStatus"),
                            rs.getDate("ReviewDate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    public void updateReview(SessionReview review) {
        String query = "UPDATE SessionReviews SET Rating = ?, Comment = ?, ReviewStatus = ?, ReviewDate = ? WHERE ReviewID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, review.getRating());
            stmt.setString(2, review.getComment());
            stmt.setString(3, review.getReviewStatus());
            stmt.setDate(4, new java.sql.Date(review.getReviewDate().getTime()));
            stmt.setInt(5, review.getReviewId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addReview(SessionReview review) {
        String query = "INSERT INTO SessionReviews (StudentID, SessionID, Rating, Comment, ReviewStatus, ReviewDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, review.getStudentId());
            stmt.setInt(2, review.getSessionId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComment());
            stmt.setString(5, review.getReviewStatus());
            stmt.setDate(6, new java.sql.Date(review.getReviewDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
