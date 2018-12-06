package edu.csumb.cst438.userservice.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.csumb.cst438.userservice.api.users.User;
import edu.csumb.cst438.userservice.business.Manager;

@CrossOrigin("*")
@RestController
public class UserController {
    @Autowired
    Manager manager;

    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    ResponseEntity<User> getUser(@RequestBody User user){
        User result = callUserDB(user.getUsername().getUsername());
        if(result==null){
                return new ResponseEntity<User>(result,HttpStatus.NOT_FOUND);
        }
        if(result.getUsername().getUsername().equals(user.getUsername().getUsername()) && result.getPassword().getPassword().equals(user.getPassword().getPassword())){
            return new ResponseEntity<User> (result,HttpStatus.OK);
        }
        return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
    }

     private User callUserDB (String name) {
         String uri = "https://shopdb-service.herokuapp.com/username/"+name;
         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity<User> user = restTemplate.exchange(uri,
         HttpMethod.GET,null, 
         new ParameterizedTypeReference<User>(){});
         return user.getBody();
    }
}
