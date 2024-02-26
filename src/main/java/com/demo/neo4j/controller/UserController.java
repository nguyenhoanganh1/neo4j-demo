package com.demo.neo4j.controller;

import com.demo.neo4j.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anh.nguyen
 * @created 26/02/2024
 */

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public ResponseEntity<Boolean> healthCheck() {
        return ResponseEntity.ok(true);
    }
}
