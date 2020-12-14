package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.dao.UserDao;
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

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();

		if (userService.getAllUsers().isEmpty() == false) {
			messages.add("Yes, it's work!");
			model.addAttribute("messages", messages);
		}


		return "hello";
	}



    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}