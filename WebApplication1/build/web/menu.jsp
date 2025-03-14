<%-- 
    Document   : menu
    Created on : Feb 12, 2025, 7:59:12 PM
    Author     : Huy
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EduOnline - Học nhanh hơn với gia sư</title>
    <link rel="stylesheet" href="css/menu.css">

</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="container">
        <!-- Hero Section -->
        <section class="hero">
            <h2 class="hero-title">Học nhanh hơn với gia sư ngôn ngữ giỏi nhất của bạn.</h2>
            <img src="https://static.preply.com/static/ssr/_next/static/media/hero-23.aaf52759.jpg" alt="Hero">
            <a href="login.jsp" class="start-button">Bắt đầu →</a>
            <li><a class="btn btn-success" href="courses">Courses</a></li>
            <li><a class="btn btn-success" href="CourseLearnController">Course Learn Review</a></li>
        </section>

        <!-- Stats Section -->
        <section class="stats">
            <div>
                <h3 class="stat-number">50.000 +</h3>
                <p>Gia sư giàu kinh nghiệm</p>
            </div>
            <div>
                <h3 class="stat-number">300.000+</h3>
                <p>Đánh giá gia sư 5 sao</p>
            </div>
            <div>
                <h3 class="stat-number">120+</h3>
                <p>Các môn học được giảng dạy</p>
            </div>
            <div>
                <h3 class="stat-number">180+</h3>
                <p>Quốc tịch của gia sư</p>
            </div>
        </section>

        <!-- Tutor Carousel Section -->
        <section class="tutor-carousel">
            <button class="carousel-button" onclick="prevTutor()">&lt;</button>
            <div class="tutor-info">
                <img id="tutor-image" src="https://preply.com/cdn-cgi/image/format=auto,fit=contain,width=700/https://static.preply.com/static/ssr/_next/static/media/Ash.a4ab1e48.jpg" alt="Gia sư" class="tutor-image">
                <div class="tutor-details">
                    <h3 id="tutor-quote">Lựa chọn tốt nhất tôi đã đưa ra để phát triển bản thân trong một thời gian dài.</h3>
                    <p><strong id="tutor-name">Tro</strong></p>
                    <span id="tutor-subject">Gia sư tiếng Anh</span>
                </div>
            </div>
            <button class="carousel-button" onclick="nextTutor()">&gt;</button>
        </section>

        <!-- Guarantee Section -->
        <section class="guarantee">
            <h2 class="guarantee-title">Những bài học bạn sẽ thích. Đảm bảo.</h2>
            <p class="guarantee-text">Hãy thử một gia sư khác miễn phí nếu bạn không hài lòng.</p>
        </section>

        <!-- Become Tutor Section -->
        <section class="become-tutor">
            <div class="tutor-content">
                <h2 class="tutor-title">Trở thành gia sư</h2>
                <p class="tutor-description">Kiếm tiền bằng cách chia sẻ kiến thức chuyên môn của bạn với học sinh.</p>
                <p class="tutor-description">Đăng ký để bắt đầu dạy kèm trực tuyến với EduOnline.</p>
                <ul class="tutor-benefits">
                    <li>✅ Tìm học sinh mới</li>
                    <li>✅ Phát triển doanh nghiệp của bạn</li>
                    <li>✅ Nhận thanh toán an toàn</li>
                </ul>
                <a href="register-tutor.jsp" class="tutor-button">Trở thành gia sư →</a>
                <a href="#" class="tutor-link">Nền tảng của chúng tôi hoạt động như thế nào</a>
            </div>
            <img src="https://static.preply.com/static/ssr/_next/static/media/tutor-23.2c14e711.jpg" alt="Trở thành gia sư" class="tutor-image">
        </section> 
    </div>

    <script>
        // JavaScript để điều khiển slider gia sư
        let tutors = [
            {
                name: "Tro",
                subject: "Gia sư tiếng Anh",
                image: "https://preply.com/cdn-cgi/image/format=auto,fit=contain,width=700/https://static.preply.com/static/ssr/_next/static/media/Ash.a4ab1e48.jpg",
                quote: "Lựa chọn tốt nhất tôi đã đưa ra để phát triển bản thân trong một thời gian dài."
            },
            {
                name: "Hoa Ly",
                subject: "Gia sư tiếng Anh",
                image: "https://preply.com/cdn-cgi/image/format=auto,fit=contain,width=700/https://static.preply.com/static/ssr/_next/static/media/Bree.1614943e.jpg",
                quote: "Chỉ cần vài bài học, bạn đã có thể thấy sự khác biệt."
            },
            {
                name: "Brina",
                subject: "Gia sư tiếng Anh",
                image: "https://preply.com/cdn-cgi/image/format=auto,fit=contain,width=700/https://static.preply.com/static/ssr/_next/static/media/Liv.bdf5bc50.jpg",
                quote: "Chỉ cần vài bài học, bạn đã có thể thấy sự khác biệt."
            }
        ];
        
        let currentTutor = 0;

        function updateTutor() {
            document.getElementById("tutor-image").src = tutors[currentTutor].image;
            document.getElementById("tutor-quote").innerText = tutors[currentTutor].quote;
            document.getElementById("tutor-name").innerText = tutors[currentTutor].name;
            document.getElementById("tutor-subject").innerText = tutors[currentTutor].subject;
        }

        function prevTutor() {
            currentTutor = (currentTutor - 1 + tutors.length) % tutors.length;
            updateTutor();
        }

        function nextTutor() {
            currentTutor = (currentTutor + 1) % tutors.length;
            updateTutor();
        }
    </script>
    <%@ include file="footer.jsp" %>
</body>
</html>
