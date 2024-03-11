package com.demo.neo4j.request;

import lombok.Data;

import java.util.UUID;

/**
 * @author anh.nguyen
 * @created 05/03/2024
 */


@Data
public class VipAccountRequest {
    private UUID id;
    private String vipAccountNumber;
    private UUID groupId;
}
