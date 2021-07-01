package com.bedfordshire.helpmebackend.repository;

import com.bedfordshire.helpmebackend.model.HelpTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Lakitha Prabudh
 */
@Repository
public interface HelpTypeRepository extends JpaRepository<HelpTypeModel, Integer> {
    List<HelpTypeModel> findByValid(boolean isValid);
    Optional<HelpTypeModel> findByUuid(String uuid);
}
