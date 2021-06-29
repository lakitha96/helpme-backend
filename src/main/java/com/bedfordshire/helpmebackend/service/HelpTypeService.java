package com.bedfordshire.helpmebackend.service;

import com.bedfordshire.helpmebackend.model.HelpTypeModel;
import com.bedfordshire.helpmebackend.repository.HelpTypeRepository;
import com.bedfordshire.helpmebackend.resource.HelpTypeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Lakitha Prabudh
 */
@Service
public class HelpTypeService {
    @Autowired
    private HelpTypeRepository helpTypeRepository;

    public List<HelpTypeResource> getAllValidTypes() {
        return helpTypeRepository.findByValid(true).stream().map(this::bindHelpTypeModelData).collect(Collectors.toList());
    }

    private HelpTypeResource bindHelpTypeModelData(HelpTypeModel helpTypeModel) {
        HelpTypeResource helpTypeResource = new HelpTypeResource();
        helpTypeResource.setUuid(helpTypeModel.getUuid());
        helpTypeResource.setName(helpTypeModel.getName());
        return helpTypeResource;
    }

    public Optional<HelpTypeModel> getHelpTypeByUuid(String uuid) {
        return helpTypeRepository.findByUuid(uuid);
    }
}
