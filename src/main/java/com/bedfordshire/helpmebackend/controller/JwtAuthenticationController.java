package com.bedfordshire.helpmebackend.controller;

import com.bedfordshire.helpmebackend.resource.UserResource;
import com.bedfordshire.helpmebackend.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lakitha Prabudh
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserResource user) {
        return ResponseEntity.ok(userDetailsService.userRegister(user));
    }
}