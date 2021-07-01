package com.bedfordshire.helpmebackend.repository;

import com.bedfordshire.helpmebackend.model.OrganizationModel;
import com.bedfordshire.helpmebackend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Lakitha Prabudh
 */
public interface OrganizationRepository extends JpaRepository<OrganizationModel, Integer> {
    Optional<OrganizationModel> findByUserModel(UserModel userModel);
}
