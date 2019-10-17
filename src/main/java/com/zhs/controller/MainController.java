package com.zhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/16 15:58
 * @Description:
 * @version: 1.0
 */

@Controller
public class MainController {

    @RequestMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/user/index")
    public String userIndex(){
        return "/user/index";
    }

    @RequestMapping("/login")
    public String login(){
       return "login";
    }

    @RequestMapping("/login-error")
    public String  loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }

    @GetMapping("/401")
    public String accessDenied(){
        return "401";
    }
}
