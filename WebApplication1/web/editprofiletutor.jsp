<%-- 
    Document   : editprofiletutor
    Created on : Feb 21, 2025, 12:01:12 AM
    Author     : Huy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chỉnh Sửa Thông Tin Gia Sư</title>
</head>
<body>
    <h2>Chỉnh Sửa Thông Tin Gia Sư</h2>
    
    <form action="UpdateTutorProfileServlet" method="post">
        <!-- Hiển thị thông tin gia sư hiện tại từ session -->
        <label for="education">Trình độ học vấn:</label>
        <input type="text" id="education" name="education" value="${sessionScope.tutorId.education}" required><br><br>

        <label for="experience">Kinh nghiệm:</label>
        <input type="text" id="experience" name="experience" value="${sessionScope.tutorId.experience}" required><br><br>

        <label for="hourlyRate">Giá mỗi giờ dạy:</label>
        <input type="number" id="hourlyRate" name="hourlyRate" value="${sessionScope.tutorId.hourlyRate}" required><br><br>

        <input type="submit" value="Cập Nhật Thông Tin">
    </form>
</body>
</html>
