package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.exception.CustomBadRequestException;
import com.bedfordshire.helpmebackend.model.*;
import com.bedfordshire.helpmebackend.repository.*;
import com.bedfordshire.helpmebackend.resource.*;
import com.bedfordshire.helpmebackend.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lakitha Prabudh
 */
@Service
public class FundRequestService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FundRequestRepository fundRequestRepository;
    @Autowired
    private HelpRequestRepository helpRequestRepository;
    @Autowired
    private FundRaiseRepository fundRaiseRepository;

    public String raiseFundRequest(String userUuid, FundRequestResource fundRequestResource) throws ParseException {
        UserModel byUuid = userRepository.findByUuid(userUuid);
        Optional<OrganizationModel> organizationModelOptional = organizationRepository.findByUserModel(byUuid);
        if (!organizationModelOptional.isPresent()) {
            throw new CustomBadRequestException("Invalid organization.");
        }

        HelpRequestModel helpRequestModelByUuid = helpRequestRepository.findByUuid(fundRequestResource.getHelpRequestUuid());
        if (helpRequestModelByUuid == null) {
            throw new CustomBadRequestException("Invalid help request id.");
        }

        FundRequestModel fundRequestModel = new FundRequestModel();
        Optional<FundRequestModel> fundRequestModelOptional = fundRequestRepository.findByHelpRequestModel(helpRequestModelByUuid);
        if (fundRequestModelOptional.isPresent()) {
            fundRequestModel = fundRequestModelOptional.get();
        }

        fundRequestModel.setHelpRequestModel(helpRequestModelByUuid);
        fundRequestModel.setCurrencyType("USD");
        fundRequestModel.setMaximumAmount(organizationModelOptional.get().getMaximumFundRequestAmount());
        fundRequestModel.setStartDate(new Date());
        fundRequestModel.setEndDate(CommonUtil.getDateByString2(fundRequestResource.getEndDate()));
        fundRequestModel.setOrganizationModel(organizationModelOptional.get());
        fundRequestModel.setUuid(UUID.randomUUID().toString());
        FundRequestModel savedFundRequestModel = fundRequestRepository.save(fundRequestModel);
        //update help request status
        helpRequestModelByUuid.setStatus(CommonUtil.HELP_REQUEST_STATUS_ONGOING);
        helpRequestRepository.save(helpRequestModelByUuid);
        return savedFundRequestModel.getUuid();
    }

    public List getFundRequestHistory(String userUuid) {
        UserModel organizerUser = userRepository.findByUuid(userUuid);
        Optional<OrganizationModel> optionalOrganizationModel = organizationRepository.findByUserModel(organizerUser);
        List<FundRequestModel> fundRequestModelList = fundRequestRepository.findByOrganizationModel(optionalOrganizationModel.get());
        return fundRequestModelList.stream().map(fundRequestModel -> {
            FundRequestHistoryResource historyResource = new FundRequestHistoryResource();
            historyResource.setHelpRequestId(fundRequestModel.getHelpRequestModel().getUuid());
            historyResource.setFundRequestId(fundRequestModel.getUuid());
            historyResource.setContactNumber(fundRequestModel.getHelpRequestModel().getContactNumber());
            historyResource.setHelpRequestUsername(fundRequestModel.getHelpRequestModel().getUserModel().getName());
            historyResource.setExpireDate(CommonUtil.getStringDateByDate(fundRequestModel.getEndDate()));
            historyResource.setTotalRaisedAmount(new DecimalFormat("#.##").format(getTotalAmountForFundRaise(fundRequestModel)));
            historyResource.setStatus(fundRequestModel.getHelpRequestModel().getStatus());
            historyResource.setHelpType(fundRequestModel.getHelpRequestModel().getHelpTypeModel().getName());
            return historyResource;
        }).collect(Collectors.toList());
    }

    public List getDonationHistoryForFundRequestId(String fundRequestUuid) {
        Optional<FundRequestModel> byUuid = fundRequestRepository.findByUuid(fundRequestUuid);
        List<FundRaiseModel> fundRequestModelList = fundRaiseRepository.findAllByFundRequestModel(byUuid.get());
        return fundRequestModelList.stream().map(fundRaiseModel -> {
            FundRaiseResource resource = new FundRaiseResource();
            resource.setHelpRequestId(fundRaiseModel.getFundRequestModel().getHelpRequestModel().getUuid());
            resource.setDonatedDate(CommonUtil.getStringDateByDate(fundRaiseModel.getTime()));
            resource.setUsername(fundRaiseModel.getDonorModel().getName());
            resource.setAmount(new DecimalFormat("#.##").format(fundRaiseModel.getAmount()));
            resource.setStatus(fundRaiseModel.getStatus());
            return resource;
        }).collect(Collectors.toList());
    }

    public Stream<DonationHistoryResource> getDonationHistory(String userUuid) {
        UserModel donorModel = userRepository.findByUuid(userUuid);
        List<FundRaiseModel> donationList = fundRaiseRepository.findAllByDonorModel(donorModel);
        return donationList.stream().map(fundRaiseModel -> {
            DonationHistoryResource resource = new DonationHistoryResource();
            resource.setHelpRequestUuid(fundRaiseModel.getFundRequestModel().getHelpRequestModel().getUuid());
            resource.setHelpRequestStatus(fundRaiseModel.getFundRequestModel().getHelpRequestModel().getStatus());
            resource.setAmount(new DecimalFormat("#.##").format(fundRaiseModel.getAmount()));
            resource.setTime(CommonUtil.getStringDateByDate(fundRaiseModel.getTime()));
            resource.setTransactionStatus(fundRaiseModel.getStatus());
            resource.setPaymentName(fundRaiseModel.getPayerName());
            resource.setTransactionId(fundRaiseModel.getTransactionId());
            return resource;
        });

    }

    public void saveDonationDetail(String userUuid, FundRaiseRequestResource resource) {
        UserModel donorModel = userRepository.findByUuid(userUuid);
        Optional<FundRequestModel> fundRequestModelOptional = fundRequestRepository.findByUuid(resource.getFundRequestUuid());
        if (fundRequestModelOptional.isPresent()) {
            FundRaiseModel fundRaiseModel = new FundRaiseModel();
            fundRaiseModel.setDonorModel(donorModel);
            fundRaiseModel.setFundRequestModel(fundRequestModelOptional.get());
            fundRaiseModel.setPayerAddress(resource.getPayerAddress());
            fundRaiseModel.setPayerEmail(resource.getPayerEmail());
            fundRaiseModel.setPayerId(resource.getPayerId());
            fundRaiseModel.setPayerName(resource.getPayerName());
            fundRaiseModel.setTransactionId(resource.getTransactionId());
            fundRaiseModel.setStatus(resource.getStatus());
            fundRaiseModel.setTime(new Date());
            fundRaiseModel.setAmount(resource.getAmount());
            fundRaiseRepository.save(fundRaiseModel);
        }
    }

    public double getTotalAmountForFundRaise(FundRequestModel fundRequestModel) {
        List<FundRaiseModel> fundRaiseModels = fundRaiseRepository.findAllByFundRequestModel(fundRequestModel);
        return fundRaiseModels.stream().mapToDouble(FundRaiseModel::getAmount).sum();
    }

    //todo return
    public void getNonExpiredFundRaisedList() {
        List<FundRequestModel> allFundRequests = fundRequestRepository.findAll();
        for (FundRequestModel fundRequestModel : allFundRequests) {
            if ("PENDING".equals(fundRequestModel.getHelpRequestModel().getStatus())) {
                HelpRequestDashboardResource dashboardResource = new HelpRequestDashboardResource();
                HelpRequestDashboardResource.UserScreen userScreen = new HelpRequestDashboardResource.UserScreen();
                UserModel userModel = fundRequestModel.getHelpRequestModel().getUserModel();
                userScreen.setUserUuid(userModel.getUuid());
                userScreen.setUserImage(userModel.getImageUrl());
                userScreen.setUserName(userModel.getName());

                dashboardResource.setUserScreen(userScreen);

                HelpRequestModel helpRequestModel = fundRequestModel.getHelpRequestModel();
                HelpRequestDashboardResource.HelpRequestScreen helpRequestScreen = new HelpRequestDashboardResource.HelpRequestScreen();
                helpRequestScreen.setAffectedAreaImageUrl(helpRequestModel.getImageUrl());
                helpRequestScreen.setDescription(helpRequestModel.getDescription());
                helpRequestScreen.setDescription(helpRequestModel.getDescription());
                helpRequestScreen.setStatus(helpRequestModel.getStatus());
                helpRequestScreen.setHelpType(helpRequestModel.getHelpTypeModel().getName());

                dashboardResource.setHelpRequestScreen(helpRequestScreen);

                HelpRequestDashboardResource.FundRequestScreen fundRequestScreen = new HelpRequestDashboardResource.FundRequestScreen();
                //todo collect data from fund request history
                fundRequestScreen.setFundRaisedAmount(0);
                fundRequestScreen.setUuid(fundRequestModel.getUuid());
                fundRequestScreen.setStatus(helpRequestModel.getStatus());
                //todo change data model
                fundRequestScreen.setEndDate(fundRequestModel.getEndDate().toString());

                dashboardResource.setFundRequestScreen(fundRequestScreen);
            }
        }
    }
}
