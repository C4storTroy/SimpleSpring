package org.home.accounts.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.home.accounts.dao.AccountDAO;
import org.home.accounts.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {


    private AccountDAO dao;

    @Autowired
    public AccountController(AccountDAO dao){
        this.dao = dao;
    }

    @RequestMapping("form")
    public String form(){
        return "form";
    }


    /**
     *
     * @param account
     * @return a jsp with message account added if is ok
     */
    @RequestMapping("/addAccount")
    public String add(@Valid Account account, BindingResult result){

        if(result.hasErrors()){
            return "form";
        }

        System.out.println("Conta added is " + account.getDescription());

        dao.add(account);

        return "account-added";
    }


    @RequestMapping("/listAccounts")
    public ModelAndView list(){

        List<Account> accounts = dao.list();

        ModelAndView mv = new ModelAndView("account/list");
        mv.addObject("allAccounts", accounts);
        return mv;
    }

    @RequestMapping("/removeAccount")
    public String remove(Account account){

        dao.remove(account);

        return "redirect:listAccounts";
    }

    @RequestMapping("/payAccount")
    public void pay(Account account, HttpServletResponse response){

        dao.pay(account.getId());
        response.setStatus(200);
        //return "redirect:listAccounts";
    }
}
