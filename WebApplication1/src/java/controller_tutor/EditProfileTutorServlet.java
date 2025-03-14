/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_tutor;

import com.sun.jdi.connect.spi.Connection;
import dal.TutorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Tutor;
import model.User;

/**
 *
 * @author Huy
 */
public class EditProfileTutorServlet extends HttpServlet {

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
            out.println("<title>Servlet EditProfileTutorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileTutorServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        TutorDAO tutorDao = new TutorDAO();
        // Kiểm tra thông tin gia sư trong session
        User user = (User) session.getAttribute("user");
        Tutor tutor = null;
        if (user != null) {
            TutorDAO tutorDAO = new TutorDAO();
            tutor = tutorDAO.getTutorByUserId(user.getId());

            if (tutor != null) {

                request.setAttribute("tutor", tutor);
            }
        }
        if (tutor == null) {
            request.setAttribute("message", "Lỗi: Không tìm thấy thông tin gia sư trong session.");
            request.getRequestDispatcher("profiletutor.jsp").forward(request, response);
            return;
        }

        // Lấy UserID từ session
        int userId = tutor.getUserId();

        // Lấy dữ liệu từ form
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String education = request.getParameter("education");
        String experience = request.getParameter("experience");

        // Xử lý giá mỗi giờ
        double hourlyRate;
        try {
            hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Giá mỗi giờ dạy không hợp lệ.");
            request.getRequestDispatcher("profiletutor.jsp").forward(request, response);
            return;
        }

        // Gọi DAO để cập nhật thông tin
        TutorDAO tutorDAO = new TutorDAO();
        tutorDAO.updateTutorProfileByUserId(userId, fullName, gender, address,
                phoneNumber, education, experience, hourlyRate);

        // Cập nhật session với thông tin mới nhất
        Tutor updatedTutor = tutorDAO.getTutorByUserId(userId);
        if (updatedTutor != null) {
            session.setAttribute("tutor", updatedTutor);
            System.out.println("Cập nhật thông tin gia sư thành công!");
        } else {
            System.out.println("Lỗi: Không tìm thấy thông tin gia sư sau khi cập nhật.");
        }

        // Chuyển hướng về trang profile sau khi cập nhật
        response.sendRedirect("ProfileTuTorServlet");
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
