package com.controller;

import com.config.HibernateUtil;
import com.dto.Post;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteController {

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Post post = session.get(Post.class, id);
        Transaction transaction = session.beginTransaction();
        session.delete(post);
        transaction.commit();
        session.close();

        return "redirect:/account";
    }
}
