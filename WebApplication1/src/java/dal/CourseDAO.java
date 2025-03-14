package dal;

import context.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Courses;
import model.Student;

public class CourseDAO extends DBContext {

    public List<Course> getCoursesByUserId(int userId) {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT c.CourseID, c.CourseName "
                + "FROM Courses c "
                + "JOIN Students s ON c.CourseID = s.CourseID "
                + "JOIN Users u ON s.UserID = u.UserID "
                + "WHERE u.UserID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            System.out.println("Lấy danh sách khóa học cho UserID: " + userId);
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("CourseID"),
                        rs.getString("CourseName")
                );
                courseList.add(course);
//                System.out.println("Khóa học lấy được: " + course.getCourseName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public boolean deleteCourse(int courseID) {
        String deleteTutorCoursesSql = "DELETE FROM TutorCourses WHERE CourseID = ?";
        String deleteCourseSql = "DELETE FROM Courses WHERE CourseID = ?";

        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;

        try {
            // Xóa các bản ghi trong bảng TutorCourses liên quan đến CourseID
            stmt1 = connection.prepareStatement(deleteTutorCoursesSql); // Sử dụng connection đã có sẵn
            stmt1.setInt(1, courseID);
            int rowsAffected1 = stmt1.executeUpdate(); // Thực thi câu lệnh xóa trong TutorCourses

            // Xóa khóa học chính trong bảng Courses
            stmt2 = connection.prepareStatement(deleteCourseSql); // Sử dụng connection đã có sẵn
            stmt2.setInt(1, courseID);
            int rowsAffected2 = stmt2.executeUpdate(); // Thực thi câu lệnh xóa trong Courses

            // Kiểm tra số dòng bị ảnh hưởng
            if (rowsAffected1 > 0 && rowsAffected2 > 0) {
                return true; // Xóa thành công
            } else {
                return false; // Không có dòng nào bị xóa
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Nếu có lỗi xảy ra
        } finally {
            // Đảm bảo đóng các tài nguyên (PreparedStatement)
            try {
                if (stmt1 != null) {
                    stmt1.close();
                }
                if (stmt2 != null) {
                    stmt2.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<Courses> getAllCourses() {
        List<Courses> coursesList = new ArrayList<>();
        String sql = "SELECT CourseID, CourseName, Description, Level, Price, Rating, TotalSessions, CourseStatus FROM Courses"; // ✅ Truy vấn đầy đủ cột

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                coursesList.add(new Courses(
                        rs.getInt("CourseID"),
                        rs.getString("CourseName"),
                        rs.getString("Description"),
                        rs.getString("Level"),
                        rs.getDouble("Price"),
                        rs.getFloat("Rating"),
                        rs.getInt("TotalSessions"), // ✅ Thêm số buổi học
                        rs.getString("CourseStatus") // ✅ Thêm trạng thái khóa học
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coursesList;
    }

    public Courses getCourseById(int courseId) {
        Courses course = null;
        String sql = "SELECT CourseID, CourseName, Description, Level, Price, Rating, TotalSessions, CourseStatus FROM Courses WHERE CourseID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);  // Gán CourseID vào truy vấn
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    course = new Courses(
                            rs.getInt("CourseID"),
                            rs.getString("CourseName"),
                            rs.getString("Description"),
                            rs.getString("Level"),
                            rs.getDouble("Price"),
                            rs.getFloat("Rating"),
                            rs.getInt("TotalSessions"),
                            rs.getString("CourseStatus")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }

    public List<Courses> getCoursesByStudentId(int studentId) {
        List<Courses> courses = new ArrayList<>();
        String query = "SELECT c.CourseID, c.CourseName, c.Description, c.Level, c.Price, c.Rating, c.TotalSessions, c.CourseStatus "
                + "FROM StudentCourses sc "
                + "JOIN Courses c ON sc.CourseID = c.CourseID "
                + "WHERE sc.StudentID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Courses course = new Courses(
                            rs.getInt("CourseID"),
                            rs.getString("CourseName"),
                            rs.getString("Description"),
                            rs.getString("Level"),
                            rs.getDouble("Price"),
                            rs.getFloat("Rating"),
                            rs.getInt("TotalSessions"),
                            rs.getString("CourseStatus")
                    );
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public boolean markAttendance(int tutorId, int courseId, int sessionNumber, String status) {
        boolean isSuccessful = false;
        String sql = "UPDATE Attendance SET Status = ?, SessionDate = GETDATE() "
                + "WHERE CourseID = ? AND SessionNumber = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);  // Trạng thái điểm danh (Present/Absent)
            stmt.setInt(2, courseId);
            stmt.setInt(3, sessionNumber);

            int rowsAffected = stmt.executeUpdate(); // Cập nhật trạng thái điểm danh
            if (rowsAffected > 0) {
                isSuccessful = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    public List<Course> getCoursesByUserIdReview(int userId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.CourseID, c.CourseName, c.Description, c.Level, c.Price, c.Rating, sc.StudentID, sc.Status "
                + "FROM Courses c "
                + "JOIN StudentCourses sc ON c.CourseID = sc.CourseID "
                + "JOIN Students s ON sc.StudentID = s.StudentID "
                + "WHERE s.UserID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setCourseId(rs.getInt("CourseID"));
                    course.setCourseName(rs.getString("CourseName"));
                    course.setDescription(rs.getString("Description"));
                    course.setLevel(rs.getString("Level"));
                    course.setPrice(rs.getDouble("Price"));
                    course.setRating(rs.getFloat("Rating"));
                    course.setStudentId(rs.getInt("StudentID"));
                    course.setCourseStatus(rs.getString("Status")); 
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public void updateCourseStatusStudent(int courseId, int studentId) {
        String sql = "UPDATE StudentCourses SET Status = ? WHERE CourseID = ? AND studentID =?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "Hoàn thành");
            stmt.setInt(2, courseId);
            stmt.setInt(3, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateCompletedSessions(int studentId, int courseId) {
        boolean isUpdated = false;
        String sql = "UPDATE StudentCourses SET completedSessions = completedSessions + 1 "
                + "WHERE StudentID = ? AND CourseID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);  // ID học viên
            stmt.setInt(2, courseId);   // ID khóa học

            int rowsAffected = stmt.executeUpdate();  // Cập nhật số buổi học đã hoàn thành
            if (rowsAffected > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public boolean checkCourseCompletion(int courseId, int sessionCount) {
        boolean isCompleted = false;
        String sql = "SELECT completedSessions FROM StudentCourses WHERE CourseID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {  // Dùng try-with-resources cho ResultSet
                if (rs.next() && rs.getInt("completedSessions") >= sessionCount) {
                    isCompleted = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCompleted;
    }

    public List<Course> getAvailableCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses WHERE CourseStatus = 'Ongoing'"; // hoặc theo trạng thái khóa học bạn muốn
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {  // Xóa dấu ngoặc thừa ở đây
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("CourseName"));
                course.setDescription(rs.getString("Description"));
                course.setLevel(rs.getString("Level"));
                course.setPrice(rs.getDouble("Price"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
//    public static void main(String[] args) {
//        CourseDAO courseDAO = new CourseDAO();
//        List<Course> courses = courseDAO.getCoursesByUserId(4); // Thử với UserID = 4
//
//        System.out.println("Danh sách khóa học:");
//        for (Course course : courses) {
//            System.out.println(course);
//        }
//    }
}
