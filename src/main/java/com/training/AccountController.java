package com.training;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping("/api/status")
    public String getStatus() {
        return "Telecom Service is up and running.";
    }

    @GetMapping("/hello")
  public String sayHello(){
    return "hellop devops";
  }
}