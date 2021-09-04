package com.bedfordshire.helpmebackend.repository;

import com.bedfordshire.helpmebackend.model.FundRequestModel;
import com.bedfordshire.helpmebackend.model.HelpRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Lakitha Prabudh
 */
public interface FundRequestRepository extends JpaRepository<FundRequestModel, Integer> {
    Optional<FundRequestModel> findByHelpRequestModel(HelpRequestModel helpRequestModel);
    Optional<FundRequestModel> findByUuid(String uuid);
}
