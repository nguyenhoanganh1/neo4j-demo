package com.demo.neo4j.security;

import com.demo.neo4j.entity.User;
import com.demo.neo4j.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User account = userRepository.findFirstByUsername(username);
            return new UserDetailsImpl(account);
        } catch (Exception e) {
            throw new UsernameNotFoundException(username + " not found!");
        }
    }

}
