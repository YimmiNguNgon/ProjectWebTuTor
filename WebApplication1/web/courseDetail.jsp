<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chi tiết khóa học</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f8f9fa;
            }
            .container {
                background-color: #ffffff;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                margin-top: 30px;
            }
            h2 {
                color: #007bff;
            }
            .course-detail {
                margin-bottom: 20px;
            }
            .course-detail p {
                font-size: 1.1rem;
                line-height: 1.5;
            }
            .btn-course {
                background-color: #007bff;
                color: white;
                font-weight: bold;
            }
            .btn-course:hover {
                background-color: #0056b3;
                color: white;
            }
            table th {
                background-color: #007bff;
                color: white;
            }
            table td {
                vertical-align: middle;
            }
            .slot-link {
                color: #007bff;
                font-weight: bold;
            }
            .slot-link:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="text-center">Chi tiết khóa học: ${course.courseName}</h2>
            <div class="course-detail">
                <p><strong>Giới thiệu:</strong> ${course.description}</p>
                <p><strong>Cấp độ:</strong> ${course.level}</p>
                <p><strong>Giá:</strong> ${course.price} VND</p>
                <p><strong>Đánh giá:</strong> ${course.rating}</p>
                <p><strong>Số buổi học:</strong> ${course.totalSessions}</p>
                <p><strong>Trạng thái:</strong> ${course.courseStatus}</p>
            </div>
            <c:if test="${param.status == 'Done'}">
                <div class="alert alert-success text-center" role="alert">
                    Điểm danh đã được hoàn tất thành công!
                </div>
            </c:if>
            <h3 class="text-center mb-4">Danh sách Slot</h3>
            <form action="SlotController" method="POST">
                <input type="hidden" name="courseId" value="${course.courseID}">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Buổi học</th>
                            <th>Điểm danh</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="i" begin="1" end="${course.totalSessions}" varStatus="status">
                            <tr>
                                <td>#${status.index}</td>
                                <td>Slot #${status.index}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty attendanceList}">
                                            <c:set var="isMarked" value="false" />
                                            <c:forEach var="attendance" items="${attendanceList}">
                                                <c:if test="${attendance.sessionNumber == status.index && !isMarked}">
                                                    <a href="MarkAttendanceController?courseId=${course.courseID}&sessionNumber=${status.index}" class="slot-link">Chỉnh sửa điểm danh</a>
                                                    <c:set var="isMarked" value="true" />
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${!isMarked}">
                                                <a href="MarkAttendanceController?courseId=${course.courseID}&sessionNumber=${status.index}" class="slot-link">Điểm danh</a>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="MarkAttendanceController?courseId=${course.courseID}&sessionNumber=${status.index}" class="slot-link">Điểm danh</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
