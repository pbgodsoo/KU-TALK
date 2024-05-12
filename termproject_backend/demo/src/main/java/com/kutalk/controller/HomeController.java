package com.kutalk.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping(value="/")
    public String home(Model model){
        model.addAttribute("test", "안녕하세요!");
        return "index";

    }    
}
