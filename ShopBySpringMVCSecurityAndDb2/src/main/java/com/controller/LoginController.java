package com.controller;

import com.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @GetMapping("login")
    private String getLogin(@ModelAttribute("user") User user) {

        return "login";

    }
}
