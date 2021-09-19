package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.client.AwsStorageClient;
import com.bedfordshire.helpmebackend.exception.CustomBadRequestException;
import com.bedfordshire.helpmebackend.model.FundRequestModel;
import com.bedfordshire.helpmebackend.model.HelpRequestModel;
import com.bedfordshire.helpmebackend.model.HelpTypeModel;
import com.bedfordshire.helpmebackend.model.UserModel;
import com.bedfordshire.helpmebackend.repository.FundRequestRepository;
import com.bedfordshire.helpmebackend.repository.HelpRequestRepository;
import com.bedfordshire.helpmebackend.repository.UserRepository;
import com.bedfordshire.helpmebackend.resource.HelpRequestDashboardResource;
import com.bedfordshire.helpmebackend.resource.HelpRequestResource;
import com.bedfordshire.helpmebackend.resource.MapLocationResource;
import com.bedfordshire.helpmebackend.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lakitha Prabudh
 */
@Service
public class HelpRequestService {
    @Autowired
    private HelpTypeService helpTypeService;
    @Autowired
    private HelpRequestRepository helpRequestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FundRequestRepository fundRequestRepository;
    @Autowired
    private AwsStorageClient awsStorageClient;
    @Autowired
    private FundRequestService fundRequestService;

    public void saveHelpRequest(String userUuid, HelpRequestResource helpRequestResource, MultipartFile multipartFile) {
        UserModel userModel = userRepository.findByUuid(userUuid);
        Optional<HelpTypeModel> helpTypeByUuid = helpTypeService.getHelpTypeByUuid(helpRequestResource.getHelpTypeUuid());
        if (!helpTypeByUuid.isPresent()) {
            throw new CustomBadRequestException("Invalid help type uuid");
        }
        String requestUuid = UUID.randomUUID().toString();
        HelpRequestModel helpRequestModel = new HelpRequestModel();
        helpRequestModel.setHelpTypeModel(helpTypeByUuid.get());
        helpRequestModel.setDescription(helpRequestResource.getDescription());
        helpRequestModel.setUserModel(userModel);
        helpRequestModel.setName(helpRequestResource.getName());
        helpRequestModel.setUuid(requestUuid);
        helpRequestModel.setRequestedTime(new Date());
        helpRequestModel.setLat(helpRequestResource.getLoc_lat());
        helpRequestModel.setLng(helpRequestResource.getLoc_lng());
        helpRequestModel.setLocation(helpRequestResource.getLocation());
        helpRequestModel.setStatus("PENDING");
        helpRequestModel.setContactNumber(helpRequestResource.getContactNumber());
        if (multipartFile != null && !multipartFile.isEmpty()) {
            helpRequestModel.setImageUrl(uploadAffectedAreaImage(multipartFile, requestUuid));
        }
        helpRequestRepository.save(helpRequestModel);
    }

    public String uploadAffectedAreaImage(MultipartFile multipartFile, String helpUuid) {
        return awsStorageClient.uploadAsset("help-request", helpUuid, multipartFile);
    }

    public List<HelpRequestResource> getAllHelpRequestByStatus(String status) {
        List<HelpRequestModel> helpRequestModelList = helpRequestRepository.findByStatus(status);
        return helpRequestModelList.stream().map(helpRequestModel -> {
            HelpRequestResource helpRequestResource = new HelpRequestResource();
            helpRequestResource.setDescription(helpRequestModel.getDescription());
            helpRequestResource.setStatus(helpRequestModel.getStatus());
            helpRequestResource.setHelpTypeUuid(helpRequestModel.getHelpTypeModel().getUuid());
            helpRequestResource.setLocation(helpRequestModel.getLocation());
            helpRequestResource.setHelpRequestUuid(helpRequestModel.getUuid());
            return helpRequestResource;
        }).collect(Collectors.toList());
    }

