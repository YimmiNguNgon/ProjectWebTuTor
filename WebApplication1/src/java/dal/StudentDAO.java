/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dal;

import context.DBContext;
import java.util.*;
import java.lang.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ICBAdmin
 */
public class StudentDAO extends DBContext {

    public int getStudentIdByUserId(int userId) {
        String sql = "SELECT StudentID FROM Students WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("StudentID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }
}