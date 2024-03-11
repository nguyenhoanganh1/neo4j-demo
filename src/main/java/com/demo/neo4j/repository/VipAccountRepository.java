package com.demo.neo4j.repository;

import com.demo.neo4j.entity.VipAccount;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author anh.nguyen
 * @created 28/02/2024
 */
public interface VipAccountRepository extends Neo4jRepository<VipAccount, UUID> {

    //    @Query("MATCH (vip:VipAccount) WHERE vip.vipAccountNumber CONTAINS $vipAccountNumber ")
    List<VipAccount> findByVipAccountNumberContaining(String vipAccountNumber);

}
