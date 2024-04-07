package com.controller;

import com.config.HibernateUtil;
import com.dao.UserDao;
import com.dto.Post;
import com.dto.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddPostController {

    UserDao userDao;

    public AddPostController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/add")
    public String getAddPost(@ModelAttribute("post") Post post) {
        return "addPost";

    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute("post") Post post) {
        String title = post.getTitle();
        String imgUrl = post.getImgUrl();

        User currentUser = userDao.getCurrentUser();
        Post myPost = new Post();
        myPost.setUser(currentUser);
        myPost.setTitle(title);
        myPost.setImgUrl(imgUrl);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(myPost);
        transaction.commit();
        session.close();

             return "redirect:/";
    }


}
