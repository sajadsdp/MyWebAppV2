package com.comp.mywebappv2.boostpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Boostcontroller {
    @GetMapping("/b")
    public String boostrap(){
        return "boostrap/boostrap1";
    }
}
