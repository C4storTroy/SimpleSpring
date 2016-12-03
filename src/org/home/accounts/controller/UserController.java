package org.home.accounts.controller;

import javax.servlet.http.HttpSession;

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
    public String executeLogin(User user, HttpSession session){

        UserDAO userDAO = new UserDAO();

        if(userDAO.existUsuario(user)){
            session.setAttribute("userauth", user);
            return "menu";
        }

        return "redirect:loginForm";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:loginForm";
    }
}
