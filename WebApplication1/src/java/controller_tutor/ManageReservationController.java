/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_tutor;

import dal.CourseRequestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.CourseRequest;

/**
 *
 * @author Huy
 */
@WebServlet(name = "ManageReservationController", urlPatterns = {"/ManageReservation"})
public class ManageReservationController extends HttpServlet {

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
            out.println("<title>Servlet ManageReservationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageReservationController at " + request.getContextPath() + "</h1>");
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
        String tutorIdParam = session.getAttribute("tutorID") + "";
        if(tutorIdParam.equals("null")) {
            response.sendRedirect("login");
            return;
        }
        System.out.println(tutorIdParam);
        int tutorId = (tutorIdParam != null) ? Integer.parseInt(tutorIdParam) : 0;
        CourseRequestDAO courseRequestDAO = new CourseRequestDAO();
        List<CourseRequest> courseRequests = courseRequestDAO.getRequestsByTutor(tutorId);

        request.setAttribute("courseRequests", courseRequests);

        request.getRequestDispatcher("manage-reservation.jsp").forward(request, response);
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
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String action = request.getParameter("action");

        CourseRequestDAO requestDAO = new CourseRequestDAO();
        if ("accept".equals(action)) {
            requestDAO.updateRequestStatus(requestId, "Accepted");
        } else if ("reject".equals(action)) {
            requestDAO.updateRequestStatus(requestId, "Rejected");
        }

        response.sendRedirect("ManageReservation");
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
