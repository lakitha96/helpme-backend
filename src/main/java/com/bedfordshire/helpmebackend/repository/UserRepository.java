package com.bedfordshire.helpmebackend.repository;

import com.bedfordshire.helpmebackend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}