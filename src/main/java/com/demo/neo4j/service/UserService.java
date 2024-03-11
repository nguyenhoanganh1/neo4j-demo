package com.demo.neo4j.service;

import com.demo.neo4j.entity.User;
import com.demo.neo4j.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User Service
 *
 * @author anh.nguyen
 * @created 26/02/2024
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
