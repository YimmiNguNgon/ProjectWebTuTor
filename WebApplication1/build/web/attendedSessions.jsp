<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.CourseSession" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Các buổi học đã điểm danh</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Các buổi học học viên đã điểm danh</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Khóa học</th>
                    <th>Buổi học</th>
                    <th>Ngày</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="session" items="${attendedSessions}">
                    <tr>
                        <td>${session.sessionId}</td>
                        <td>${session.courseId}</td>
                        <td>${session.sessionNumber}</td>
                        <td>${session.sessionDate}</td>
                        <td>
                            <a href="SessionReviewController?sessionId=${session.sessionId}&studentId=${studentId}" class="btn btn-primary">Review</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
