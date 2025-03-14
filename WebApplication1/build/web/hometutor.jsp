<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" type="text/css" href="css/footer.css">
        
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

        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <header>
            <div class="container_header">
                <nav>
                    <ul>
                        <li><a href="hometutor.jsp">Trang Chủ</a></li>
                        <li><a href="ProfileTuTorServlet">Thông Tin Cá Nhân</a></li>
                        <li><a href="tutorcoursesservlet">Khóa Học</a></li>
                        <li><a href="ManageReservation">Đặt lịch</a></li>
                        <li><a href="scheduleservlet">Lịch Dạy</a></li>
                        <li><a href="attendance.jsp">Điểm Danh</a></li>

                    </ul>
                </nav>
            </div>
        </header>

        <section class="hero">
            <div class="container">
                <h2>Chào mừng đến với trang quản lý gia sư</h2>
                
            </div>
        </section>
        <section class="hero">
            <div class="container">
                <h2>Các khóa học hiện tại</h2>
                
            </div>
        </section>

        <%@ include file="footer.jsp" %>
    </body>
</html>
