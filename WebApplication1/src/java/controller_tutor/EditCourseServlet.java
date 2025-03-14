/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_tutor;

import dal.TutorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Courses;
import org.json.JSONObject;

/**
 *
 * @author Huy
 */
@MultipartConfig
public class EditCourseServlet extends HttpServlet {

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
            out.println("<title>Servlet EditCourseServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditCourseServlet at " + request.getContextPath() + "</h1>");
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
//        int courseID = Integer.parseInt(request.getParameter("courseID"));
//        TutorDAO tutorDAO = new TutorDAO();
//        Courses course = tutorDAO.getTutorById(id);
//
//        request.setAttribute("course", course);
//        request.getRequestDispatcher("/editCourse.jsp").forward(request, response);
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
        Integer tutorID = (Integer) session.getAttribute("tutorID");

        if (tutorID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy dữ liệu từ request
        int courseID = Integer.parseInt(request.getParameter("courseID"));
            String courseName = request.getParameter("courseName");
            String description = request.getParameter("description");
            String level = request.getParameter("level");
            double price = Double.parseDouble(request.getParameter("price"));
            int totalSessions = Integer.parseInt(request.getParameter("totalSessions")); // ✅ Thêm số buổi học
            String courseStatus = request.getParameter("courseStatus");

        // Gọi DAO để cập nhật khóa học
        TutorDAO tutorDAO = new TutorDAO();
        System.out.println(tutorID + " " + courseID + " " + courseName + " " + description + " " + level + " " + price + " " + totalSessions);

        boolean success = tutorDAO.editCourseForTutor(courseID, courseName, description, level, price, totalSessions, courseStatus);

        // Trả về JSON cho AJAX
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject jsonResponse = new JSONObject();

        if (success) {
            jsonResponse.put("success", true);
            jsonResponse.put("courseID", courseID);
            jsonResponse.put("courseName", courseName);
            jsonResponse.put("description", description);
            jsonResponse.put("level", level);
            jsonResponse.put("price", price);
            jsonResponse.put("totalSessions", totalSessions);
        } else {
            jsonResponse.put("success", false);
        }

        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
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
