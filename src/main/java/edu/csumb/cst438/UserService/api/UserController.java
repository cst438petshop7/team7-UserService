package edu.csumb.cst438.userservice.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.csumb.cst438.userservice.business.Manager;

@RestController
public class UserController<User> {
    @Autowired
    Manager manager;

    @GetMapping("/users")
    @ResponseBody
    List<User> getUsers(){
        return null;
    }
}