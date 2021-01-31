package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.RoleService;

@Controller
@RequestMapping("/")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping("/roles/{name}")
    public String getRoleByName(@PathVariable("name") String name){
        roleService.findRoleByName(name);
        return "redirect:/admin";
    }
}
