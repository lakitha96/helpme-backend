package com.bedfordshire.helpmebackend.jwt;

import com.bedfordshire.helpmebackend.model.UserModel;
import com.bedfordshire.helpmebackend.repository.UserRepository;
import com.bedfordshire.helpmebackend.resource.UserCustomDetail;
import com.bedfordshire.helpmebackend.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@Service
public class JwtUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private UserResource getUserResource(UserModel userModel) {
        UserResource userResource = new UserResource();
        userResource.setUsername(userModel.getName());
        userResource.setPassword(userModel.getPassword());
        userResource.setUuid(userModel.getUuid());
        userResource.setEmail(userModel.getEmail());
        userResource.setRole(userModel.getRole());
        return userResource;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel byEmailAndActive = userRepository.findByEmail(email);
        if (byEmailAndActive == null) {
            throw new UsernameNotFoundException("Email not found.");
        }
        return new UserCustomDetail(getUserResource(byEmailAndActive));
    }
}
