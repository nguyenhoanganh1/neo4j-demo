package com.demo.neo4j.controller.admin;

import com.demo.neo4j.entity.User;
import com.demo.neo4j.entity.VipAccount;
import com.demo.neo4j.repository.VipAccountRepository;
import com.demo.neo4j.request.UserRequest;
import com.demo.neo4j.request.VipAccountFilter;
import com.demo.neo4j.service.UserService;
import com.demo.neo4j.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/account")
public class AccountAdminController {

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @Autowired
    VipAccountRepository vipAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/paginate/{pageNumber}")
    public String paginate(Model model, @PathVariable("pageNumber") Integer pageNumber) {
        sessionService.set("pageNumber", pageNumber);
        model.addAttribute("edit", false);
        return "forward:/admin/account/index";
    }

    @RequestMapping("/filter")
    public String filter(VipAccountFilter filter) {
        sessionService.set("filter", filter);
        return "forward:/admin/account/paginate/0";
    }

    @RequestMapping("/reset")
    public String reset(Model model) {
        model.addAttribute("edit", true);
        return "forward:/admin/account/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") UUID id) {
        User user = userService.getUserById(id).orElse(null);

        UserRequest item = new UserRequest();
        item.setId(user.getId());
        item.setEmail(user.getEmail());
        item.setPassword(user.getPassword());
        item.setUsername(user.getUsername());

        user.getOwnedVipAccounts().forEach(data -> item.setUsername(data.getVipAccountNumber()));

        model.addAttribute("item", item);
        model.addAttribute("edit", true);
        return this.forward(model);
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("edit", false);
        model.addAttribute("item", new UserRequest());
        return this.forward(model);
    }

    @Transactional
    @RequestMapping("/create")
    public String create(Model model, @ModelAttribute("item") UserRequest item) {
        VipAccount vipAccount = vipAccountRepository.findFirstByVipAccountNumber(item.getVipAccountNumber());

        User user = new User();
        user.setUsername(item.getUsername());
        user.setPassword(passwordEncoder.encode(item.getPassword()));
        user.setEmail(item.getEmail());
        user.setOwnedVipAccounts(List.of(vipAccount));
        userService.createUser(user);

        model.addAttribute("message", "Create successfully!");
        model.addAttribute("edit", true);
        return this.forward(model);
    }

    @RequestMapping("/update")
    public String update(Model model, @ModelAttribute("item") UserRequest item) {
        VipAccount vipAccount = vipAccountRepository.findFirstByVipAccountNumber(item.getVipAccountNumber());
        User user = userService.getUserById(item.getId()).orElse(null);
        user.setUsername(item.getUsername());
        user.setPassword(passwordEncoder.encode(item.getPassword()));
        user.setEmail(item.getEmail());
        user.setOwnedVipAccounts(List.of(vipAccount));
        userService.createUser(user);

        model.addAttribute("message", "Update successfully!");
        model.addAttribute("edit", true);
        return this.forward(model);
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") UUID id) {
        userService.deleteUser(id);
        model.addAttribute("message", "Delete successfully!");
        model.addAttribute("item", new UserRequest());
        model.addAttribute("edit", false);
        return this.forward(model);
    }

    String forward(Model model) {
        Integer pageNumber = sessionService.get("pageNumber", 0);
        Pageable pageable = PageRequest.of(pageNumber, 20);
        Page<User> page = userService.findPageByFilter(pageable);
        model.addAttribute("accounts", this.getVipAccounts());
        model.addAttribute("page", page);
        return "admin/account/index";
    }

    public List<VipAccount> getVipAccounts() {
        return vipAccountRepository.findAll();
    }
}
