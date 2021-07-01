package com.bedfordshire.helpmebackend.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lakitha Prabudh
 */
public class FundRequestResource {
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("currency_type")
    private String currencyType;
    @JsonProperty("help_request_uuid")
    private String helpRequestUuid;

    public FundRequestResource() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getHelpRequestUuid() {
        return helpRequestUuid;
    }

    public void setHelpRequestUuid(String helpRequestUuid) {
        this.helpRequestUuid = helpRequestUuid;
    }
}
