package com.bedfordshire.helpmebackend.repository;

import com.bedfordshire.helpmebackend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    UserModel findByEmail(String email);

    UserModel findByUuid(String uuid);

}