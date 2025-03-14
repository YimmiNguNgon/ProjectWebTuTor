<%@ taglib  %> prefix="c" uri="http://jakarta.apache.org/taglibs/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="css/homeuser.css">
    </head>
    <body>
        <div class="container">
            <h2>Welcome to Home</h2>
            
            <!-- Booking Section -->
            <div class="section">
                <h3>Your Bookings</h3>
                <div class="booking-container">
                    <c:forEach var="booking" items="${bookings}">
                        <div class="booking-card">
                            <p><strong>Course:</strong> ${booking.courseName}</p>
                            <p><strong>Time:</strong> ${booking.startTime} - ${booking.endTime}</p>
                            <p><strong>Status:</strong> ${booking.status}</p>
                            <button class="button">Cancel Booking</button>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Feedback Section -->
            <div class="section">
                <h3>Student Feedback</h3>
                <div class="feedback-container">
                    <c:forEach var="feedback" items="${feedbacks}">
                        <div class="feedback-card">
                            <p><strong>Student:</strong> ${feedback.studentName}</p>
                            <p><strong>Rating:</strong> ${feedback.rating} ?</p>
                            <p><strong>Comments:</strong> ${feedback.comments}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
