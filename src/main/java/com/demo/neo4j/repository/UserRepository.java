package com.demo.neo4j.repository;

import com.demo.neo4j.entity.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author anh.nguyen
 * @created 26/02/2024
 */
public interface UserRepository extends Neo4jRepository<User, String> {
}
