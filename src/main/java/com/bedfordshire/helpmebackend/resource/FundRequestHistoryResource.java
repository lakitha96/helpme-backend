package com.bedfordshire.helpmebackend.resource;

/**
 * @author Lakitha Prabudh
 */
public class FundRequestHistoryResource {
    private String fundRequestId;
    private String helpRequestId;
    private String helpRequestUsername;
    private String contactNumber;
    private String totalRaisedAmount;
    private String expireDate;
    private String status;
    private String helpType;

    public FundRequestHistoryResource() {
    }

    public String getFundRequestId() {
        return fundRequestId;
    }

    public void setFundRequestId(String fundRequestId) {
        this.fundRequestId = fundRequestId;
    }

    public String getHelpRequestUsername() {
        return helpRequestUsername;
    }

    public void setHelpRequestUsername(String helpRequestUsername) {
        this.helpRequestUsername = helpRequestUsername;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getTotalRaisedAmount() {
        return totalRaisedAmount;
    }

    public void setTotalRaisedAmount(String totalRaisedAmount) {
        this.totalRaisedAmount = totalRaisedAmount;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getHelpRequestId() {
        return helpRequestId;
    }

    public void setHelpRequestId(String helpRequestId) {
        this.helpRequestId = helpRequestId;
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

    @Override
    public String toString() {
        return "FundRequestHistoryResource{" +
                "fundRequestId='" + fundRequestId + '\'' +
                ", helpRequestUsername='" + helpRequestUsername + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", totalRaisedAmount='" + totalRaisedAmount + '\'' +
                ", expireDate='" + expireDate + '\'' +
                '}';
    }
}
