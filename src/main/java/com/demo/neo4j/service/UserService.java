package com.demo.neo4j.service;

import com.demo.neo4j.entity.User;
import com.demo.neo4j.entity.VipAccount;
import com.demo.neo4j.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(UUID id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public Page<User> findPageByFilter(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
