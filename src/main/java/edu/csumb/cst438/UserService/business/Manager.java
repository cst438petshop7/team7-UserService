package edu.csumb.cst438.userservice.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csumb.cst438.userservice.api.users.User;
import edu.csumb.cst438.userservice.data.UserDBClient;

@Service
public class Manager{
    @Autowired
    UserDBClient userDBClient;
    public List<User> getUserList(){
        return null;
    }
}