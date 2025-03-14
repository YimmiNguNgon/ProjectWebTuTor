<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh Sách Khóa Học</title>
        <link rel="stylesheet" href="css/tutorcourses.css">
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

        <section class="course-list">
            <div class="container">
                <h2>Khóa Học Hiện Tại Của Gia Sư</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Tên Khóa Học</th>
                            <th>Mô Tả</th>
                            <th>Cấp Độ</th>
                            <th>Giá (VNĐ)</th>
                            <th>Số Buổi</th>
                            
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="course" items="${sessionScope.tutorCourses}">
                            <tr>
                                <td>${course.courseName}</td>
                                <td>${course.description}</td>
                                <td>${course.level}</td>
                                <td>${course.price}</td>
                                <td>${course.totalSessions}</td>
                                
                                <td style="text-align: center;">
                                    <div style="display: flex; justify-content: center; gap: 10px;">
                                        <!-- Nút chỉnh sửa -->
                                        <a 
                                            class="edit-btn" 
                                            data-courseid="${course.courseID}" 
                                            data-coursename="${course.courseName}" 
                                            data-description="${course.description}" 
                                            data-level="${course.level}" 
                                            data-price="${course.price}" 
                                            data-totalsessions="${course.totalSessions}" 
                                            data-coursestatus="${course.courseStatus}" 
                                            onclick="showPopupEdit(event, this)">
                                            📝Chỉnh Sửa
                                        </a>

                                        <a href="javascript:void(0);" class="delete-btn" onclick="deleteCourse('${course.courseID}', this);">🗑️ Xóa</a>
                                        <a href="SlotController?courseId=${course.courseID}" class="edit-btn">📝 Điểm danh</a>
                                        
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Nút mở modal -->
                <button class="btn" id="openModal">Thêm Khóa Học</button>
            </div>
        </section>

        <!-- Modal -->
        <!-- Modal (Ẩn mặc định) -->
        <div id="addCourseModal" class="modal">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h2>Thêm Khóa Học Mới</h2>
                
                <form action="addcourseservlet" method="POST">
                    <input type="hidden" id="courseName" name="courseId" required>
                    
                    
                    <label for="courseName">Tên Khóa Học:   </label>
                    <input value="${course.courseName}"  type="text" id="courseName" name="courseName" required>

                    <label for="description">Mô Tả:</label>
                    <textarea id="description" name="description" required></textarea>

                    <label for="level">Cấp Độ:</label>
                    <select id="level" name="level" required>
                        <option value="Beginner">Beginner</option>
                        <option value="Intermediate">Intermediate</option>
                        <option value="Advanced">Advanced</option>
                    </select>

                    <label for="price">Giá (VNĐ):</label>
                    <input type="number" id="price" name="price" required>
                    <!--            <label for="cat">Category</label>
                                <input type="text" id="category" name="category" required>-->
                    <label for="totalSessions">Số Buổi Học:</label>
                    <input type="number" id="totalSessions" name="totalSessions" required>

                    
                    
                    <button type="submit" class="add-btn">Lưu Khóa Học</button>
                </form>
            </div>
        </div>


        <!-- Modal Edit Course -->
        <div id="editCourseModal" class="custom-modal">
            <div class="custom-modal-content">
                <span class="custom-close">&times;</span>
                <h2>Chỉnh Sửa Khóa Học</h2>
                <form id="editCourseForm" action="editCourseServlet" method="POST">
                    <!-- Hidden field for courseID -->
                    <input type="hidden" id="courseEditID" name="courseID">

                    <label for="courseName">Tên Khóa Học:</label>
                    <input type="text" id="courseEditName" name="courseName" required placeholder="Nhập tên khóa học">

                    <label for="description">Mô Tả:</label>
                    <textarea id="descriptionEdit" name="description" required placeholder="Nhập mô tả khóa học"></textarea>

                    <label for="level">Cấp Độ:</label>
                    <select id="levelEdit" name="level" required>
                        <option value="Beginner">Beginner</option>
                        <option value="Intermediate">Intermediate</option>
                        <option value="Advanced">Advanced</option>
                    </select>

                    <label for="price">Giá (VNĐ):</label>
                    <input type="number" id="priceEdit" name="price" min="0" step="1000" required placeholder="Nhập giá khóa học">

                    <!-- ✅ Thêm số buổi học -->
                    <label for="totalSessions">Số Buổi Học:</label>
                    <input type="number" id="totalSessionsEdit" name="totalSessions" min="1" required placeholder="Nhập số buổi học">

                   
                    

                    <button type="submit" class="custom-btn" onclick="return confirm('Bạn có chắc chắn muốn lưu thay đổi?')">Lưu Khóa Học</button>
                </form>
            </div>
        </div>
        <script>
            function showPopupEdit(event, element) {
//                event.preventDefault();
                const courseID = element.getAttribute('data-courseid');
                const courseName = element.getAttribute('data-coursename');
                const description = element.getAttribute('data-description');
                const level = element.getAttribute('data-level');
                const price = element.getAttribute('data-price');

                // Populate modal fields
                document.getElementById('courseEditID').value = courseID;
                document.getElementById('courseEditName').value = courseName;
                document.getElementById('descriptionEdit').value = description;
                document.getElementById('levelEdit').value = level;
                document.getElementById('priceEdit').value = price;

                // Show the modal
                document.getElementById('editCourseModal').style.display = 'block';
            }


            // Đóng modal khi nhấn vào nút close
            document.querySelector('.custom-close').addEventListener('click', function () {
                document.getElementById('editCourseModal').style.display = 'none';
            });

            // Đóng modal nếu người dùng nhấn vào vùng bên ngoài modal
            window.onclick = function (event) {
                if (event.target == document.getElementById('editCourseModal')) {
                    document.getElementById('editCourseModal').style.display = 'none';
                }
            };

            document.getElementById("editCourseForm").addEventListener("submit", function (event) {
                event.preventDefault(); // Ngăn form submit theo cách thông thường

                // Lấy dữ liệu form
                let formData = new FormData(this);

                // Gửi dữ liệu lên server thông qua fetch
                fetch("editcourseservlet", {
                    method: "POST",
                    body: formData
                })
                        .then(response => response.json()) // Giả sử bạn nhận về kết quả dưới dạng JSON
                        .then(data => {
                            // Xử lý kết quả (ví dụ: hiển thị thông báo thành công hoặc cập nhật lại danh sách khóa học)
                            alert("Cập nhật khóa học thành công!");
                            location.reload(); // Tải lại trang (hoặc cập nhật danh sách mà không tải lại)
                        })
                        .catch(error => {
                            console.error('Error:', error);
                            alert("Có lỗi xảy ra!");
                        });
            });
        </script>

        <!-- ✅ Script mở/đóng modal đúng cách -->
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var modal = document.getElementById("addCourseModal");
                var btn = document.getElementById("openModal");
                var closeBtn = document.getElementsByClassName("close")[0];

                // Ẩn modal khi tải trang
                modal.style.display = "none";

                // Hiển thị modal khi nhấn nút "Thêm Khóa Học"
                btn.addEventListener("click", function () {
                    modal.style.display = "flex";
                });

                // Đóng modal khi nhấn vào nút X
                closeBtn.addEventListener("click", function () {
                    modal.style.display = "none";
                });

                // Đóng modal khi click ra ngoài
                window.addEventListener("click", function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                    }
                });

                // 🟢 Gửi form bằng AJAX khi ấn "Lưu Khóa Học"
                document.querySelector("#addCourseModal form").addEventListener("submit", function (event) {
                    event.preventDefault(); // Ngăn form submit theo cách thông thường
                    console.log("Ad");
                    let formData = new FormData(this);
                    console.log(formData.get("courseName"))
                    fetch("AddCoursesServlet", {
                        method: "POST",
                        body: formData
                    })
                            .then(response => response.json()) // Chuyển đổi phản hồi thành JSON
                            .then(data => {
                                console.log(data)
                                if (data.success) {
                                    // 🟢 Thêm khóa học mới vào bảng hiển thị
                                    let newRow = document.createElement("tr");
                                    newRow.innerHTML =
                                            "<td>" + data.courseName + "</td>" +
                                            "<td>" + data.description + "</td>" +
                                            "<td>" + data.level + "</td>" +
                                            "<td>" + data.price + "</td>" +
                                            "<td>" + data.totalSessions + "</td>" +
                                            "<td>" +
                                            "<a href='editCourse.jsp?courseID=" + data.courseID + "' class='edit-btn'>Chỉnh Sửa</a>" +
                                            "<a href='deleteCourseServlet?courseID=" + data.courseID + "' class='delete-btn' onclick='return confirm(\"Bạn có chắc muốn xóa?\");'>Xóa</a>" +
                                            "</td>";


                                    document.querySelector("tbody").appendChild(newRow);

                                    // 🟢 Ẩn modal sau khi thêm thành công
                                    modal.style.display = "none";
                                    location.reload();
                                    // 🟢 Reset form
                                    document.querySelector("#addCourseModal form").reset();
                                } else {
                                    alert("Thêm khóa học thất bại!");
                                }
                            })
                            .catch(error => console.error("Lỗi khi thêm khóa học:", error));
                });
            });

        </script>
        <script>
            function deleteCourse(courseID) {
                if (confirm("Bạn có chắc muốn xóa khóa học này?")) {
                    // Gửi yêu cầu AJAX để xóa khóa học
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "deletecourseservlet", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    xhr.onload = function() {
                        var response = JSON.parse(xhr.responseText);
                        if (response.success) {
                            alert(response.message); // Thông báo xóa thành công
                            // Có thể xóa row trong bảng ngay lập tức nếu cần
                            location.reload(); // Tải lại trang để cập nhật danh sách khóa học
                        } else {
                            alert(response.message); // Thông báo lỗi
                        }
                    };
                    xhr.send("courseID=" + courseID); // Gửi courseID lên server
                }
            }
        </script>
        <style>
            /* Phong cách cho Modal */
