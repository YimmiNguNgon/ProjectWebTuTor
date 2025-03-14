<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Điểm danh học viên</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f7f8fa;
            }
            .container {
                background-color: #ffffff;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                margin-top: 30px;
            }
            h2 {
                color: #007bff;
                text-align: center;
                margin-bottom: 30px;
            }
            .table th {
                background-color: #007bff;
                color: white;
                text-align: center;
            }
            .table td {
                text-align: center;
                vertical-align: middle;
            }
            .table-responsive {
                margin-bottom: 30px;
            }
            .btn-submit {
                background-color: #28a745;
                color: white;
                font-weight: bold;
                width: 100%;
            }
            .btn-submit:hover {
                background-color: #218838;
                color: white;
            }
            .checkbox-label {
                font-size: 1.1rem;
            }
            .form-group {
                margin-bottom: 1.5rem;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2>Điểm danh học viên cho khóa học</h2>
            <form action="MarkAttendanceController" method="POST">
                <input type="hidden" name="courseId" value="${courseId}">
                <input type="hidden" name="sessionNumber" value="${sessionNumber}">
                
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Tên học viên</th>
                                <th>Điểm danh</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.studentID}</td>
                                <td>${student.account.name}</td>
                                <td>
                                    <input type="checkbox" name="attended_${student.studentID}" value="true" 
                                    <c:if test="${student.attended}">
                                        checked
                                    </c:if>>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-submit">Lưu điểm danh</button>
                </div>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
