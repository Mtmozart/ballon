package br.com.ballon.application.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ClientController {

    @GetMapping
    public String consumerController(){
        return "online";
    }
}
