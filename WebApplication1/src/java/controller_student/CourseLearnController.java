/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_student;

import dal.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Course;
import model.User;

/**
 *
 * @author HP
 */
@WebServlet(name = "CourseLearnController", urlPatterns = {"/CourseLearnController"})
public class CourseLearnController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login?type=authentization");
            return;
        }
        int userId = user.getId();

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getCoursesByUserIdReview(userId);

        request.setAttribute("courses", courses);
        request.getRequestDispatcher("studentCourses.jsp").forward(request, response);
    }

}
