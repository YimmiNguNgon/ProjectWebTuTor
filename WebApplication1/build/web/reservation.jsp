<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Đặt lịch học</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        
        <div class="container mt-5">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h3 class="text-center">Đặt lịch học</h3>
                </div>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:if>

                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>

                <div class="card-body">
                    <form action="reservation" method="post">
                        <div class="mb-3">
                            <input type="hidden" name="courseId" class="form-control" value="${courseId}" required>
                            <label for="tutor" class="form-label">Chọn gia sư:</label>
                            <select name="tutorId" id="tutor" class="form-select">
                                <c:forEach var="tutor" items="${tutors}">
                                    <option value="${tutor.tutorId}">${tutor.fullName} - ${tutor.email}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="dayOfWeek" class="form-label">Chọn ngày trong tuần:</label>
                            <select name="dayOfWeek" id="dayOfWeek" class="form-select">
                                <option value="Monday">Thứ 2</option>
                                <option value="Tuesday">Thứ 3</option>
                                <option value="Wednesday">Thứ 4</option>
                                <option value="Thursday">Thứ 5</option>
                                <option value="Friday">Thứ 6</option>
                                <option value="Saturday">Thứ 7</option>
                                <option value="Sunday">Chủ nhật</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="startTime" class="form-label">Thời gian bắt đầu:</label>
                            <input type="time" name="startTime" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="endTime" class="form-label">Thời gian kết thúc:</label>
                            <input type="time" name="endTime" class="form-control" required>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-success">Đặt lịch</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                let startTimeInput = document.querySelector('input[name="startTime"]');
                let endTimeInput = document.querySelector('input[name="endTime"]');

                startTimeInput.setAttribute("min", "08:00");
                startTimeInput.setAttribute("max", "22:00");

                endTimeInput.setAttribute("min", "08:00");
                endTimeInput.setAttribute("max", "22:00");

                startTimeInput.addEventListener("change", function () {
                    let startTime = startTimeInput.value;
                    if (startTime < "08:00" || startTime > "22:00") {
                        alert("Thời gian bắt đầu phải từ 08:00 đến 22:00.");
                        startTimeInput.value = ""; 
                        return;
                    }

                    endTimeInput.setAttribute("min", startTime);
                });

                endTimeInput.addEventListener("change", function () {
                    let startTime = startTimeInput.value;
                    let endTime = endTimeInput.value;

                    if (endTime < "08:00" || endTime > "22:00") {
                        alert("Thời gian kết thúc phải từ 08:00 đến 22:00.");
                        endTimeInput.value = "";
                        return;
                    }

                    if (endTime <= startTime) {
                        alert("Thời gian kết thúc phải lớn hơn thời gian bắt đầu.");
                        endTimeInput.value = "";
                    }
                });
            });
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
