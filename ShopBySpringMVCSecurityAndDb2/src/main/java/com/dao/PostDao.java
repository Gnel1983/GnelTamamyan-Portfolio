package com.dao;

import com.config.HibernateUtil;
import com.dto.Post;
import com.dto.User;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostDao {


    UserDetails userDetails;

    UserDao userDao;

    public PostDao( UserDao userDao) {

        this.userDao = userDao;
    }

    public List<Post> getAllPosts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Post> postsList = session.createQuery("from Post", Post.class).getResultList();

        return postsList;
    }

    public List<Post> getCurrentUserPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (null != authentication && authentication.getPrincipal() instanceof UserDetails) {
             userDetails = (UserDetails) authentication.getPrincipal();
        }

        String username = userDetails.getUsername();
        User userFromDb = userDao.getUserFromDb(username);

        List<Post> postsList = userFromDb.getPostsList();

        return postsList;


    }

}