    public List<HelpRequestDashboardResource> getAllPendingHelpRequests() {
        List<HelpRequestModel> pendingList = helpRequestRepository.findByStatus(CommonUtil.HELP_REQUEST_STATUS_PENDING);
        return pendingList.stream().map(requestModel -> {
            HelpRequestDashboardResource dashboardResource = new HelpRequestDashboardResource();
            HelpRequestDashboardResource.UserScreen userScreen = new HelpRequestDashboardResource.UserScreen();
            userScreen.setUserUuid(requestModel.getUserModel().getUuid());
            userScreen.setUserImage(requestModel.getUserModel().getImageUrl());
            userScreen.setUserName(requestModel.getUserModel().getName());

            dashboardResource.setUserScreen(userScreen);

            HelpRequestDashboardResource.HelpRequestScreen helpRequestScreen = new HelpRequestDashboardResource.HelpRequestScreen();
            helpRequestScreen.setAffectedAreaImageUrl(requestModel.getImageUrl());
            helpRequestScreen.setDescription(requestModel.getDescription());
            helpRequestScreen.setDescription(requestModel.getDescription());
            helpRequestScreen.setStatus(requestModel.getStatus());
            helpRequestScreen.setHelpType(requestModel.getHelpTypeModel().getName());
            helpRequestScreen.setLocLng(requestModel.getLng());
            helpRequestScreen.setLocLat(requestModel.getLat());
            helpRequestScreen.setName(requestModel.getName());
            helpRequestScreen.setUuid(requestModel.getUuid());

            dashboardResource.setHelpRequestScreen(helpRequestScreen);
            return dashboardResource;
        }).collect(Collectors.toList());
    }

    public List<MapLocationResource> getAllOngoingHelpRequestsMapLocations() {
        List<HelpRequestModel> pendingList = helpRequestRepository.findByStatus(CommonUtil.HELP_REQUEST_STATUS_PENDING);
        List<HelpRequestModel> ongoingList = helpRequestRepository.findByStatus(CommonUtil.HELP_REQUEST_STATUS_ONGOING);
        pendingList.addAll(ongoingList);

        return pendingList.stream().map(model -> {
            MapLocationResource mapLocationResource = new MapLocationResource();
            mapLocationResource.setLat(model.getLat());
            mapLocationResource.setLng(model.getLng());
            return mapLocationResource;
        }).collect(Collectors.toList());
    }

    public List<HelpRequestDashboardResource> getAllOngoingHelpRequests() {
        List<HelpRequestModel> pendingList = helpRequestRepository.findByStatus(CommonUtil.HELP_REQUEST_STATUS_ONGOING);
        return pendingList.stream().map(requestModel -> {
            HelpRequestDashboardResource dashboardResource = new HelpRequestDashboardResource();
            HelpRequestDashboardResource.UserScreen userScreen = new HelpRequestDashboardResource.UserScreen();
            userScreen.setUserUuid(requestModel.getUserModel().getUuid());
            userScreen.setUserImage(requestModel.getUserModel().getImageUrl());
            userScreen.setUserName(requestModel.getUserModel().getName());

            dashboardResource.setUserScreen(userScreen);

            HelpRequestDashboardResource.HelpRequestScreen helpRequestScreen = new HelpRequestDashboardResource.HelpRequestScreen();
            helpRequestScreen.setAffectedAreaImageUrl(requestModel.getImageUrl());
            helpRequestScreen.setDescription(requestModel.getDescription());
            helpRequestScreen.setDescription(requestModel.getDescription());
            helpRequestScreen.setStatus(requestModel.getStatus());
            helpRequestScreen.setHelpType(requestModel.getHelpTypeModel().getName());
            helpRequestScreen.setLocLng(requestModel.getLng());
            helpRequestScreen.setLocLat(requestModel.getLat());
            helpRequestScreen.setName(requestModel.getName());
            helpRequestScreen.setUuid(requestModel.getUuid());
            dashboardResource.setHelpRequestScreen(helpRequestScreen);

            Optional<FundRequestModel> fundRequestModelOptional = fundRequestRepository.findByHelpRequestModel(requestModel);
            if (fundRequestModelOptional.isPresent()) {
                HelpRequestDashboardResource.FundRequestScreen fundRequestScreen = new HelpRequestDashboardResource.FundRequestScreen();
                fundRequestScreen.setStatus(helpRequestScreen.getStatus());
                fundRequestScreen.setUuid(fundRequestModelOptional.get().getUuid());
                fundRequestScreen.setEndDate(CommonUtil.getStringDateByDate(fundRequestModelOptional.get().getEndDate()));
                fundRequestScreen.setMaxAmount(fundRequestModelOptional.get().getMaximumAmount());
                fundRequestScreen.setFundRaisedAmount(fundRequestService.getTotalAmountForFundRaise(fundRequestModelOptional.get()));

                dashboardResource.setFundRequestScreen(fundRequestScreen);

                HelpRequestDashboardResource.OrganizationScreen organizationScreen = new HelpRequestDashboardResource.OrganizationScreen();
                organizationScreen.setName(fundRequestModelOptional.get().getOrganizationModel().getName());
                organizationScreen.setImageUrl(fundRequestModelOptional.get().getOrganizationModel().getImageUrl());
                organizationScreen.setLocation(fundRequestModelOptional.get().getOrganizationModel().getLocation());
                dashboardResource.setOrganizationScreen(organizationScreen);
            }

            return dashboardResource;
        }).collect(Collectors.toList());
    }

