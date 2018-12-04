package edu.csumb.cst438.userservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.csumb.cst438.userservice.api.users.User;
import edu.csumb.cst438.userservice.business.Manager;

@RestController
public class UserController {
    @Autowired
    Manager manager;

    @GetMapping("/users/{username}")
    @ResponseBody
    Boolean getUsers(@PathVariable String username){
        User result = callDB(username);
        if(result!=null){
            if(result.getUsername().getUsername()==username){return true;}
            else{return false;}
        }
        return false;
    }

     private User callDB (String username) {
         String uri = "https://shopdb-service.herokuapp.com/username/" + username;
         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity<User> result = restTemplate.exchange(uri,
         HttpMethod.GET,null, 
         new ParameterizedTypeReference<User>(){});
         return result.getBody();
    }
}