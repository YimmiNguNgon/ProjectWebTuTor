/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller_tutor;

import dal.CourseDAO;
import dal.TutorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author Huy
 */
public class DeleteCourseServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer tutorID = (Integer) session.getAttribute("tutorID");

        if (tutorID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy courseID từ request
        int courseID = Integer.parseInt(request.getParameter("courseID"));

        // Gọi DAO để xóa khóa học
        CourseDAO courseDAO = new CourseDAO();
        System.out.println("TutorID: " + tutorID + " đang xóa khóa học với ID: " + courseID);

        boolean success = courseDAO.deleteCourse(courseID); // Gọi phương thức deleteCourse từ DAO

        // Trả về kết quả dưới dạng JSON cho AJAX
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject jsonResponse = new JSONObject();

        if (success) {
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Khóa học đã được xóa thành công.");
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Không thể xóa khóa học.");
        }

        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
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
        processRequest(request, response);
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
