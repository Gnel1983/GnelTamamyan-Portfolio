package com.controller;

import com.config.HibernateUtil;
import com.dao.UserDao;
import com.dto.Authority;
import com.dto.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class SignupController {

    PasswordEncoder passwordEncoder;

    UserDao userDao;

    public SignupController(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @GetMapping("/signup")
    public String getSignup(@ModelAttribute("user") User user) {
        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(@ModelAttribute("user") User user) {

        String username = user.getUsername();
        String password = user.getPassword();

        User userFromDb = userDao.getUserFromDb(username);

        if (null != userFromDb) {
            return "redirect:/signup?error";
        }

        User myUser = new User();
        myUser.setUsername(username);
        myUser.setPassword(passwordEncoder.encode(password));
        myUser.setEnabled(true);
        Authority authority = new Authority();
        authority.setUser(myUser);
        authority.setAuthority("ROLE_USER");
        authority.setUsername(username);
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(authority);
        myUser.setAuthorities(authorityList);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(myUser);
        transaction.commit();
        session.close();

            return "redirect:/login";
    }
}
