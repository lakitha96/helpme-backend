package com.bedfordshire.helpmebackend.repository;

import com.bedfordshire.helpmebackend.model.HelpRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Lakitha Prabudh
 */
public interface HelpRequestRepository extends JpaRepository<HelpRequestModel, Integer> {
    HelpRequestModel findByUuid(String uuid);
    List<HelpRequestModel> findByStatus(String status);
}
