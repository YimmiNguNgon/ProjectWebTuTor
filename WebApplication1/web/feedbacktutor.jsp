<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đánh Giá Gia Sư</title>
    <link rel="stylesheet" href="css/feedbacktutor.css"> <!-- Chưa có file CSS, tạo sau -->
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="container">
        <h2>Đánh Giá Gia Sư</h2>

        <form action="FeedbackServlet" method="post">
            <label for="tutor">Chọn Gia Sư:</label>
            <select name="tutorId" id="tutor" required>
                <option value="">-- Chọn Gia Sư --</option>
                <c:forEach var="tutor" items="${tutors}">
                    <option value="${tutor.tutorId}">${tutor.fullName} - ${tutor.education}</option>
                </c:forEach>
            </select>

            <label for="course">Chọn Khóa Học:</label>
            <select name="courseId" id="course" required>
                <option value="">-- Chọn Khóa Học --</option>
                <c:forEach var="course" items="${courses}">
                    <option value="${course.courseId}">${course.courseName}</option>
                </c:forEach>
            </select>

            <label>Kiến thức (1-5):</label>
            <input type="number" name="knowledge" min="1" max="5" required>

            <label>Kỹ năng giảng dạy (1-5):</label>
            <input type="number" name="teachingSkill" min="1" max="5" required>

            <label>Thái độ (1-5):</label>
            <input type="number" name="attitude" min="1" max="5" required>

            <label for="comments">Nhận xét:</label>
            <textarea name="comments" id="comments" rows="4"></textarea>

            <button type="submit">Gửi Đánh Giá</button>
        </form>
    </div>
<div style="text-align: center; margin-top: 20px;">
    <a href="menu.jsp" class="back-button">⬅ Quay lại Menu</a>
</div>

    <%@ include file="footer.jsp" %>
</body>
</html>