.custom-modal {
    display: none; /* Ẩn modal mặc định */
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.4); /* Màu nền mờ */
    padding-top: 60px;
}

.custom-modal-content {
    background-color: #fff;
    margin: 5% auto;
    padding: 25px;
    border-radius: 12px;
    width: 80%;
    max-width: 500px;
    text-align: left;
    box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.2);
    position: relative;
    animation: fadeIn 0.3s ease-in-out;
}
/* Định dạng chung cho form */
#editCourseForm {
    width: 50%;
    margin: 20px auto;
    padding: 20px;
    background: #f9f9f9;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* Định dạng label */
#editCourseForm label {
    font-weight: bold;
    display: block;
    margin-top: 10px;
}

/* Định dạng input và textarea */
#editCourseForm input[type="text"],
#editCourseForm input[type="number"],
#editCourseForm textarea,
#editCourseForm select {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
}

/* Hiệu ứng focus cho input và textarea */
#editCourseForm input:focus,
#editCourseForm textarea:focus,
#editCourseForm select:focus {
    border-color: #007BFF;
    outline: none;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

/* Định dạng nút submit */
.custom-btn {
    display: block;
    width: 100%;
    padding: 12px;
    margin-top: 15px;
    background: #007BFF;
    color: white;
    font-size: 16px;
    font-weight: bold;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background 0.3s;
}

