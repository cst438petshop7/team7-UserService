package edu.csumb.cst438.userservice.api;

import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.csumb.cst438.userservice.api.users.Cart;
import edu.csumb.cst438.userservice.api.users.Credit;
import edu.csumb.cst438.userservice.api.users.Password;
import edu.csumb.cst438.userservice.api.users.User;
import edu.csumb.cst438.userservice.api.users.Username;
import edu.csumb.cst438.userservice.business.Manager;

@RestController
public class UserController<User> {
    @Autowired
    Manager manager;

    @GetMapping("/username/{username}")
    @ResponseBody
    List<User> getUsers(@PathVariable String username){
        String check 
        List<User> result = callDB(username);
        return result;
    }

     private List<Hero> callDB (@PathVariable String username) {
         String uri = "http://localhost:8081/username";
         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity<List<User>> result = restTemplate.exchange(uri,
         HttpMethod.GET,null, 
         new ParameterizedTypeReference<List<Hero>>(){});
         return result.getBody();
    }
}