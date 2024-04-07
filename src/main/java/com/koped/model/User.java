package com.koped.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "tbl_user")
public class User {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "username", unique = true)
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "email")
    private String email;

    
    public User() {
    	
    }

    public void setUsername(String username) {
        if (username != null && !username.isEmpty()) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 8) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
    }

    public void setEmail(String email) {
        if (email != null && isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
}
