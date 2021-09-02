package com.bedfordshire.helpmebackend.resource;

/**
 * @author Lakitha Prabudh
 */
public class HelpRequestDashboardResource {
    private UserScreen userScreen;
    private HelpRequestScreen helpRequestScreen;
    private OrganizationScreen organizationScreen;
    private FundRequestScreen fundRequestScreen;

    public static class UserScreen {
        private String userUuid;
        private String userImage;
        private String userName;

        public UserScreen() {
        }

        public String getUserUuid() {
            return userUuid;
        }

        public void setUserUuid(String userUuid) {
            this.userUuid = userUuid;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class HelpRequestScreen {
        private String uuid;
        private String helpType;
        private String affectedAreaImageUrl;
        private String description;
        private String status;
        private String locLng;
        private String locLat;
        private String name;


        public HelpRequestScreen() {
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getAffectedAreaImageUrl() {
            return affectedAreaImageUrl;
        }

        public void setAffectedAreaImageUrl(String affectedAreaImageUrl) {
            this.affectedAreaImageUrl = affectedAreaImageUrl;
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

        public String getHelpType() {
            return helpType;
        }

        public void setHelpType(String helpType) {
            this.helpType = helpType;
        }

        public String getLocLng() {
            return locLng;
        }

        public void setLocLng(String locLng) {
            this.locLng = locLng;
        }

        public String getLocLat() {
            return locLat;
        }

        public void setLocLat(String locLat) {
            this.locLat = locLat;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class OrganizationScreen {
        private String uuid;
        private String name;
        private String imageUrl;
        private String location;

        public OrganizationScreen() {
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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    public static class FundRequestScreen {
        private String uuid;
        private String endDate;
        private String status;
        private double maxAmount;
        private double fundRaisedAmount;

        public FundRequestScreen() {
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getFundRaisedAmount() {
            return fundRaisedAmount;
        }

        public double getMaxAmount() {
            return maxAmount;
        }

        public void setMaxAmount(double maxAmount) {
            this.maxAmount = maxAmount;
        }

        public void setFundRaisedAmount(double fundRaisedAmount) {
            this.fundRaisedAmount = fundRaisedAmount;
        }
    }

    public HelpRequestDashboardResource() {
    }

    public UserScreen getUserScreen() {
        return userScreen;
    }

    public void setUserScreen(UserScreen userScreen) {
        this.userScreen = userScreen;
    }

    public HelpRequestScreen getHelpRequestScreen() {
        return helpRequestScreen;
    }

    public void setHelpRequestScreen(HelpRequestScreen helpRequestScreen) {
        this.helpRequestScreen = helpRequestScreen;
    }

    public OrganizationScreen getOrganizationScreen() {
        return organizationScreen;
    }

    public void setOrganizationScreen(OrganizationScreen organizationScreen) {
        this.organizationScreen = organizationScreen;
    }

    public FundRequestScreen getFundRequestScreen() {
        return fundRequestScreen;
    }

    public void setFundRequestScreen(FundRequestScreen fundRequestScreen) {
        this.fundRequestScreen = fundRequestScreen;
    }

    @Override
    public String toString() {
        return "HelpRequestScreenResource{" +
                "userScreen=" + userScreen +
                ", helpRequestScreen=" + helpRequestScreen +
                ", organizationScreen=" + organizationScreen +
                ", fundRequestScreen=" + fundRequestScreen +
                '}';
    }
}
