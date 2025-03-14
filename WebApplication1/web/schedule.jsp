<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Thêm JSTL -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lịch Dạy Của Gia Sư</title>
    
    <style>
            /* Reset CSS */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background-color: #f5f5f5;
            color: #333;
            line-height: 1.6;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
        }

        /* Header */


        header nav ul {
            background: #0073e6;
            padding: 15px 0;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: center;
            list-style: none;
        }

        header nav ul li {
            margin: 0 15px;
        }

        header nav ul li a {
            color: #F2F5E4;
            text-decoration: none;
            font-size: 18px;
            font-weight: bold;
            transition: color 0.3s;
        }

        header nav ul li a:hover {
            color: #F2F5E4;
        }

        /* Hero Section */
        .hero {
            background: url('../images/hero-bg.jpg') no-repeat center center/cover;
            text-align: center;
            padding: 100px 0;
            color: white;
            box-shadow: inset 0px 0px 100px rgba(0, 0, 0, 0.3);
        }

        .hero h2 {
            font-size: 36px;
            font-weight: bold;
            color: #222;
        }

        /* Footer */
        footer {
            background: #222;
            color: white;
            text-align: center;
            padding: 15px 0;
            margin-top: 30px;
        }

        @media (max-width: 768px) {
            header nav ul {
                flex-direction: column;
                text-align: center;
            }

            header nav ul li {
                margin-bottom: 10px;
            }
        }

        .container_schedule {
    max-width: 900px;
    margin: 20px auto;
    padding: 20px;
    background: #f8f9fa;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center;
}

h2 {
    color: #007DA3;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 10px;
    background: white;
    border-radius: 8px;
    overflow: hidden;
}

th, td {
    padding: 12px;
    border: 1px solid #ddd;
    text-align: center;
}

th {
    background: #007DA3;
    color: white;
    font-weight: bold;
}

td {
    font-size: 16px;
}

tr:nth-child(even) {
    background: #f2f2f2;
}

tr:hover {
    background: #e0f7fa;
}

a {
    text-decoration: none;
    color: #007DA3;
    font-weight: bold;
}

a:hover {
    text-decoration: underline;
}

.add-class-btn {
    display: inline-block;
    margin-top: 15px;
    padding: 10px 20px;
    font-size: 16px;
    background: #007DA3;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background 0.3s ease;
}

.add-class-btn:hover {
    background: #005f7a;
}
        </style>
</head>
<body>
    <header>
            <div class="container_header">
                <nav>
                    <ul>
                        <li><a href="hometutor.jsp">Trang Chủ</a></li>
                        <li><a href="ProfileTuTorServlet">Thông Tin Cá Nhân</a></li>
                        <li><a href="tutorcoursesservlet">Khóa Học</a></li>
                        <li><a href="ManageReservation">Đặt lịch</a></li>
                        <li><a href="scheduleservlet">Lịch Dạy</a></li>
                    </ul>
                </nav>
            </div>
        </header>
    <section class="schedule">
        <div class="container_schedule">
            <h2>Lịch Dạy</h2>
            <table>
                <tr>
                    <th>Ngày</th>
                    <th>Giờ</th>
                    <th>Môn Dạy</th>
                    <th>Học Sinh</th>
                    <th>Chỉnh Sửa</th>
                </tr>
                <!-- Hiển thị lịch dạy từ session -->
                <c:if test="${not empty sessionScope.schedules}">
                <c:forEach var="schedule" items="${sessionScope.schedules}">
                    <tr>
                        <td>${schedule.dayOfWeek}</td>
                        <td>${schedule.startTime} - ${schedule.endTime}</td>
                        <td>${schedule.courseName}</td> <!-- Hiển thị môn học -->
                        <td>${schedule.hocSinh}</td> <!-- Hiển thị tên học sinh -->
                        <td><a href="editSchedule.jsp?scheduleID=${schedule.scheduleID}">Chỉnh Sửa</a></td>
                    </tr>
                </c:forEach>
            </c:if>
            </table>

            <a href="ManageReservation"><button class="add-class-btn">Thêm Lịch Dạy Mới</button></a>
        </div>
    </section>
    <jsp:include page="footer.jsp"/>
    
</body>
</html>
