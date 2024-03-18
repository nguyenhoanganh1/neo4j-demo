package com.demo.neo4j.request;

import lombok.Data;

import java.util.UUID;

/**
 * @author anh.nguyen
 * @created 18/03/2024
 */

@Data
public class UserRequest {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String vipAccountNumber;
}
