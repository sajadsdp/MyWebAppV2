package com.comp.mywebappv2.user;

import com.comp.mywebappv2.post.Post;
import com.comp.mywebappv2.post.PostNotFoundExceotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService serviceU;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> ListUsers = serviceU.listAllU();
        model.addAttribute("ListUsers", ListUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle","Add New User");
         return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes raU){
        serviceU.save(user);
        raU.addFlashAttribute("massage","The User Has Been Save Successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id ,Model model ,RedirectAttributes raP){
        try {
            User user = serviceU.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle","Edit User (ID:"+ id +")");
            return "user_form";
        } catch (UserNotFoundException e) {
            raP.addFlashAttribute("massage",e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id ,RedirectAttributes raP){
        try {
            serviceU.delete(id);
            raP.addFlashAttribute("massage","The User Id("+id+") has been deleted ");
        } catch (UserNotFoundException e) {
            raP.addFlashAttribute("massage",e.getMessage());
        }
        return "redirect:/users";
    }
}
