package com.bedfordshire.helpmebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String imageUrl;
    @JsonIgnore
    private String password;
    private String uuid;
    @JsonIgnore
    private String role;
    private String email;
    private boolean status;

    public UserModel() {
    }

    public UserModel(UserModel userModel) {
        this.setName(userModel.getName());
        this.setEmail(userModel.getEmail());
        this.setRole(userModel.getRole());
        this.setPassword(userModel.getPassword());
        this.setUuid(userModel.getUuid());
        this.setStatus(userModel.isStatus());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}