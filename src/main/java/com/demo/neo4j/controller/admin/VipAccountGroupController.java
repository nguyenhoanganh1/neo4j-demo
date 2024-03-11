package com.demo.neo4j.controller.admin;

import com.demo.neo4j.entity.VipAccountGroup;
import com.demo.neo4j.service.VipAccountGroupService;
import com.demo.neo4j.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * @author anh.nguyen
 * @created 28/02/2024
 */
@RequestMapping("/admin/vip-account-group")
@Controller
public class VipAccountGroupController {

    @Autowired
    SessionService sessionService;

    @Autowired
    private VipAccountGroupService vipAccountGroupService;

    @RequestMapping("/paginate/{pageNumber}")
    public String paginate(Model model, @PathVariable("pageNumber") Integer pageNumber) {
        sessionService.set("pageNumber", pageNumber);
        model.addAttribute("edit", false);
        return "forward:/admin/vip-account-group/index";
    }

    @RequestMapping("/reset")
    public String reset(Model model) {
        model.addAttribute("edit", true);
        return "forward:/admin/vip-account-group/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("edit", false);
        model.addAttribute("item", new VipAccountGroup());
        return this.forward(model);
    }

    @RequestMapping("/create")
    public String create(Model model, @ModelAttribute("item") VipAccountGroup item) {
        vipAccountGroupService.create(item);
        model.addAttribute("message", "Create successfully!");
        model.addAttribute("edit", true);
        return this.forward(model);
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") UUID id) {
        VipAccountGroup item = vipAccountGroupService.getById(id);
        model.addAttribute("item", item);
        model.addAttribute("edit", true);
        return this.forward(model);
    }

    @RequestMapping("/update")
    public String update(Model model, @ModelAttribute("item") VipAccountGroup item) {
        vipAccountGroupService.update(item);
        model.addAttribute("message", "Update successfully!");
        model.addAttribute("edit", true);
        return this.forward(model);
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") UUID id) {
        vipAccountGroupService.delete(id);
        model.addAttribute("message", "Delete successfully!");
        model.addAttribute("item", new VipAccountGroup());
        model.addAttribute("edit", false);
        return this.forward(model);
    }

    String forward(Model model) {
        Integer pageNumber = sessionService.get("pageNumber", 0);
        Pageable pageable = PageRequest.of(pageNumber, 20);
        Page<VipAccountGroup> page = vipAccountGroupService.findPageByFilter(pageable);
        model.addAttribute("page", page);
        return "admin/vip-account-group/index";
    }

}
