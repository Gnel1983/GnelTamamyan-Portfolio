package com.controller;

import com.dao.PostDao;
import com.dto.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    PostDao postDao;

    public HomeController(PostDao postDao) {
        this.postDao = postDao;
    }

    @GetMapping
    public ModelAndView getHome() {

        List<Post> allPosts = postDao.getAllPosts();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("post",allPosts);

        return modelAndView;
    }



}
