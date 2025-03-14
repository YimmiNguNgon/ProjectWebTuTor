<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Điểm Danh Lịch Dạy</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>
        <div class="container">
            <nav>
                <ul>
                    <li><a href="hometutor.jsp">Trang Chủ</a></li>
                    <li><a href="ProfileTuTorServlet">Thông Tin Cá Nhân</a></li>
                    <li><a href="tutorcoursesservlet">Khóa Học</a></li>
                    <li><a href="ManageReservation">Đặt lịch</a></li>
                    <li><a href="scheduleservlet">Lịch Dạy</a></li>
                    <li><a href="attendance.jsp">Điểm Danh</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <section class="schedule">
        <div class="container">
            <h2>Điểm Danh Lịch Dạy</h2>
            <table>
                <tr>
                    <th>Ngày</th>
                    <th>Giờ</th>
                    <th>Môn Dạy</th>
                    <th>Học Sinh</th>
                    <th>Điểm Danh</th>
                </tr>
                <!-- Hiển thị lịch dạy từ session -->
                <c:if test="${not empty sessionScope.schedules}">
                    <c:forEach var="schedule" items="${sessionScope.schedules}">
                        <tr>
                            <td>${schedule.dayOfWeek}</td>
                            <td>${schedule.startTime} - ${schedule.endTime}</td>
                            <td>${schedule.courseName}</td>
                            <td>${schedule.hocSinh}</td> <!-- Hiển thị tên học sinh -->
                            <td>
                                <form action="markAttendanceServlet" method="POST">
                                    <input type="hidden" name="tutorId" value="${schedule.tutorID}">
                                    <input type="hidden" name="courseId" value="${schedule.courseID}">
                                    <input type="hidden" name="studentId" value="${schedule.studentID}">
                                    <input type="hidden" name="sessionNumber" value="${schedule.sessionNumber}">
                                    <select name="attendanceStatus">
                                        <option value="Present">Có mặt</option>
                                        <option value="Absent">Vắng mặt</option>
                                    </select>
                                    <button type="submit">Điểm Danh</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </section>

    <jsp:include page="footer.jsp"/>

</body>
</html>
