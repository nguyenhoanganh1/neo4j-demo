package com.demo.neo4j.entity;


import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.UUID;

/**
 * User Node
 *
 * @author anh.nguyen
 * @created 26/02/2024
 */

@Data
@Node
public class User {

    @Id
    @GeneratedValue(value = GeneratedValue.UUIDGenerator.class)
    private UUID id;

    private String username; // là SĐT hoặc số chứng khoán

    private String email;

    private String password;

    @Relationship(type = "OWNS", direction = Relationship.Direction.OUTGOING)
    private List<VipAccount> ownedVipAccounts;
}
