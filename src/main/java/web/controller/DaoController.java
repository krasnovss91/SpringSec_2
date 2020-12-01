package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class DaoController {
 private UserDao userDao;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String users(ModelMap model){
        if(userDao.getAllUsers()!=null){
            List<String> messages = new ArrayList<>();
            messages.add("This is DAO Test!");
            model.addAttribute("messages", messages);
        }
        return "test";
    }
}
