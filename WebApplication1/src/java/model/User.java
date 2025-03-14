package model;

import java.sql.*;
/**
 *
 * @author Huy
 */
public class User {
    private int id;    
    private int role;
    private String email;
    private String fullName;
    private String password;
    private String phoneNumber;
    private String gender;
    private String address;
    private String imageUrl;  // Thêm trường image_url

    public User() {
    }

    public User(int id, int role, String email, String fullName, String password, String phoneNumber, String gender, String address, String imageUrl) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.imageUrl = imageUrl;  // Khởi tạo trường image_url
    }

    public int getId() {
        return id;
    }

    public int getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
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

    public String getImageUrl() {  // Thêm getter cho image_url
        return imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setImageUrl(String imageUrl) {  // Thêm setter cho image_url
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", role=" + role + ", email=" + email + ", fullName=" + fullName + ", password=" + password + ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", address=" + address + ", imageUrl=" + imageUrl + '}';  // Cập nhật toString
    }
}
