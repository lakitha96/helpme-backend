package com.bedfordshire.helpmebackend.repository;

import com.bedfordshire.helpmebackend.model.FundRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lakitha Prabudh
 */
public interface FundRequestRepository extends JpaRepository<FundRequestModel, Integer> {
}
