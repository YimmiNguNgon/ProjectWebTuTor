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
import jakarta.servlet.annotation.MultipartConfig;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Course;
import org.json.JSONObject;
/**
 *
 * @author Huy
 */
@MultipartConfig
public class AddCoursesServlet extends HttpServlet {
   
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
            out.println("<title>Servlet AddCoursesServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCoursesServlet at " + request.getContextPath () + "</h1>");
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
        
        CourseDAO tutorDAO = new CourseDAO();
        List<Course> availableCourses = tutorDAO.getAvailableCourses();
        request.setAttribute("courses", availableCourses);
    
    // Forward đến trang JSP chứa modal
    request.getRequestDispatcher("tutorcourses.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Integer tutorID = (Integer) session.getAttribute("tutorID");

        if (tutorID == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        // Lấy dữ liệu từ request
        // Lấy dữ liệu từ request
        String courseName = request.getParameter("courseName");
        String description = request.getParameter("description");
        String level = request.getParameter("level");
        double price = Double.parseDouble(request.getParameter("price"));
        int totalSessions = Integer.parseInt(request.getParameter("totalSessions")); // Số buổi học
        
        // Gọi DAO để thêm khóa học
        TutorDAO tutorDAO = new TutorDAO();
        System.out.println(tutorID + " " + courseName + " " + description  + " " + level + " " + price + " " + totalSessions);
        int courseID = tutorDAO.addCourseForTutor(tutorID, courseName, description, level, price, totalSessions);        // Trả về JSON cho AJAX
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject jsonResponse = new JSONObject();
        if (courseID > 0) {
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
