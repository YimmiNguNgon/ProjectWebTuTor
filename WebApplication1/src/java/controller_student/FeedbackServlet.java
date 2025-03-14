/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_student;

import dal.FeedbackDAO;
import dal.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.Feedback;
import model.User;

/**
 *
 * @author ICBAdmin
 */
public class FeedbackServlet extends HttpServlet {

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
            out.println("<title>Servlet FeedbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy session để kiểm tra user đăng nhập
        // Lấy session để kiểm tra user đăng nhập
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != 4) { // Chỉ cho phép student feedback
            response.sendRedirect("login.jsp");
            return;
        }

// Lấy StudentID từ UserID (chứ không phải role)
        int userId = user.getId(); // Lấy ID của User đang đăng nhập
        int studentId = new StudentDAO().getStudentIdByUserId(userId); // Tìm StudentID từ UserID

// Kiểm tra nếu không tìm thấy StudentID
        if (studentId == -1) {
            request.setAttribute("error", "Bạn không phải học viên hợp lệ.");
            request.getRequestDispatcher("feedbacktutor.jsp").forward(request, response);
            return;
        }

        int tutorId = Integer.parseInt(request.getParameter("tutorId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int knowledge = Integer.parseInt(request.getParameter("knowledge"));
        int teachingSkill = Integer.parseInt(request.getParameter("teachingSkill"));
        int attitude = Integer.parseInt(request.getParameter("attitude"));
        String comments = request.getParameter("comments");

// Tính điểm Rating trung bình
        double rating = (knowledge + teachingSkill + attitude) / 3.0; // 

// Tạo đối tượng Feedback
        Feedback feedback = new Feedback(studentId, tutorId, courseId, rating, comments);

// Gọi DAO để lưu vào database
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        feedbackDAO.insertFeedback(feedback);

// Chuyển hướng về trang feedback với thông báo thành công
        request.setAttribute("message", "Cảm ơn bạn đã đánh giá!");
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
