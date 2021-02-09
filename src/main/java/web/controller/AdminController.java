package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {

        if (user.getUsername() != null) {
            userService.saveUser(user);
        } else {
            userService.editUser(user);
        }

        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit-user";
    }

    @PostMapping("/edit/admin/edit")
    public String editUser(@ModelAttribute("editUser") User user) {
        userService.editUser(user);
        return "redirect:/admin";
    }

  //  @PostMapping("/delete/{id}")//сделать удаление без переменной. С ней метод Post работать не может
  //  public String deleteUser(@PathVariable("id") long id) {
    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute User user){
        long id = user.getId();
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
