package org.home.accounts.controller;

import org.home.accounts.dao.UserDAO;
import org.home.accounts.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/loginForm")
    public String form(){
        return "user/login";
    }

    @RequestMapping("/executeLogin")
    public String executeLogin(User user){

        UserDAO userDAO = new UserDAO();
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        if(userDAO.existUsuario(user)){
            return "menu";
        }

        return "redirect:loginForm";
    }
}
