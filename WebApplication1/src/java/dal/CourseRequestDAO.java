/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.CourseRequest;
import model.Schedule;
import model.User;

public class CourseRequestDAO extends DBContext {

    private Connection conn;

    public CourseRequestDAO() {
        this.conn = connection;
    }

    public boolean isJoin(int userId, int courseId) {
        String checkSql = "SELECT COUNT(*) FROM Students WHERE UserID = ? AND CourseID = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, userId);
            checkStmt.setInt(2, courseId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int addStudent(int userId, int courseId) {
        String sql = "INSERT INTO Students (UserID, CourseID, DateJoined) VALUES (?, ?, GETDATE())";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, courseId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean addCourseRequest(int userId, int tutorId, int courseId) {
        int studentId = addStudent(userId, courseId);
        if (studentId == -1) {
            return false;
        }
        String sql = "INSERT INTO CourseRequests (StudentID, TutorID, CourseID, RequestDate, Status) VALUES (?, ?, ?, GETDATE(), 'Pending')";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, tutorId);
            stmt.setInt(3, courseId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<CourseRequest> getRequestsByTutor(int tutorId) {
        List<CourseRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM CourseRequests WHERE TutorID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tutorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = this.getUserByStudentId(rs.getInt("StudentID"));
                Course course = this.getCourseById(rs.getInt("CourseID"));
                list.add(new CourseRequest(
                        rs.getInt("RequestID"),
                        rs.getInt("StudentID"),
                        rs.getInt("TutorID"),
                        rs.getInt("CourseID"),
                        rs.getTimestamp("RequestDate"),
                        rs.getString("Status"),
                        user,
                        this.getSchedulesByStudentAndTutor(rs.getInt("StudentID"), tutorId),course));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Course getCourseById(int courseId) {
        Course course = null;
        String sql = "SELECT * FROM Courses WHERE CourseID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                course = new Course(
                        rs.getInt("CourseID"),
                        rs.getString("CourseName"),
                        rs.getString("Description"),
                        rs.getString("Level"),
                        rs.getDouble("Price"),
                        rs.getFloat("Rating"),
                        rs.getInt("TotalSessions"), // ✅ Thêm totalSessions
                        rs.getString("CourseStatus") 
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    public boolean updateRequestStatus(int requestId, String newStatus) {
        if (!newStatus.equals("Pending") && !newStatus.equals("Accepted") && !newStatus.equals("Rejected")) {
            throw new IllegalArgumentException("Invalid status value");
        }
        String sql = "UPDATE CourseRequests SET Status = ? WHERE RequestID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, requestId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByStudentId(int studentId) {
        String sql = "SELECT u.* FROM Users u "
                + "JOIN Students s ON u.UserID = s.UserID "
                + "WHERE s.StudentID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getInt("role_id"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Phone"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getString("image_url") // Lấy image_url nếu có
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Schedule getSchedulesByStudentAndTutor(int studentId, int tutorId) {
        String sql = "SELECT * FROM Schedules WHERE studentId = ? AND tutorId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, tutorId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleID(rs.getInt("scheduleId"));
                schedule.setStudentID(rs.getInt("studentId"));
                schedule.setTutorID(rs.getInt("tutorId"));
                schedule.setDayOfWeek(rs.getString("dayOfWeek"));
                schedule.setStartTime(rs.getTime("startTime"));
                schedule.setEndTime(rs.getTime("endTime"));

                return schedule;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
