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

    @RequestMapping(path="/login/{username}", method=RequestMethod.POST)
    @ResponseBody
    ResponseEntity<String> getUser(@PathVariable String username, @RequestBody User user){
        User result = callUserDB(username);
        if(result==null){
                return new ResponseEntity<String>("result is null",HttpStatus.NOT_FOUND);
        }
        if(result.getUsername().getUsername() == user.getUsername().getUsername() && result.getPassword().getPassword() == user.getPassword().getPassword()){
            return new ResponseEntity<String> ("Username and password found",HttpStatus.OK);
        }
        return new ResponseEntity<String>("result ain't null but never found"+result.getUsername().getUsername() + " " + user.getUsername().getUsername() + " " + result.getPassword().getPassword() + " " + user.getPassword().getPassword(),HttpStatus.NOT_FOUND);
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