    public Object getOngoingHelpRequestByUuid(String uuid) {
        HelpRequestModel requestModel = helpRequestRepository.findByUuid(uuid);
        if (requestModel == null) {
            throw new CustomBadRequestException("Invalid help request id.");
        }

        HelpRequestDashboardResource dashboardResource = new HelpRequestDashboardResource();
        HelpRequestDashboardResource.UserScreen userScreen = new HelpRequestDashboardResource.UserScreen();
        userScreen.setUserUuid(requestModel.getUserModel().getUuid());
        userScreen.setUserImage(requestModel.getUserModel().getImageUrl());
        userScreen.setUserName(requestModel.getUserModel().getName());

        dashboardResource.setUserScreen(userScreen);

        HelpRequestDashboardResource.HelpRequestScreen helpRequestScreen = new HelpRequestDashboardResource.HelpRequestScreen();
        helpRequestScreen.setAffectedAreaImageUrl(requestModel.getImageUrl());
        helpRequestScreen.setDescription(requestModel.getDescription());
        helpRequestScreen.setDescription(requestModel.getDescription());
        helpRequestScreen.setStatus(requestModel.getStatus());
        helpRequestScreen.setHelpType(requestModel.getHelpTypeModel().getName());
        helpRequestScreen.setLocLng(requestModel.getLng());
        helpRequestScreen.setLocLat(requestModel.getLat());
        helpRequestScreen.setName(requestModel.getName());
        helpRequestScreen.setUuid(requestModel.getUuid());
        dashboardResource.setHelpRequestScreen(helpRequestScreen);

        Optional<FundRequestModel> fundRequestModelOptional = fundRequestRepository.findByHelpRequestModel(requestModel);
        if (fundRequestModelOptional.isPresent()) {
            HelpRequestDashboardResource.FundRequestScreen fundRequestScreen = new HelpRequestDashboardResource.FundRequestScreen();
            fundRequestScreen.setStatus(helpRequestScreen.getStatus());
            fundRequestScreen.setUuid(fundRequestModelOptional.get().getUuid());
            fundRequestScreen.setEndDate(CommonUtil.getStringDateByDate(fundRequestModelOptional.get().getEndDate()));
            fundRequestScreen.setMaxAmount(fundRequestModelOptional.get().getMaximumAmount());
            fundRequestScreen.setFundRaisedAmount(fundRequestService.getTotalAmountForFundRaise(fundRequestModelOptional.get()));

            dashboardResource.setFundRequestScreen(fundRequestScreen);

            HelpRequestDashboardResource.OrganizationScreen organizationScreen = new HelpRequestDashboardResource.OrganizationScreen();
            organizationScreen.setName(fundRequestModelOptional.get().getOrganizationModel().getName());
            organizationScreen.setImageUrl(fundRequestModelOptional.get().getOrganizationModel().getImageUrl());
            organizationScreen.setLocation(fundRequestModelOptional.get().getOrganizationModel().getLocation());
            dashboardResource.setOrganizationScreen(organizationScreen);

        }
        return dashboardResource;
    }

    public List<HelpRequestDashboardResource> getAllHelpRequests() {
        List<HelpRequestDashboardResource> allHelpRequests = new ArrayList();
        allHelpRequests.addAll(getAllPendingHelpRequests());
        allHelpRequests.addAll(getAllOngoingHelpRequests());
        return allHelpRequests;
    }
}
