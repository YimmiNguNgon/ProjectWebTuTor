package controller_tutor;

import dal.CourseDAO;
import dal.CourseSessionDAO;
import dal.StudentCourseDAO;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import model.Student;
import model.Attendance;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import model.Courses;

@WebServlet(name = "MarkAttendanceController", urlPatterns = {"/MarkAttendanceController"})
public class MarkAttendanceController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int sessionNumber = Integer.parseInt(request.getParameter("sessionNumber"));

        // Initialize DAOs
        StudentCourseDAO studentDao = new StudentCourseDAO();
        CourseSessionDAO courseSessionDAO = new CourseSessionDAO();

        List<Student> students = studentDao.getStudentsByCourseId(courseId);
        List<Attendance> attendanceRecords = courseSessionDAO.getAttendanceRecords(courseId, sessionNumber);

        request.setAttribute("students", students);
        request.setAttribute("courseId", courseId);
        request.setAttribute("sessionNumber", sessionNumber);

        for (Student student : students) {
            boolean isAttended = false;
            for (Attendance attendance : attendanceRecords) {
                if (attendance.getStudentID() == student.getStudentID()) {
                    isAttended = attendance.isAttended();
                    break;
                }
            }
            student.setAttended(isAttended);
        }

        request.getRequestDispatcher("markAttendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int sessionNumber = Integer.parseInt(request.getParameter("sessionNumber"));
        java.sql.Date sessionDate = new java.sql.Date(System.currentTimeMillis());

        StudentCourseDAO studentDao = new StudentCourseDAO();
        CourseSessionDAO courseSessionDAO = new CourseSessionDAO();
        List<Student> students = studentDao.getStudentsByCourseId(courseId);
        CourseDAO courseDAO = new CourseDAO();
        Courses course = courseDAO.getCourseById(courseId);

        for (Student student : students) {
            boolean attended = request.getParameter("attended_" + student.getStudentID()) != null;

            Attendance existingAttendance = courseSessionDAO.getSessionByStudentAndCourse(courseId, student.getStudentID(), sessionNumber);

            if (existingAttendance != null) {
                courseSessionDAO.updateSession(courseId, student.getStudentID(), sessionNumber, sessionDate, attended);
            } else {
                courseSessionDAO.createSession(courseId, student.getStudentID(), sessionNumber, sessionDate, attended);
            }
            if (course != null && sessionNumber == course.getTotalSessions()) {
                courseDAO.updateCourseStatusStudent(courseId, student.getStudentID());
            }
        }

        response.sendRedirect("SlotController?courseId=" + courseId + "&status=Done");
    }

}
