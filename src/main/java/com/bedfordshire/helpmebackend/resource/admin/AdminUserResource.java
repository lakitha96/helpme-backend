package com.bedfordshire.helpmebackend.resource.admin;

/**
 * @author Lakitha Prabudh
 */
public class AdminUserResource {
    private String email;
    private String name;
    private String role;
    private String status;

    public AdminUserResource() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
