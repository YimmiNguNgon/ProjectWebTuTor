/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_student;

import dal.CourseRequestDAO;
import dal.ScheduleDAO;
import dal.TutorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import model.CourseRequest;
import model.Tutor;
import model.User;

/**
 *
 * @author HP
 */
@WebServlet(name = "ReservationController", urlPatterns = {"/reservation"})
public class ReservationController extends HttpServlet {

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
            out.println("<title>Servlet ReservationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReservationController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseIdParam = request.getParameter("courseId");
        int courseId = (courseIdParam != null) ? Integer.parseInt(courseIdParam) : 0;

        TutorDAO tutorDAO = new TutorDAO();
        List<Tutor> tutors = tutorDAO.getTutorsByCourseId(courseId);

        request.setAttribute("tutors", tutors);
        request.setAttribute("courseId", courseId);
        request.getRequestDispatcher("reservation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login?type=authentization");
                return;
            }
            int userId = user.getId();
            CourseRequestDAO courseRequestDao = new CourseRequestDAO();
            String courseIdParam = request.getParameter("courseId");
            int tutorId = Integer.parseInt(request.getParameter("tutorId"));
            String dayOfWeek = request.getParameter("dayOfWeek");

            LocalDate currentDate = LocalDate.now();

            String startTimeStr = request.getParameter("startTime");
            String endTimeStr = request.getParameter("endTime");

            String startDateTime = currentDate + " " + startTimeStr + ":00";
            String endDateTime = currentDate + " " + endTimeStr + ":00";

            Timestamp startTime = Timestamp.valueOf(startDateTime);
            Timestamp endTime = Timestamp.valueOf(endDateTime);
            System.out.println("startTime" + startTime);
            System.out.println("startTime" + endTime);
            int courseId = (courseIdParam != null) ? Integer.parseInt(courseIdParam) : 0;

            ScheduleDAO scheduleDAO = new ScheduleDAO();
            TutorDAO tutorDAO = new TutorDAO();
            List<Tutor> tutors = tutorDAO.getTutorsByCourseId(courseId);

            request.setAttribute("courseId", courseId);
            request.setAttribute("tutors", tutors);
            if(courseRequestDao.isJoin(userId, courseId)) {
                 request.setAttribute("errorMessage", "Bạn đã join khóa học này rồi");
            }else
            if (scheduleDAO.isScheduleConflict(tutorId, dayOfWeek, startTime, endTime)) {
                request.setAttribute("errorMessage", "Gia sư này đã có lịch học vào thời gian này!");
            } else {
                int studentId = courseRequestDao.addStudent(userId, courseId);
                if (studentId > 0) {
                    boolean isAdd = courseRequestDao.addCourseRequest(userId, tutorId, courseId);
                    if (isAdd) {
                        scheduleDAO.createSchedule(tutorId, studentId, dayOfWeek, startTime, endTime);
                        request.setAttribute("successMessage", "Đặt lịch thành công!");
                    } else {
                        request.setAttribute("errorMessage", "Đặt lịch thất bại thử lại ngay!");
                    }
                } else {
                    request.setAttribute("errorMessage", "Đặt lịch thất bại thử lại ngay!");
                }
            }
            request.getRequestDispatcher("reservation.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi xử lý yêu cầu!");
            request.getRequestDispatcher("reservation.jsp").forward(request, response);
        }
    }

}
