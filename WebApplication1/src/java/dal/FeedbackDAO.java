package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Feedback;

public class FeedbackDAO extends DBContext {  // Kế thừa DBContext

    public void insertFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedback (StudentID, TutorID, CourseID, Rating, Comments) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, feedback.getStudentId());
            ps.setInt(2, feedback.getTutorId());
            ps.setInt(3, feedback.getCourseId());
            ps.setDouble(4, feedback.getRating());
            ps.setString(5, feedback.getComments());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Lỗi khi thêm feedback: " + e.getMessage());
        }
    }
}
