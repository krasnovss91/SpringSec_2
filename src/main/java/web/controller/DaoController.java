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
public class DaoController {

    UserDaoImpl dao = new UserDaoImpl();
    /*
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        if (dao.getAllUsers().isEmpty() == false) {
            List<String> messages = new ArrayList<>();
            messages.add("Yes, it's work!");
            model.addAttribute("messages", messages);
        }


            return "test";
    }
*/
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String testPage() {
        return "test";
    }
    

}
