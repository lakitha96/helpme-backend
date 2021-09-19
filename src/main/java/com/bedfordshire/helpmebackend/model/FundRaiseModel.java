package com.bedfordshire.helpmebackend.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Lakitha Prabudh
 */
@Entity
@Table(name = "fund_raise")
public class FundRaiseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "donor_id", referencedColumnName = "id")
    private UserModel donorModel;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fund_request_id", referencedColumnName = "id")
    private FundRequestModel fundRequestModel;
    private String transactionId;
    private String payerAddress;
    private String payerEmail;
    private String payerName;
    private String payerId;
    private double amount;
    private String status;
    private Date time;

    public FundRaiseModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getDonorModel() {
        return donorModel;
    }

    public void setDonorModel(UserModel userModel) {
        this.donorModel = userModel;
    }

    public FundRequestModel getFundRequestModel() {
        return fundRequestModel;
    }

    public void setFundRequestModel(FundRequestModel fundRequestModel) {
        this.fundRequestModel = fundRequestModel;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date timestamp) {
        this.time = timestamp;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FundRaiseModel{" +
                "id=" + id +
                ", userModel=" + donorModel +
                ", fundRequestModel=" + fundRequestModel +
                ", transactionId='" + transactionId + '\'' +
                ", payerAddress='" + payerAddress + '\'' +
                ", payerEmail='" + payerEmail + '\'' +
                ", payerName='" + payerName + '\'' +
                ", payerId='" + payerId + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", timestamp=" + time +
                '}';
    }
}
