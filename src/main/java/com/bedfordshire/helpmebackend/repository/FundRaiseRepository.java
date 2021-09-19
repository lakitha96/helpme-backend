package com.bedfordshire.helpmebackend.repository;

import com.bedfordshire.helpmebackend.model.FundRaiseModel;
import com.bedfordshire.helpmebackend.model.FundRequestModel;
import com.bedfordshire.helpmebackend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Lakitha Prabudh
 */
public interface FundRaiseRepository extends JpaRepository<FundRaiseModel, Integer> {
    List<FundRaiseModel> findAllByFundRequestModel(FundRequestModel fundRequestModel);
    List<FundRaiseModel> findAllByDonorModel(UserModel donorModel);
}
