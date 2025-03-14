<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đặt lịch học | EduOnline</title>
        <link rel="stylesheet" href="css/booking.css">
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div class="container">
            <h2>Đặt lịch học</h2>

            <!-- Form đặt lịch học -->
            <form id="bookingForm" action="BookingServlet" method="post" onsubmit="addBookingToTable(event)">
                <label for="tutor">Chọn Gia Sư:</label>
                <select name="tutor" id="tutor" required>
                    <option value="">-- Chọn gia sư --</option>
                    <c:forEach var="tutor" items="${tutors}">
                        <option value="${tutor.tutorId}">${tutor.fullName} - ${tutor.education}</option>
                    </c:forEach>
                </select>




                <label for="course">Chọn Khóa Học:</label>
                <select name="course" id="course" required>
                    <option value="">-- Chọn khóa học --</option>
                    <c:forEach var="course" items="${courses}">
                        <option value="${course.id}">${course.name} - ${course.price} VND</option>
                    </c:forEach>
                </select>

                <label for="date">Chọn Ngày Học:</label>
                <input type="date" name="date" id="date" required>

                <label for="startTime">Chọn Giờ Học:</label>
                <input type="time" name="startTime" id="startTime" required>
                <input type="time" name="endTime" id="endTime" required>

                <label for="package">Chọn Gói Học:</label>
                <select name="package" id="package">
                    <option value="single">Học từng buổi</option>
                    <option value="long-term">Gói dài hạn</option>
                </select>

                <label for="trial">Đăng ký buổi học thử:</label>
                <input type="checkbox" name="trial" id="trial"> Học thử miễn phí (hoặc phí thấp)

                <button type="submit">Đặt lịch học</button>
            </form>

            <!-- Danh sách lịch học đã đặt -->
            <h3>Lịch học đã đặt</h3>
            <table id="bookingTable">
                <thead>
                    <tr>
                        <th>Gia sư</th>
                        <th>Khóa học</th>
                        <th>Thời gian</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="booking" items="${bookings}">
                        <tr>
                            <td>${booking.tutorName}</td>
                            <td>${booking.courseName}</td>
                            <td>${booking.date} - ${booking.startTime} đến ${booking.endTime}</td>
                            <td>${booking.status}</td>
                            <td>
                                <c:if test="${booking.status == 'Pending'}">
                                    <form action="CancelBookingServlet" method="post">
                                        <input type="hidden" name="bookingId" value="${booking.id}">
                                        <button type="submit" class="cancel-button">Hủy lịch</button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <%@ include file="footer.jsp" %>
    </body>
</html>
