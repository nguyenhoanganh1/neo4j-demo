package com.demo.neo4j.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;

/**
 * @author anh.nguyen
 * @created 28/02/2024
 */
@Data
@Node
public class JoinGroup {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime since;

    @Relationship(type = "TARGET_NODE", direction = Relationship.Direction.OUTGOING)
    private VipAccountGroup vipAccountGroup;

}
