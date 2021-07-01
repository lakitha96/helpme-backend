package com.bedfordshire.helpmebackend.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Lakitha Prabudh
 */
@Entity
@Table(name = "help_request")
public class HelpRequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel userModel;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "help_type_id", referencedColumnName = "id")
    private HelpTypeModel helpTypeModel;
    private String uuid;
    private String name;
    private String description;
    private String status;
    private Date requestedTime;
    private String location;

    public HelpRequestModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public HelpTypeModel getHelpTypeModel() {
        return helpTypeModel;
    }

    public void setHelpTypeModel(HelpTypeModel helpTypeModel) {
        this.helpTypeModel = helpTypeModel;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(Date requestedTime) {
        this.requestedTime = requestedTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "HelpRequestModel{" +
                "id=" + id +
                ", helpTypeModel=" + helpTypeModel +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", requestedTime='" + requestedTime + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
