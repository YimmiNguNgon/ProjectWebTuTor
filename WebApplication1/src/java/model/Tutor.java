package model;

import java.sql.*;

public class Tutor {
    private int tutorId;      // ID của gia sư (khóa chính)
    private int userId;       // ID người dùng liên kết (khóa ngoại)
    private String email;      // Email của gia sư
    private String fullName;   // Tên đầy đủ của gia sư
    private String phoneNumber;
    private String gender;
    private String address;    // Số điện thoại của gia sư
    private String education;  // Trình độ học vấn
    private String experience; // Kinh nghiệm làm việc
    private double hourlyRate; // Giá mỗi giờ dạy
    private boolean verified;  // Trạng thái xác minh (true: đã xác minh, false: chưa xác minh)
    private String imageUrl;   // Đường dẫn đến ảnh của gia sư

    // Constructor không tham số
    public Tutor() {}

    // Constructor đầy đủ (Sửa để khớp với `getAllTutors()`)
    public Tutor(int tutorId, int userId, String email, String fullName, String phoneNumber, String gender, String address, String education, String experience, double hourlyRate, boolean verified, String imageUrl) {
        this.tutorId = tutorId;
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.education = education;
        this.experience = experience;
        this.hourlyRate = hourlyRate;
        this.verified = verified;
        this.imageUrl = imageUrl;  // Thêm ảnh vào constructor
    }

    // Getter và Setter cho imageUrl
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Các getter và setter khác
    public int getTutorId() {
        return tutorId;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getEducation() {
        return education;
    }

    public String getExperience() {
        return experience;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    
    @Override
    public String toString() {
        return "Tutor{" + "tutorId=" + tutorId + ", userId=" + userId + ", email=" + email + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", address=" + address + ", education=" + education + ", experience=" + experience + ", hourlyRate=" + hourlyRate + ", verified=" + verified + ", imageUrl=" + imageUrl + '}';
    }
}
