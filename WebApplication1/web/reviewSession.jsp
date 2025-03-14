<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.SessionReview" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Review Buổi Học</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Đánh giá buổi học</h2>

            <form action="SessionReviewController" method="post">
                <input type="hidden" name="sessionId" value="${review.sessionId}">
                <input type="hidden" name="studentId" value="${review.studentId}">

                <div class="form-group">
                    <label for="rating">Đánh giá (1-5 sao):</label>
                    <input type="number" name="rating" class="form-control" id="rating" min="1" max="5" 
                           value="${review.rating != 0 ? review.rating : 1}" required>
                </div>

                <div class="form-group">
                    <label for="comment">Nhận xét:</label>
                    <textarea name="comment" class="form-control" id="comment" rows="4" required>${review.comment != null ? review.comment : ""}</textarea>
                </div>

                <div class="form-group">
                    <label for="reviewStatus">Trạng thái đánh giá:</label>
                    <select name="reviewStatus" class="form-control" id="reviewStatus" required>
                        <option value="25%" ${review.reviewStatus != null && review.reviewStatus.equals("25%") ? "selected" : ""}>25%</option>
                        <option value="50%" ${review.reviewStatus != null && review.reviewStatus.equals("50%") ? "selected" : ""}>50%</option>
                        <option value="75%" ${review.reviewStatus != null && review.reviewStatus.equals("75%") ? "selected" : ""}>75%</option>
                        <option value="100%" ${review.reviewStatus != null && review.reviewStatus.equals("100%") ? "selected" : ""}>100%</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Lưu Đánh Giá</button>
            </form>
        </div>
    </body>
</html>
