package com.bedfordshire.helpmebackend.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Lakitha Prabudh
 */
@Entity
@Table(name = "fund_request")
public class FundRequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date startDate;
    private Date endDate;
    private String uuid;
    private String currencyType;
    private double raisedAmount;
    private double maximumAmount;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private OrganizationModel organizationModel;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "help_request_id", referencedColumnName = "id")
    private HelpRequestModel helpRequestModel;

    public FundRequestModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public double getRaisedAmount() {
        return raisedAmount;
    }

    public void setRaisedAmount(double raisedAmount) {
        this.raisedAmount = raisedAmount;
    }

    public double getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public OrganizationModel getOrganizationModel() {
        return organizationModel;
    }

    public void setOrganizationModel(OrganizationModel organizationModel) {
        this.organizationModel = organizationModel;
    }

    public HelpRequestModel getHelpRequestModel() {
        return helpRequestModel;
    }

    public void setHelpRequestModel(HelpRequestModel helpRequestModel) {
        this.helpRequestModel = helpRequestModel;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
