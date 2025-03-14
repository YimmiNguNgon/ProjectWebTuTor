package controller;

import dal.UserDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmailAndPassword(email, password);

        if (user != null ) {
           
                HttpSession session = request.getSession();
                session.setAttribute("user", user);  

                // Nếu người dùng là Tutor, lưu tutorID vào session
                if (user.getRole() == 3) {
                    int tutorID = userDAO.getTutorIDByUserID(user.getId()); // Lấy TutorID từ UserID
                    session.setAttribute("tutorID", tutorID); // Lưu tutorID vào session
                    System.out.println("TutorID đã được lưu vào session: " + tutorID); // Debug
                }
                // Điều hướng dựa trên role_id
                switch (user.getRole()) {
                    case 1:  // Admin
                        response.sendRedirect("homeadmin.jsp");
                        break;
                    case 2:  // Staff
                        response.sendRedirect("homestaff.jsp");
                        break;
                    case 3:  // Tutor
                        response.sendRedirect("hometutor.jsp");
                        break;
                    case 4:  // Student
                        response.sendRedirect("menu.jsp");
                        break;
                    default:
                        response.sendRedirect("login.jsp"); // Nếu role không hợp lệ, quay lại login
                        break;
                }
            
        } else {
            request.setAttribute("error", "Email hoặc mật khẩu không đúng.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển hướng đến trang login nếu là GET
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
