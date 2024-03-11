package com.demo.neo4j.controller;

import com.demo.neo4j.entity.User;
import com.demo.neo4j.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    /*
     * ĐĂNG KÝ VÀ KÍCH HOẠT TÀI KHOẢN
     */
    @GetMapping("/account/sign-up")
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("form", user);
        return "account/sign-up";
    }

    @PostMapping("/account/sign-up")
    public String signUp(Model model,
                         @ModelAttribute("form") User user) {
        if (userService.existByUsername(user.getUsername())) {
            model.addAttribute("message", user.getUsername() + " đã được sử dụng!");
        } else {
            String pw = passwordEncoder.encode(user.getPassword());
            user.setPassword(pw);
            userService.createUser(user);
            model.addAttribute("message", "Đăng ký thành công");
            return "forward:/security/login/form";
        }
        return "account/sign-up";
    }



}