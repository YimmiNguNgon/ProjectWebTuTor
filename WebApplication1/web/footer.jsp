<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Footer | EduOnline</title>
    <style>
        /* Footer Styles */
.footer {
    background: #004080; /* Màu xanh đậm đồng bộ với header */
    color: white;
    padding: 30px 0;
    text-align: center;
}

.footer-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
}

.footer-section {
    flex: 1;
    min-width: 250px;
    margin-bottom: 20px;
}

.footer-section h3 {
    font-size: 20px;
    margin-bottom: 10px;
    border-bottom: 2px solid white;
    display: inline-block;
    padding-bottom: 5px;
}

.footer-section ul {
    list-style: none;
    padding: 0;
}

.footer-section ul li {
    margin: 8px 0;
}

.footer-section ul li a {
    color: white;
    text-decoration: none;
    transition: color 0.3s;
}

.footer-section ul li a:hover {
    color: #66ffcc; /* Hiệu ứng màu xanh ngọc khi hover */
}

.footer-bottom {
    border-top: 1px solid rgba(255, 255, 255, 0.3);
    padding-top: 10px;
    margin-top: 20px;
}

.footer-bottom p {
    margin-bottom: 5px;
}

.footer-bottom ul {
    list-style: none;
    padding: 0;
    display: flex;
    justify-content: center;
    gap: 15px;
}

.footer-bottom ul li a {
    color: white;
    text-decoration: none;
    transition: color 0.3s;
}

.footer-bottom ul li a:hover {
    color: #66ffcc;
}

@media (max-width: 768px) {
    .footer-container {
        flex-direction: column;
        text-align: center;
    }
}

    </style>
</head>
<body><footer class="footer">
    <div class="footer-container">
        <div class="footer-section">
            <h3>Hỗ trợ</h3>
            <ul>
                <li><a href="#">Bạn có cần trợ giúp không?</a></li>
            </ul>
        </div>
        
        <div class="footer-section">
            <h3>Liên hệ</h3>
            <p></p>
            <p>Thôn Thái Bình, Xã Bình Yên</p>
            <p>Thạch Thất, Hà Nội</p>
        </div>

        <div class="footer-section">
            <h3>EDUOnline</h3>
            <ul class="social-links">
                <li><a href="#">Facebook</a></li>
                <li><a href="#">Instagram</a></li>
                <li><a href="#">YouTube</a></li>
                <li><a href="#">LinkedIn</a></li>
                <li><a href="#">TikTok</a></li>
            </ul>
        </div>

        
    </div>

    <div class="footer-bottom">
        <p>© 2012-2025 EDuOnline.</p>
        <ul>
            <li><a href="#">Trung tâm pháp lý</a></li>
            <li><a href="#">Chính sách bảo mật</a></li>
            <li><a href="#">Chính sách Cookie</a></li>
        </ul>
    </div>
</footer>
</body>
