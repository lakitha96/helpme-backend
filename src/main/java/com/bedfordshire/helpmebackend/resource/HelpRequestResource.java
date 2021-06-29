package com.bedfordshire.helpmebackend.resource;

import com.bedfordshire.helpmebackend.model.HelpTypeModel;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lakitha Prabudh
 */
public class HelpRequestResource {
    @JsonProperty(value = "help_type_uuid")
    private String helpTypeUuid;
    private String name;
    private String description;
    private String status;
    private String location;

    public HelpRequestResource() {
    }

    public String getHelpTypeUuid() {
        return helpTypeUuid;
    }

    public void setHelpTypeUuid(String helpTypeUuid) {
        this.helpTypeUuid = helpTypeUuid;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "HelpRequestResource{" +
                "helpTypeUuid=" + helpTypeUuid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
