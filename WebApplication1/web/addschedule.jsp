<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Lịch Dạy Mới</title>
    <link rel="stylesheet" href="css/schedule.css">
</head>
<body>
    <header>
        <div class="container">
            <nav>
                <ul>
                    <li><a href="hometutor.jsp">Trang Chủ</a></li>
                    <li><a href="ProfileTuTorServlet">Thông Tin Cá Nhân</a></li>
                    <li><a href="tutorcoursesservlet">Khóa Học</a></li>
                    <li><a href="scheduleservlet">Lịch Dạy</a></li>
                    <li><a href="contact.jsp">Liên Hệ</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <section class="add-schedule">
        <div class="container">
            <h2>Thêm Lịch Dạy Mới</h2>
            <form action="AddScheduleServlet" method="post">
                <table>
                    <tr>
                        <td><label for="dayOfWeek">Ngày Dạy:</label></td>
                        <td>
                            <select name="dayOfWeek" id="dayOfWeek" required>
                                <option value="Monday">Thứ Hai</option>
                                <option value="Tuesday">Thứ Ba</option>
                                <option value="Wednesday">Thứ Tư</option>
                                <option value="Thursday">Thứ Năm</option>
                                <option value="Friday">Thứ Sáu</option>
                                <option value="Saturday">Thứ Bảy</option>
                                <option value="Sunday">Chủ Nhật</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="startTime">Giờ Bắt Đầu:</label></td>
                        <td><input type="time" id="startTime" name="startTime" required></td>
                    </tr>
                    <tr>
                        <td><label for="endTime">Giờ Kết Thúc:</label></td>
                        <td><input type="time" id="endTime" name="endTime" required></td>
                    </tr>
                    <tr>
                        <td><label for="courseID">Môn Dạy:</label></td>
                        <td>
                            <select name="courseID" id="courseID" required>
                                <c:forEach var="course" items="${sessionScope.tutorCourses}">
                                    <option value="${course.courseID}">${course.courseName} - ${course.level}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="studentID">Học Sinh:</label></td>
                        <td>
                            <select name="studentID" id="studentID" required>
                                <c:forEach var="student" items="${sessionScope.students}">
                                    <option value="${student.studentID}">${student.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
                <button type="submit" class="add-btn">Thêm Lịch Dạy</button>
            </form>
        </div>
    </section>

    <jsp:include page="footer.jsp"/>
</body>
</html>
