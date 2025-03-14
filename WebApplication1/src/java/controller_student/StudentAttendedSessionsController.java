/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_student;

import dal.CourseSessionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CourseSession;

/**
 *
 * @author HP
 */
@WebServlet(name = "StudentAttendedSessionsController", urlPatterns = {"/StudentAttendedSessionsController"})
public class StudentAttendedSessionsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));  

        CourseSessionDAO courseSessionDAO = new CourseSessionDAO();
        List<CourseSession> attendedSessions = courseSessionDAO.getAttendedSessionsByStudent(studentId);

        request.setAttribute("attendedSessions", attendedSessions); 
        request.setAttribute("studentId", studentId);
        request.getRequestDispatcher("attendedSessions.jsp").forward(request, response);
    }

}
