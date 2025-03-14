package model;

public class Courses {
    private int courseID;
    private String courseName;
    private String description;
    private String level;
    private double price;
    private float rating;
    private int totalSessions;   // ✅ Thêm số buổi học
    private String courseStatus; // ✅ Thêm trạng thái khóa học

    // Constructor
    public Courses(int courseID, String courseName, String description, String level, double price, float rating, int totalSessions, String courseStatus) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.description = description;
        this.level = level;
        this.price = price;
        this.rating = rating;
        this.totalSessions = totalSessions;
        this.courseStatus = courseStatus;
    }

    // Getters and Setters
    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotalSessions() {
        return totalSessions;
    }

    public void setTotalSessions(int totalSessions) {
        this.totalSessions = totalSessions;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    // ToString method
    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", level='" + level + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", totalSessions=" + totalSessions +
                ", courseStatus='" + courseStatus + '\'' +
                '}';
    }
}
