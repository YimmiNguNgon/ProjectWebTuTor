/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller_tutor;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Tutor;

/**
 *
 * @author Huy
 */
public class ProfileTutorDetailServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ProfileTutorDetailServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileTutorDetailServlet at " + request.getContextPath () + "</h1>");
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
         // Lấy session hiện tại (không tạo mới nếu không có)
        HttpSession session = request.getSession(false);

        // Kiểm tra nếu session tồn tại và có thông tin người dùng
        if (session != null) {
            Tutor detail = (Tutor) session.getAttribute("detail");  // Lấy thông tin người dùng từ session

            if (detail != null) {
                // Đưa thông tin người dùng vào request để hiển thị trong JSP
                request.setAttribute("detail", detail);

                // Chuyển hướng đến trang profiletutor.jsp để hiển thị thông tin gia sư
                RequestDispatcher dispatcher = request.getRequestDispatcher("profiletutor.jsp");
                dispatcher.forward(request, response);
            } else {
                // Nếu không có thông tin người dùng trong session, chuyển hướng về trang đăng nhập
                response.sendRedirect("login.jsp");
            }
        } else {
            // Nếu session không tồn tại, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
        }
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
