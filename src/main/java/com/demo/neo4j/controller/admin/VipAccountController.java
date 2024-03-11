package com.demo.neo4j.controller.admin;

import com.demo.neo4j.entity.JoinGroup;
import com.demo.neo4j.entity.VipAccount;
import com.demo.neo4j.entity.VipAccountGroup;
import com.demo.neo4j.repository.JoinGroupRepository;
import com.demo.neo4j.request.VipAccountFilter;
import com.demo.neo4j.request.VipAccountRequest;
import com.demo.neo4j.service.VipAccountGroupService;
import com.demo.neo4j.service.VipAccountService;
import com.demo.neo4j.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/vip-account")
public class VipAccountController {
    @Autowired
    VipAccountService vipAccountService;

    @Autowired
    VipAccountGroupService vipAccountGroupService;

    @Autowired
    JoinGroupRepository joinGroupRepository;

    @Autowired
    SessionService sessionService;


    @RequestMapping("/paginate/{pageNumber}")
    public String paginate(Model model, @PathVariable("pageNumber") Integer pageNumber) {
        sessionService.set("pageNumber", pageNumber);
        model.addAttribute("edit", false);
        return "forward:/admin/vip-account/index";
    }

    @RequestMapping("/filter")
    public String filter(VipAccountFilter filter) {
        sessionService.set("filter", filter);
        return "forward:/admin/vip-account/paginate/0";
    }

    @RequestMapping("/reset")
    public String reset(Model model) {
        model.addAttribute("edit", true);
        return "forward:/admin/vip-account/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("edit", false);
        model.addAttribute("item", new VipAccountRequest());
        model.addAttribute("groups", this.getVipAccountGroups());
        return this.forward(model);
    }

    @Transactional
    @RequestMapping("/create")
    public String create(Model model, @ModelAttribute("item") VipAccountRequest item) {
        VipAccountGroup vipAccountGroup = vipAccountGroupService.getById(item.getGroupId());

        JoinGroup joinGroup = new JoinGroup();
        joinGroup.setSince(LocalDateTime.now());
        joinGroup.setVipAccountGroup(vipAccountGroup);
        joinGroup = joinGroupRepository.save(joinGroup);

        VipAccount vipAccount = new VipAccount();
        vipAccount.setVipAccountNumber(item.getVipAccountNumber());
        vipAccount.setJoinGroups(List.of(joinGroup));
        vipAccountService.createVipAccount(vipAccount);

        model.addAttribute("message", "Create successfully!");
        model.addAttribute("edit", true);
        return this.forward(model);
    }

    @RequestMapping("/update")
    public String update(Model model, @ModelAttribute("item") VipAccountRequest item) {
        VipAccountGroup vipAccountGroup = vipAccountGroupService.getById(item.getGroupId());

        JoinGroup joinGroup = new JoinGroup();
        joinGroup.setSince(LocalDateTime.now());
        joinGroup.setVipAccountGroup(vipAccountGroup);

        VipAccount vipAccount = new VipAccount();
        vipAccount.setVipAccountNumber(item.getVipAccountNumber());
        vipAccount.getJoinGroups().add(joinGroup);

        joinGroupRepository.save(joinGroup);

        model.addAttribute("message", "Update successfully!");
        model.addAttribute("edit", true);
        return this.forward(model);
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") UUID id) {
        vipAccountService.deleteVipAccount(id);
        model.addAttribute("message", "Delete successfully!");
        model.addAttribute("item", new VipAccount());
        model.addAttribute("edit", false);
        return this.forward(model);
    }

    @RequestMapping("/recommend")
    public String recommendVipAccountNumber(Model model, @ModelAttribute("filter") VipAccountFilter filter) {
        Integer pageNumber = sessionService.get("pageNumber", 0);
        Pageable pageable = PageRequest.of(pageNumber, 20);
        List<VipAccount> vipAccounts = vipAccountService.recommendVipAccountNumber(filter.getVipAccountNumber());
        Page<VipAccount> page = new PageImpl<>(vipAccounts, pageable, 100);
        model.addAttribute("page", page);
        model.addAttribute("filter", filter);
        model.addAttribute("edit", false);
        model.addAttribute("item", new VipAccountRequest());
        model.addAttribute("groups", this.getVipAccountGroups());
        return "admin/vip-account/index";
    }

    String forward(Model model) {
        VipAccountFilter filter = sessionService.get("filter", new VipAccountFilter());
        model.addAttribute("filter", filter);

        Integer pageNumber = sessionService.get("pageNumber", 0);
        Pageable pageable = PageRequest.of(pageNumber, 20);
        Page<VipAccount> page = vipAccountService.findPageByFilter(pageable);
        model.addAttribute("page", page);
        return "admin/vip-account/index";
    }

    public List<VipAccountGroup> getVipAccountGroups() {
        return vipAccountGroupService.getAll();
    }
}