.custom-btn:hover {
    background: #0056b3;
}

/* Nút đóng modal */
.custom-close {
    color: #aaa;
    font-size: 28px;
    font-weight: bold;
    position: absolute;
    top: 10px;
    right: 20px;
    cursor: pointer;
}

.custom-close:hover,
.custom-close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

.custom-btn {
    background-color: #4CAF50; /* Màu xanh */
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    font-size: 16px;
    border-radius: 6px;
    transition: background 0.3s ease;
}

.custom-btn:hover {
    background-color: #45a049;
}

/* Căn giữa modal và hiệu ứng đẹp hơn */
.modal {
    display: none; /* Ẩn modal khi tải trang */
    position: fixed;
    z-index: 1000;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
}

/* Chỉnh sửa form trong modal */
.modal-content {
    background-color: #fff;
    padding: 25px;
    border-radius: 12px;
    width: 450px;
    max-width: 90%;
    text-align: left;
    box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.2);
    position: relative;
    animation: fadeIn 0.3s ease-in-out;
}

/* Tạo hiệu ứng khi modal mở */
@keyframes fadeIn {
    from {
        opacity: 0;
        transform: scale(0.95);
    }
    to {
        opacity: 1;
        transform: scale(1);
    }
}

/* Phong cách form */
.modal-content h2 {
    margin-bottom: 15px;
    font-size: 22px;
    color: #333;
    text-align: center;
}

/* Nhãn của input */
.modal-content label {
    font-weight: bold;
    display: block;
    margin: 10px 0 5px;
}

/* Các trường nhập liệu trong modal */
.modal-content input,
.modal-content textarea,
.modal-content select {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 16px;
}

/* Textarea cho mô tả */
.modal-content textarea {
    resize: vertical;
    min-height: 80px;
}

/* Nút Lưu khóa học */
.add-btn {
    width: 100%;
    background: linear-gradient(135deg, #28a745, #218838);
    color: white;
    padding: 12px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-size: 16px;
    transition: background 0.3s ease;
}

.add-btn:hover {
    background: linear-gradient(135deg, #218838, #1e7e34);
}

            /* Hiệu ứng mở modal */
            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: scale(0.95);
                }
                to {
                    opacity: 1;
                    transform: scale(1);
                }
            }
        </style>

    </body>
</html>