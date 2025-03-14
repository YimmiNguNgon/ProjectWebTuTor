<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh Sách Khóa Học</title>
        <link rel="stylesheet" href="css/tutorcourses.css">
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

        <section class="course-list">
            <h2 class="mt-5">Chỉnh Sửa Khóa Học</h2> 
            <form action="EditCourseServlet" method="post" class="mt-4">
                <input type="hidden" name="courseID" value="${course.courseID}">

                <div class="mb-3">
                    <label for="courseName" class="form-label">Tên Khóa Học</label>
                    <input type="text" class="form-control" id="courseName" name="courseName" value="${course.courseName}" required>
                </div>

                <div class="mb-3">
                    <label for="description" class="form-label">Mô Tả</label>
                    <textarea class="form-control" id="description" name="description" rows="3" required>${course.description}</textarea>
                </div>

                <div class="mb-3">
                    <label for="level" class="form-label">Cấp Độ</label>
                    <input type="text" class="form-control" id="level" name="level" value="${course.level}" required>
                </div>

                <div class="mb-3">
                    <label for="price" class="form-label">Giá</label>
                    <input type="number" class="form-control" id="price" name="price" value="${course.price}" required>
                </div>

                <button type="submit" class="btn btn-primary">Cập Nhật</button>
                <a href="courseList.jsp" class="btn btn-secondary">Quay Lại</a>
            </form>
        </section>

    </body>
</html>