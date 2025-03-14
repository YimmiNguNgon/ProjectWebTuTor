<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Courses</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="mt-5">Courses You Have Enrolled In</h2>

        <c:if test="${not empty courses}">
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Course Name</th>
                        <th>Description</th>
                        <th>Level</th>
                        <th>Price</th>
                        <th>Rating</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="course" items="${courses}">
                        <tr>
                            <td>${course.courseName}</td>
                            <td>${course.description}</td>
                            <td>${course.level}</td>
                            <td>${course.price}</td>
                            <td>${course.rating}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${course.courseStatus == 'Hoàn thành'}">
                                        <span class="badge badge-success">Completed</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge badge-warning">In Progress</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a class="btn btn-primary" href="StudentAttendedSessionsController?studentId=${course.studentId}">Review</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty courses}">
            <p>You have not enrolled in any courses yet.</p>
        </c:if>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
