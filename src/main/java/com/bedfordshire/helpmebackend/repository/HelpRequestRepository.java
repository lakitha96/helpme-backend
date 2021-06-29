package com.bedfordshire.helpmebackend.repository;

import com.bedfordshire.helpmebackend.model.HelpRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lakitha Prabudh
 */
public interface HelpRequestRepository extends JpaRepository<HelpRequestModel, Integer> {
}
