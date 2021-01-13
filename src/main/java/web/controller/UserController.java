package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping(value = "hello")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();

        if (userService.getAllUsers().isEmpty() == false) {
            messages.add("Yes, it's work!");
            model.addAttribute("messages", messages);
        }

        return "hello";
    }

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/user")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user";
    }

    @GetMapping("/admin")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("admin/add")
    public String addUser(@ModelAttribute User user) {

        if (user.getUsername() != null) {
            userService.saveUser(user);
        } else {
            userService.editUser(user);
        }

        return "redirect:/admin";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.getAllUsers());
        return "register";
    }

    @PostMapping("admin/register")
    public String registerUser(@ModelAttribute User user) {
        if (user.getUsername() != null) {
            userService.saveUser(user);
        } else {
            userService.editUser(user);
        }

        return "redirect:/admin";
    }

    @GetMapping("admin/edit/{username}")
    public String editUser(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findUserByName(username));
        return "edit-user";
    }

    @PostMapping("admin/edit/admin/edit")
    public String editUser(@ModelAttribute("editUser") User user) {
        userService.editUser(user);

        return "redirect:/admin";
    }

    @RequestMapping("admin/delete/{username}")//так будет работать только с GET. без имени пользователя не работает
    public String deleteUser(@PathVariable("username") String username) {
   // @DeleteMapping("admin/delete")
    //public String deleteUser(@ModelAttribute("deleteUser") User user){
        userService.deleteUser(username);
      //  userService.deleteUser(user);

        return "redirect:/admin";
    }
}