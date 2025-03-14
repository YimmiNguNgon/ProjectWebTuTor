/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author Huy
 */
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class markAttendanceDAO extends DBContext{
    public boolean markAttendance(int studentId, int courseId, int sessionNumber, String status) {
        boolean isSuccessful = false;

        // Bước 1: Kiểm tra xem học viên đã điểm danh cho buổi học này chưa
        String checkAttendanceSQL = "SELECT COUNT(*) FROM Attendance WHERE StudentID = ? AND CourseID = ? AND SessionNumber = ?";
        
        try (PreparedStatement checkStmt = connection.prepareStatement(checkAttendanceSQL)) {
            checkStmt.setInt(1, studentId);
            checkStmt.setInt(2, courseId);
            checkStmt.setInt(3, sessionNumber);
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                int attendanceCount = rs.getInt(1);

                // Bước 2: Nếu chưa điểm danh cho buổi học này, chèn mới hoặc cập nhật điểm danh
                String insertOrUpdateSQL;
                if (attendanceCount == 0) {
                    // Thêm mới điểm danh nếu chưa có
                    insertOrUpdateSQL = "INSERT INTO Attendance (StudentID, CourseID, SessionNumber, Status, SessionDate) "
                                       + "VALUES (?, ?, ?, ?, GETDATE())";
                } else {
                    // Cập nhật nếu học viên đã điểm danh trước đó
                    insertOrUpdateSQL = "UPDATE Attendance SET Status = ?, SessionDate = GETDATE() "
                                       + "WHERE StudentID = ? AND CourseID = ? AND SessionNumber = ?";
                }

                // Cập nhật điểm danh vào bảng Attendance
                try (PreparedStatement stmt = connection.prepareStatement(insertOrUpdateSQL)) {
                    stmt.setInt(1, studentId);
                    stmt.setInt(2, courseId);
                    stmt.setInt(3, sessionNumber);
                    stmt.setString(4, status); // Trạng thái điểm danh (Present/Absent)

                    int rowsAffected = stmt.executeUpdate();

                    // Bước 3: Nếu đã cập nhật điểm danh thành công, cập nhật số buổi học đã hoàn thành
                    if (rowsAffected > 0) {
                        String updateProgressSQL = "UPDATE StudentCourses SET completedSessions = completedSessions + 1 "
                                                  + "WHERE StudentID = ? AND CourseID = ?";
                        
                        try (PreparedStatement stmtProgress = connection.prepareStatement(updateProgressSQL)) {
                            stmtProgress.setInt(1, studentId);
                            stmtProgress.setInt(2, courseId);
                            stmtProgress.executeUpdate();
                        }

                        isSuccessful = true;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccessful;
    }
}
