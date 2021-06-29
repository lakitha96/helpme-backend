package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.exception.CustomBadRequestException;
import com.bedfordshire.helpmebackend.model.HelpRequestModel;
import com.bedfordshire.helpmebackend.model.HelpTypeModel;
import com.bedfordshire.helpmebackend.model.UserModel;
import com.bedfordshire.helpmebackend.repository.HelpRequestRepository;
import com.bedfordshire.helpmebackend.repository.UserRepository;
import com.bedfordshire.helpmebackend.resource.HelpRequestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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

    public void saveHelpRequest(String userUuid, HelpRequestResource helpRequestResource) {
        UserModel userModel = userRepository.findByUuid(userUuid);
        Optional<HelpTypeModel> helpTypeByUuid = helpTypeService.getHelpTypeByUuid(helpRequestResource.getHelpTypeUuid());
        if (!helpTypeByUuid.isPresent()) {
            throw new CustomBadRequestException("Invalid help type uuid");
        }
        HelpRequestModel helpRequestModel = new HelpRequestModel();
        helpRequestModel.setHelpTypeModel(helpTypeByUuid.get());
        helpRequestModel.setDescription(helpRequestResource.getDescription());
        helpRequestModel.setUserModel(userModel);
        helpRequestModel.setName(helpRequestResource.getName());
        helpRequestModel.setUuid(UUID.randomUUID().toString());
        helpRequestModel.setRequestedTime(new Date());
        helpRequestModel.setLocation(helpRequestResource.getLocation());
        helpRequestModel.setStatus("PENDING");
        helpRequestRepository.save(helpRequestModel);
    }
}
