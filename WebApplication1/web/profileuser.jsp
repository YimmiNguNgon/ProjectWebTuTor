<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
        <section class="profile">
        <div class="container">
            <h2>Thông Tin Gia Sư</h2>
            <div class="profile-info">
                <img src="https://via.placeholder.com/150" alt="Gia Sư">
                <div class="details">
                    <!-- Kiểm tra nếu thông tin người dùng có trong session -->
                    <c:if test="${not empty sessionScope.user}">
                        <!-- Hiển thị thông tin gia sư -->
                        <h3>${sessionScope.user.fullname}</h3>
                        <p><strong>Email:</strong> ${sessionScope.user.email}</p>
                        <p><strong>Số điện thoại:</strong> ${sessionScope.user.phoneNumber}</p>
                        
                        <a href="editProfile.jsp"><button class="edit-btn">Chỉnh Sửa Thông Tin</button></a>
                    </c:if>
                    
                    
                </div>
            </div>
        </div>
    </section>
    </body>
</html>
