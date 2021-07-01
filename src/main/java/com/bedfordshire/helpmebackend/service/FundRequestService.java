package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.exception.CustomBadRequestException;
import com.bedfordshire.helpmebackend.model.FundRequestModel;
import com.bedfordshire.helpmebackend.model.HelpRequestModel;
import com.bedfordshire.helpmebackend.model.OrganizationModel;
import com.bedfordshire.helpmebackend.model.UserModel;
import com.bedfordshire.helpmebackend.repository.FundRequestRepository;
import com.bedfordshire.helpmebackend.repository.HelpRequestRepository;
import com.bedfordshire.helpmebackend.repository.OrganizationRepository;
import com.bedfordshire.helpmebackend.repository.UserRepository;
import com.bedfordshire.helpmebackend.resource.FundRequestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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

    public String raiseFundRequest(String userUuid, FundRequestResource fundRequestResource) {
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
        fundRequestModel.setHelpRequestModel(helpRequestModelByUuid);
        fundRequestModel.setCurrencyType("LKR");
        fundRequestModel.setMaximumAmount(organizationModelOptional.get().getMaximumFundRequestAmount());
        fundRequestModel.setStartDate(new Date());
        fundRequestModel.setEndDate(new Date());
        fundRequestModel.setOrganizationModel(organizationModelOptional.get());
        fundRequestModel.setUuid(UUID.randomUUID().toString());
        FundRequestModel savedFundRequestModel = fundRequestRepository.save(fundRequestModel);
        return savedFundRequestModel.getUuid();
    }
}
