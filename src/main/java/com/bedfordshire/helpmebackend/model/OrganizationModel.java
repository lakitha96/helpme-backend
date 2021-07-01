package com.bedfordshire.helpmebackend.model;

import javax.persistence.*;

/**
 * @author Lakitha Prabudh
 */
@Entity
@Table(name = "organization")
public class OrganizationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    private String status;
    private double maximumFundRequestAmount;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_owner_id", referencedColumnName = "id")
    private UserModel userModel;

    public OrganizationModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public double getMaximumFundRequestAmount() {
        return maximumFundRequestAmount;
    }

    public void setMaximumFundRequestAmount(double maximumAmount) {
        this.maximumFundRequestAmount = maximumAmount;
    }

    @Override
    public String toString() {
        return "OrganizationModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", userModel=" + userModel +
                '}';
    }
}
