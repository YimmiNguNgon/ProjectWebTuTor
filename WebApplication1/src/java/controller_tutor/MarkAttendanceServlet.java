/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller_tutor;

import dal.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Huy
 */
public class MarkAttendanceServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet MarkAttendanceServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MarkAttendanceServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Lấy thông tin từ request
        int tutorId = Integer.parseInt(request.getParameter("tutorId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int sessionNumber = Integer.parseInt(request.getParameter("sessionNumber"));
        String attendanceStatus = request.getParameter("attendanceStatus");

        // Gọi CourseDAO để điểm danh học viên
        CourseDAO courseDAO = new CourseDAO();
        boolean attendanceUpdated = courseDAO.markAttendance(tutorId, courseId, sessionNumber, attendanceStatus);

        if (attendanceUpdated) {
            // Cập nhật số buổi học hoàn thành
            boolean progressUpdated = courseDAO.updateCompletedSessions(studentId, courseId);

            if (progressUpdated) {
                // Kiểm tra xem khóa học đã hoàn thành hay chưa
                boolean isCourseCompleted = courseDAO.checkCourseCompletion(courseId, sessionNumber);

                if (isCourseCompleted) {
                    response.getWriter().write("Khóa học đã hoàn thành!");
                    response.sendRedirect("courseCompletion.jsp");
                } else {
                    response.getWriter().write("Điểm danh thành công!");
                    response.sendRedirect("attendance.jsp");
                }
            } else {
                response.getWriter().write("Lỗi khi cập nhật tiến độ học!");
                response.sendRedirect("error.jsp");
            }
        } else {
            response.getWriter().write("Lỗi khi điểm danh!");
            response.sendRedirect("error.jsp");
        }
    }
    

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
