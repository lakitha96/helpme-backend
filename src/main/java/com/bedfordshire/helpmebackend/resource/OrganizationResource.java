package com.bedfordshire.helpmebackend.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lakitha Prabudh
 */
public class OrganizationResource {
    @JsonProperty("organization_name")
    private String organizationName;
    private String location;
    private String status;
    @JsonProperty("user")
    private UserResource userResource;

    public OrganizationResource() {
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    public UserResource getUserResource() {
        return userResource;
    }

    public void setUserResource(UserResource userResource) {
        this.userResource = userResource;
    }
}
