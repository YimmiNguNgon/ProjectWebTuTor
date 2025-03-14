/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
/**
 *
 * @author Admin
 */
public class Account {
     private int userId;
    private int roleId;
    private String name;
    private String email;
    private String password;
    private String phone;

    public Account() {
    }

    public Account(int userId, int roleId, String name, String email, String password, String phone) {
        this.userId = userId;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Account{" + "userId=" + userId + ", roleId=" + roleId + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone + '}';
    }
    
    
    
}
