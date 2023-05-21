package com.iset.produit.controllers;

import com.iset.produit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("login")
        public String saveUser(@RequestParam(name="username") String username,
                               @RequestParam(name="password") String pass)
{
    userService.saveUser(username,pass);
    return "listeProduits";
}

}
