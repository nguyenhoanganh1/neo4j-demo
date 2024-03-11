package com.demo.neo4j.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

/**
 * @author anh.nguyen
 * @created 28/02/2024
 */

@Node
@Data
public class VipAccountGroup {
    @Id
    @GeneratedValue(value = GeneratedValue.UUIDGenerator.class)
    private UUID id;

    private String name;

    private String description;

}
