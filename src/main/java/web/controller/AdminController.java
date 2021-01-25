package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {

        this.userService = userService;
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
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        if (user.getUsername() != null) { //доставать по id, не по имени!
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

    @RequestMapping("admin/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return "redirect:/admin";
    }
}
