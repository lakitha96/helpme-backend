package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.client.AwsStorageClient;
import com.bedfordshire.helpmebackend.exception.CustomBadRequestException;
import com.bedfordshire.helpmebackend.model.HelpRequestModel;
import com.bedfordshire.helpmebackend.model.HelpTypeModel;
import com.bedfordshire.helpmebackend.model.UserModel;
import com.bedfordshire.helpmebackend.repository.HelpRequestRepository;
import com.bedfordshire.helpmebackend.repository.UserRepository;
import com.bedfordshire.helpmebackend.resource.HelpRequestDashboardResource;
import com.bedfordshire.helpmebackend.resource.HelpRequestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private AwsStorageClient awsStorageClient;

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
        List<HelpRequestModel> pendingList = helpRequestRepository.findByStatus("PENDING");
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
}
