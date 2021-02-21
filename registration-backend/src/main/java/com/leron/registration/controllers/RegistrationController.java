package com.leron.registration.controllers;

import com.leron.registration.models.User;
import com.leron.registration.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //Controller returns a view (html) vs RestController returns response in form of JSON or Xml.
// If we use Controller we have to add ResponseBody in our method
@RequestMapping
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @PostMapping("/register")
    public User  registerUser(@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();

        if(tempEmail != null && !"".equals(tempEmail)) {
          User userObj =  service.getUserByEmail(tempEmail);
          if(userObj != null) {
              throw new Exception("User with " + tempEmail+ " already exist");
          }
        }
       User savedUser  = null;
        savedUser   = service.saveUser(user);
       return savedUser;
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        String tempPassword = user.getPassword();
            User userObj = null;
        if(tempEmail != null && tempPassword != null) {
            userObj = service.getUserByEmailAndPassword(tempEmail, tempPassword);
        }
        if(userObj == null) {
            throw new Exception("Could not find user with:" + userObj);
        }
        return userObj;
    }



}
