package com.demo.neo4j.service;

import com.demo.neo4j.entity.VipAccount;
import com.demo.neo4j.repository.VipAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author anh.nguyen
 * @created 28/02/2024
 */
@Service
public class VipAccountService {

    @Autowired
    private VipAccountRepository vipAccountRepository;

    public List<VipAccount> getAllVipAccounts() {
        return vipAccountRepository.findAll();
    }

    public Optional<VipAccount> getVipAccountById(UUID id) {
        return vipAccountRepository.findById(id);
    }

    public VipAccount createVipAccount(VipAccount vipAccount) {
        return vipAccountRepository.save(vipAccount);
    }

    public VipAccount save(VipAccount vipAccount) {
        return vipAccountRepository.save(vipAccount);
    }

    public void deleteVipAccount(UUID id) {
        vipAccountRepository.deleteById(id);
    }

    public Page<VipAccount> findPageByFilter(Pageable pageable) {
        return vipAccountRepository.findAll(pageable);
    }

    public List<VipAccount> recommendVipAccountNumber(String vipAccountNumber) {
        return vipAccountRepository.findByVipAccountNumberContaining(vipAccountNumber);
    }
}