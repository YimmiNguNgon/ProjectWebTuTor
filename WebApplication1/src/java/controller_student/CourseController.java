/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller_student;

import dal.CourseDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Courses;

/**
 *
 * @author HP
 */
@WebServlet(name="CourseController", urlPatterns={"/courses"})
public class CourseController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CourseDAO coursesDAO = new CourseDAO();
            List<Courses> coursesList = coursesDAO.getAllCourses();
            request.setAttribute("coursesList", coursesList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("courses.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
