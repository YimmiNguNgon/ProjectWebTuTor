/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_student;

import dal.CourseDAO;
import dal.TutorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Course;
import model.Tutor;

/**
 *
 * @author ICBAdmin
 */
public class TutorListServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TutorListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TutorListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        model.User user = (model.User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp"); // Nếu chưa đăng nhập, chuyển về trang login
            return;
        }

        int userId = user.getRole(); // Lấy UserID từ session
        TutorDAO tutorDAO = new TutorDAO();
        CourseDAO courseDAO = new CourseDAO();

        // Lấy danh sách gia sư
        List<Tutor> tutorList = tutorDAO.getAllTutors();
        System.out.println("Gia sư lấy từ DB:");
        for (Tutor t : tutorList) {
            System.out.println(t.getTutorId() + " - " + t.getFullName());
        }

        // Lấy danh sách khóa học (lấy toàn bộ khóa học từ database)
        List<Course> courseList = courseDAO.getCoursesByUserId(userId);
        System.out.println("Khóa học lấy từ DB:");
        for (Course c : courseList) {
            System.out.println(c.getCourseId() + " - " + c.getCourseName());
        }

        // Đưa dữ liệu vào requestScope để hiển thị trong JSP
        request.setAttribute("tutors", tutorList);
        request.setAttribute("courses", courseList);

        // Chuyển hướng đến feedbacktutor.jsp
        request.getRequestDispatcher("feedbacktutor.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
