<%-- 
    Document   : courses
    Created on : Mar 6, 2025, 1:13:11 AM
    Author     : HP
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách khóa học</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-4">
            <h2 class="text-center">Danh sách khóa học</h2>
            <div class="row">
                <c:choose>
                    <c:when test="${not empty coursesList}">
                        <c:forEach var="course" items="${coursesList}">
                            <div class="col-md-4 mb-4">
                                <div class="card shadow-sm">
                                    <div class="card-body">
                                        <h5 class="card-title">${course.courseName}</h5>
                                        <p class="card-text">${course.description}</p>
                                        <p><strong>Trình độ:</strong> ${course.level}</p>
                                        <p><strong>Giá:</strong> ${course.price} VNĐ</p>
                                        <p><strong>Đánh giá:</strong> ${course.rating} ⭐</p>
                                        <p><strong>Số buổi học:</strong> ${course.totalSessions}</p> <!-- ✅ Thêm totalSessions -->
                                        <p><strong>Trạng thái:</strong> ${course.courseStatus}</p> <!-- ✅ Thêm courseStatus -->
                                        <a href="reservation?courseId=${course.courseID}" class="btn btn-primary">Đặt lịch</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p class="text-center">Không có khóa học nào.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
