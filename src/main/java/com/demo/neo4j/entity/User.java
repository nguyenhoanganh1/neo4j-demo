package com.demo.neo4j.entity;


import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

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
    private String id;

    private String username;

    private String password;

}
