package com.test.jump;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hi sssss1112";
    }

}
