package com.comp.mywebappv2.post;

import com.comp.mywebappv2.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PostController {
    @Autowired private PostService serviceP;

    @GetMapping("/posts")
    public String showPostList(Model model){
        List<Post> ListPosts = serviceP.listAllP();
        model.addAttribute("ListPosts",ListPosts);
        return "allPosts";
    }

    @GetMapping("/posts/new/{id}")
    public String showNewPost(Model model, @PathVariable("id") Integer uid){
        model.addAttribute("post", new Post());
        model.addAttribute("pageTitle","Add Post For User (ID:"+ uid +")");
        model.addAttribute("userId",uid);
        return "post_form";
    }

    @PostMapping("/posts/save")
    public String savePost(Post post, RedirectAttributes raP){
        serviceP.save(post);
        raP.addFlashAttribute("massage","The Post Has Been Save Successfully");
        return "redirect:/users";
    }

    @GetMapping("/posts/{id}")
    public String showPostUser(Model model, @PathVariable("id") Integer uid){
        List<Post> ListPosts = serviceP.listAllP();
        model.addAttribute("ListPosts",ListPosts);
        model.addAttribute("userId",uid);
        return "posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id ,Model model ,RedirectAttributes raP){
        try {
            Post post = serviceP.get(id);
            model.addAttribute("post",post);
            model.addAttribute("pageTitle","edit post (ID:"+ id +")");
            return "post_form";
        } catch (PostNotFoundExceotion e) {
            raP.addFlashAttribute("massage","The Post Has Been Save Successfully");
            return "redirect:/users";
        }
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable("id") Integer id ,RedirectAttributes raP) {
        try {
            serviceP.delete(id);
            raP.addFlashAttribute("massage","The Post Id("+id+") has been deleted ");
        } catch (PostNotFoundExceotion e) {
            raP.addFlashAttribute("massage",e.getMessage());
        }
        return "redirect:/posts/{id}";
    }
}
