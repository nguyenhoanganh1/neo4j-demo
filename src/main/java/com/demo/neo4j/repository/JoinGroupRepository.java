package com.demo.neo4j.repository;

import com.demo.neo4j.entity.JoinGroup;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

/**
 * @author anh.nguyen
 * @created 05/03/2024
 */

public interface JoinGroupRepository extends Neo4jRepository<JoinGroup, Long> {

    JoinGroup findFirstByVipAccountGroupId(UUID id);

}
