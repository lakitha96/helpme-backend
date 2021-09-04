package com.bedfordshire.helpmebackend.resource;

/**
 * @author Lakitha Prabudh
 */
public class DonationHistoryResource {
    private String transactionId;
    private String paymentName;
    private String amount;
    private String transactionStatus;
    private String helpRequestStatus;
    private String time;
    private String helpRequestUuid;

    public DonationHistoryResource() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getHelpRequestStatus() {
        return helpRequestStatus;
    }

    public void setHelpRequestStatus(String helpRequestStatus) {
        this.helpRequestStatus = helpRequestStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHelpRequestUuid() {
        return helpRequestUuid;
    }

    public void setHelpRequestUuid(String helpRequestUuid) {
        this.helpRequestUuid = helpRequestUuid;
    }
}
