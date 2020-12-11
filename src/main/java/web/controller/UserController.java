package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.dao.UserDaoImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
	UserDaoImpl dao;

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
     /*
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		 */
		if (dao.getAllUsers().isEmpty() == false) {
			messages.add("Yes, it's work!");
			model.addAttribute("messages", messages);
		}


		return "hello";
	}


/*
	@RequestMapping(value = "test", method = RequestMethod.GET)

	public String testDao(ModelMap model) {

		if (dao.getAllUsers().isEmpty() == false) {
			List<String> messages = new ArrayList<>();
			messages.add("Yes, it's work!");
			model.addAttribute("messages", messages);
		}
		return "test";
	}
*/
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}