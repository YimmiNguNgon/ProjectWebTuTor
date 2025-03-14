/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class StudentCourseDAO extends DBContext {

    public List<Student> getStudentsByCourseId(int courseId) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT S.*, U.UserId "
                + "FROM StudentCourses sc "
                + "JOIN Students as s on S.StudentId = sc.StudentId "
                + "JOIN Users u ON s.UserID = u.UserId "
                + "WHERE sc.CourseID = ?";

        try (
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            UserDAO userDao = new UserDAO();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getInt("StudentID"));
                student.setAccount(userDao.getAccountByUId(rs.getInt("UserID") + ""));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}
