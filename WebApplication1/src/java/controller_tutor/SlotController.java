/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_tutor;

import dal.CourseDAO;
import dal.CourseSessionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Attendance;
import model.Courses;

/**
 *
 * @author HP
 */
@WebServlet(name = "SlotController", urlPatterns = {"/SlotController"})
public class SlotController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        CourseDAO courseDAO = new CourseDAO();
        Courses course = courseDAO.getCourseById(courseId);
        CourseSessionDAO courseSessionDAO = new CourseSessionDAO();
        List<Attendance> attendanceList = courseSessionDAO.getAttendanceRecords(courseId);

        request.setAttribute("course", course);
        request.setAttribute("attendanceList", attendanceList);

        request.getRequestDispatcher("courseDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
