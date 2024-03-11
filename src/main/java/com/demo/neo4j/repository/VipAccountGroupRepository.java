package com.demo.neo4j.repository;

import com.demo.neo4j.entity.VipAccountGroup;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

/**
 * @author anh.nguyen
 * @created 03/03/2024
 */
public interface VipAccountGroupRepository extends Neo4jRepository<VipAccountGroup, UUID> {


}
