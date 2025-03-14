<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh Sửa Thông Tin Gia Sư</title>
    <link rel="stylesheet" type="text/css" href="css/hometutor.css">
    <style>
        /* CSS cho modal */
        .modal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
        }
        .modal-content {
            background-color: white;
            padding: 20px;
            width: 40%;
            margin: 10% auto;
            border-radius: 5px;
            box-shadow: 0px 0px 10px #888;
        }
        .close-btn {
            float: right;
            font-size: 20px;
            cursor: pointer;
        }
        .form-group {
            margin-bottom: 10px;
        }
        .form-group label {
            font-weight: bold;
        }
        .form-group input, .form-group textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
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
        
        .container-profile {
    max-width: 800px;
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

.profile-info {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 15px;
}

.profile-info img {
    border-radius: 0%;
    width: 225px;
    height: 260px;
    object-fit: cover;
    border: 4px solid #007DA3;
}

.details {
    text-align: left;
    width: 100%;
    max-width: 500px;
}

.details h3 {
    color: #007DA3;
    margin-bottom: 10px;
}

.details p {
    margin: 5px 0;
    font-size: 16px;
}

strong {
    color: #333;
}

.edit-btn {
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

.edit-btn:hover {
    background: #005f7a;
}
@media (min-width: 768px) {
    .profile-info {
        flex-direction: row;
        align-items: flex-start;
        text-align: left;
    }

    .profile-info img {
        margin-right: 20px;
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
                    </ul>
                </nav>
        </div>
    </header>

    <section class="profile">
        <div class="container-profile">
            <h2>Thông Tin Gia Sư</h2>
            <div class="profile-info">
                <img style="border-radius: 0%; width: 225px;height: 260px; 
                    object-fit: cover;
                    border: 4px solid #007DA3;"src="${requestScope.tutor.imageUrl != null ? requestScope.tutor.imageUrl : 'https://via.placeholder.com/150'}" alt="Gia Sư" width="150">
                <div class="details">
                    <c:if test="${not empty requestScope.tutor}">
                        <h3>${requestScope.tutor.email}</h3>
                        <p><strong>Giới tính:</strong> ${requestScope.tutor.gender}</p>
                        <p><strong>Địa chỉ:</strong> ${requestScope.tutor.address}</p>
                        <p><strong>Email:</strong> ${requestScope.tutor.fullName}</p>
                        <p><strong>Số điện thoại:</strong> ${requestScope.tutor.phoneNumber}</p>
                        
                        <h4>Thông Tin Chi Tiết Gia Sư</h4>
                        <p><strong>Trình độ học vấn:</strong> ${requestScope.tutor.education}</p>
                        <p><strong>Kinh nghiệm:</strong> ${requestScope.tutor.experience}</p>
                        <p><strong>Giá mỗi giờ dạy:</strong> ${requestScope.tutor.hourlyRate}</p>
                        <p><strong>Trạng thái xác minh:</strong> ${requestScope.tutor.verified ? 'Đã xác minh' : 'Chưa xác minh'}</p>

                        <!-- Nút mở modal -->
                        <button class="edit-btn" onclick="openModal()">Chỉnh Sửa Thông Tin</button>
                    </c:if>
                        <c:if test="${not empty message}">
    <div class="alert">
        <p>${message}</p>
    </div>
</c:if>

<style>
    .alert {
        background-color: #d4edda;
        color: #155724;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #c3e6cb;
        border-radius: 5px;
    }
</style>
                </div>
            </div>
        </div>
    </section>

    <!-- Modal chỉnh sửa -->
    <div id="editModal" class="modal">
        <div class="modal-content">
            <span class="close-btn" onclick="closeModal()">&times;</span>
            <h3>Chỉnh Sửa Thông Tin Gia Sư</h3>
            <form action="editprofiletutorservlet" method="post">
            <input type="hidden" name="userId" value="${sessionScope.tutor.userId}">

            <div class="form-group">
                <label>Họ tên:</label>
                <input type="text" name="fullName" value="${sessionScope.tutor.fullName}" required>
            </div>

            <div class="form-group">
                <label>Giới tính:</label>
                <select name="gender">
                    <option value="Nam" ${sessionScope.user.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                    <option value="Nữ" ${sessionScope.user.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                </select>
            </div>

            <div class="form-group">
                <label>Địa chỉ:</label>
                <input type="text" name="address" value="${sessionScope.tutor.address}" required>
            </div>

            <div class="form-group">
                <label>Số điện thoại:</label>
                <input type="text" name="phoneNumber" value="${sessionScope.tutor.phone}" required>
            </div>

            <div class="form-group">
                <label>Trình độ học vấn:</label>
                <input type="text" name="education" value="${sessionScope.tutor.education}" required>
            </div>

            <div class="form-group">
                <label>Kinh nghiệm:</label>
                <textarea name="experience" required>${sessionScope.tutor.experience}</textarea>
            </div>

            <div class="form-group">
                <label>Giá mỗi giờ dạy:</label>
                <input type="number" name="hourlyRate" value="${sessionScope.tutor.hourlyRate}" required>
            </div>

            <button type="submit">Lưu Thay Đổi</button>
        </form>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>

    <script>
        function openModal() {
            document.getElementById("editModal").style.display = "block";
        }
        function closeModal() {
            document.getElementById("editModal").style.display = "none";
        }
        window.onclick = function(event) {
            var modal = document.getElementById("editModal");
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</body>
</html>
