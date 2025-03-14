/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.*;

/**
 *
 * @author Huy
 */
public class ScheduleDAO extends DBContext {

    public boolean isScheduleConflict(int tutorId, String dayOfWeek, Timestamp startTime, Timestamp endTime) {
        String sql = "SELECT COUNT(*) FROM Schedules "
                + "WHERE tutorId = ? AND dayOfWeek = ? "
                + "AND (CAST(startTime AS TIME) <= CAST(? AS TIME) "
                + "AND CAST(endTime AS TIME) >= CAST(? AS TIME))";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, tutorId);
            stmt.setString(2, dayOfWeek);
            stmt.setTimestamp(3, startTime);
            stmt.setTimestamp(4, endTime);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createSchedule(int tutorId, int studentId, String dayOfWeek, Timestamp startTime, Timestamp endTime) {

        String sql = "INSERT INTO Schedules (tutorId, studentId, dayOfWeek, startTime, endTime) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tutorId);
            stmt.setInt(2, studentId);
            stmt.setString(3, dayOfWeek);
            stmt.setTimestamp(4, startTime);
            stmt.setTimestamp(5, endTime);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
