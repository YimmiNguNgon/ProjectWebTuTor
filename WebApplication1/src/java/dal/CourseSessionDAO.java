/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Attendance;
import model.CourseSession;

/**
 *
 * @author HP
 */
public class CourseSessionDAO extends DBContext {

    public void createSession(int courseId, int studentId, int sessionNumber, Date sessionDate, boolean attended) {
        String query = "INSERT INTO CourseSessions (CourseID, StudentID, SessionNumber, SessionDate, Attended) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            stmt.setInt(2, studentId);
            stmt.setInt(3, sessionNumber);
            stmt.setDate(4, new java.sql.Date(sessionDate.getTime()));
            stmt.setBoolean(5, attended);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSession(int courseId, int studentId, int sessionNumber, Date sessionDate, boolean attended) {
        String query = "UPDATE CourseSessions SET Attended = ? WHERE CourseID = ? AND StudentID = ? AND SessionNumber = ? AND SessionDate = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, attended);
            stmt.setInt(2, courseId);
            stmt.setInt(3, studentId);
            stmt.setInt(4, sessionNumber);
            stmt.setDate(5, new java.sql.Date(sessionDate.getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CourseSession> getAttendedSessionsByStudent(int studentId) {
        List<CourseSession> sessions = new ArrayList<>();
        String query = "SELECT cs.SessionID, cs.CourseID, cs.StudentID, cs.SessionNumber, cs.SessionDate, cs.Attended "
                + "FROM CourseSessions cs "
                + "WHERE cs.StudentID = ? AND cs.Attended = ?";  

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);  
            stmt.setBoolean(2, true);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CourseSession session = new CourseSession();
                    session.setSessionId(rs.getInt("SessionID"));
                    session.setCourseId(rs.getInt("CourseID"));
                    session.setStudentId(rs.getInt("StudentID"));
                    session.setSessionNumber(rs.getInt("SessionNumber"));
                    session.setSessionDate(rs.getDate("SessionDate"));
                    session.setAttended(rs.getBoolean("Attended"));
                    sessions.add(session);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    public List<Attendance> getAttendanceRecords(int courseId, int sessionNumber) {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT StudentID, Attended FROM CourseSessions WHERE CourseID = ? AND SessionNumber = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            stmt.setInt(2, sessionNumber);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int studentID = rs.getInt("StudentID");
                boolean attended = rs.getBoolean("Attended");

                attendanceList.add(new Attendance(studentID, attended));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceList;
    }

    public Attendance getSessionByStudentAndCourse(int courseId, int studentId, int sessionNumber) {
        String query = "SELECT * FROM CourseSessions WHERE CourseID = ? AND StudentID = ? AND SessionNumber = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            stmt.setInt(2, studentId);
            stmt.setInt(3, sessionNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                boolean attended = rs.getBoolean("Attended");
                return new Attendance(studentId, attended);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Attendance> getAttendanceRecords(int courseId) {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT StudentID, SessionNumber, Attended FROM CourseSessions WHERE CourseID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int studentID = rs.getInt("StudentID");
                int sessionNumber = rs.getInt("SessionNumber");
                boolean attended = rs.getBoolean("Attended");

                attendanceList.add(new Attendance(studentID, sessionNumber, attended));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceList;
    }

    public void markAttendance(int sessionId, int studentId, boolean attended) {
        String query = "UPDATE CourseSessions SET Attended = ? WHERE SessionID = ? AND StudentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, attended);
            stmt.setInt(2, sessionId);
            stmt.setInt(3, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
