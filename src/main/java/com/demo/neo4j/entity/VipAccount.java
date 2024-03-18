package com.demo.neo4j.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.UUID;

/**
 * @author anh.nguyen
 * @created 28/02/2024
 */
@Data
@Node
public class VipAccount {
    @Id
    @GeneratedValue(value = GeneratedValue.UUIDGenerator.class)
    private UUID id;

    private String vipAccountNumber;

    private boolean isUsed = false;

    @Relationship(type = "JOIN_GROUP", direction = Relationship.Direction.OUTGOING)
    private List<JoinGroup> joinGroups;
}
