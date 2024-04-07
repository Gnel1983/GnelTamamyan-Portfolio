package com.dao;


import com.config.HibernateUtil;
import com.dto.User;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class UserDao {
    UserDetails userDetails;

    public User getUserFromDb(String username) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        User customUser = (User) session.createQuery("from User U where U.username= :username").setParameter("username", username).uniqueResult();

        return customUser;

    }


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && authentication.getPrincipal() instanceof UserDetails) {
            userDetails = (UserDetails) authentication.getPrincipal();
        }

        String username = userDetails.getUsername();

        return getUserFromDb(username);
    }
}
