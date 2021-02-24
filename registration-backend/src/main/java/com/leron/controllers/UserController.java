package com.leron.controllers;

import com.leron.exceptions.UserNotFoundException;
import com.leron.models.User;
import com.leron.repositories.UserRepository;
import com.leron.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private HttpServletRequest req;


    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.insert(user));
    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user ) {
        String tempUsername = user.getUsername();
        String tempPassword = user.getPassword();
        User userObj = null;

        if(tempUsername != null && tempPassword != null) {
            userObj = userService.findByUsernameAndPassword(tempUsername, tempPassword);
        }
        if (userObj == null) {

            throw new UserNotFoundException("user not found");
        }
        HttpSession session = req.getSession();
        session.setAttribute("currentUser", userObj);
        return ResponseEntity.ok(userObj);

    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(){
        HttpSession session = req.getSession();
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(this.userService.findByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(this.userService.findByEmail(email));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody User newUser){
         User user = userService.findById(id);

            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());

            userService.insert(user);
            return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") int id) {
        User user = userService.findById(id);

        if(userService.findById(user.getId()).equals(id)){
            userRepository.delete(user);
        }
        return ResponseEntity.ok().build();


    }

}
