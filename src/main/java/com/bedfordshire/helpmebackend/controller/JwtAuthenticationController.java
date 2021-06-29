package com.bedfordshire.helpmebackend.controller;

import com.bedfordshire.helpmebackend.resource.UserResource;
import com.bedfordshire.helpmebackend.service.UserDetailsService;
import com.bedfordshire.helpmebackend.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lakitha Prabudh
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
public class JwtAuthenticationController extends ResponseHandler {

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserResource user) {
        return successResponseDataRetrieve(userDetailsService.userRegister(user));
    }
}