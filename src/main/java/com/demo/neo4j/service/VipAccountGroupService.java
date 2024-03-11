package com.demo.neo4j.service;

import com.demo.neo4j.entity.VipAccountGroup;
import com.demo.neo4j.repository.VipAccountGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author anh.nguyen
 * @created 03/03/2024
 */
@Service
public class VipAccountGroupService {

    @Autowired
    VipAccountGroupRepository vipAccountGroupRepository;


    public VipAccountGroup getById(UUID id) {
        return vipAccountGroupRepository.findById(id).orElse(null);
    }

    public Page<VipAccountGroup> findPageByFilter(Pageable pageable) {
        return vipAccountGroupRepository.findAll(pageable);
    }

    public VipAccountGroup create(VipAccountGroup vipAccountGroup) {
        return vipAccountGroupRepository.save(vipAccountGroup);
    }

    public VipAccountGroup update(VipAccountGroup vipAccountGroup) {
        VipAccountGroup vag = this.getById(vipAccountGroup.getId());
        if (null == vag) {
            return null;
        }
        vag.setName(vipAccountGroup.getName());
        vag.setDescription(vipAccountGroup.getDescription());
        return vipAccountGroupRepository.save(vag);
    }

    public void delete(UUID id) {
        VipAccountGroup vag = this.getById(id);
        if (null == vag) {
            return;
        }
        vipAccountGroupRepository.delete(vag);
    }

    public VipAccountGroup save(VipAccountGroup vipAccountGroup) {
        return vipAccountGroupRepository.save(vipAccountGroup);
    }


    public List<VipAccountGroup> getAll() {
        return vipAccountGroupRepository.findAll();
    }
}
