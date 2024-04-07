package com.controller;

import com.dao.PostDao;
import com.dto.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AccountController {

    PostDao postDao;

    public AccountController(PostDao postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/account")
    public ModelAndView getAccount() {
        List<Post> currentUserPosts = postDao.getCurrentUserPosts();

        ModelAndView modelAndView = new ModelAndView("account");
        modelAndView.addObject("post", currentUserPosts);

        return modelAndView;
    }

}
