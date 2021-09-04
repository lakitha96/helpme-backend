package com.bedfordshire.helpmebackend.resource;

/**
 * @author Lakitha Prabudh
 */
public class FundRaiseRequestResource {
    private String fundRequestUuid;
    private String transactionId;
    private String payerAddress;
    private String payerEmail;
    private String payerName;
    private String payerId;
    private double amount;
    private String status;

    public FundRaiseRequestResource() {
    }

    public String getFundRequestUuid() {
        return fundRequestUuid;
    }

    public void setFundRequestUuid(String fundRequestUuid) {
        this.fundRequestUuid = fundRequestUuid;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPayerAddress() {
        return payerAddress;
    }

    public void setPayerAddress(String payerAddress) {
        this.payerAddress = payerAddress;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
