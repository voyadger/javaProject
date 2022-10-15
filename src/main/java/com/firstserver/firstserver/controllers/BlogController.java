package com.firstserver.firstserver.controllers;

import com.firstserver.firstserver.models.Post;
import com.firstserver.firstserver.models.User;
import com.firstserver.firstserver.repo.PostRepository;
import com.firstserver.firstserver.repo.UserRepository;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/")
    public String blog(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        try {
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }

            User user = userRepository.findByUsername(username);
            model.addAttribute("userid", user.getId());
        }catch (Exception e){
            model.addAttribute("userid", -1);
        }


        return "blogs";
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable(value = "id") long id, Model model){
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "post";
    }

    @GetMapping("/user/{id}")
    public String getUserPosts(@PathVariable(value = "id") long id, Model model){

        List<Post> posts = postRepository.findByUserId(id);

        ArrayList<Post> res = new ArrayList<>();
        model.addAttribute("posts", posts);

        return "blogs";
    }
}
