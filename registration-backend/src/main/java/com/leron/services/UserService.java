package com.leron.services;

import com.leron.exceptions.CustomerException;
import com.leron.exceptions.UserNotFoundException;
import com.leron.models.User;
import com.leron.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User registration(User user) {
        return userRepository.save(user);
    }


    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User findByUsername(String username) {

        return userRepository.findByUsername(username)
            .orElseThrow(()-> new UserNotFoundException("No user found for this username: " + username));
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(()-> new UserNotFoundException("Could not find user"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("No user found for this email: " + email));
    }

    public User findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("No user found for this id: " + id));
    }

    public User insert(User user ) {
        return userRepository.save(user);
    }

    public User update(int  id) {
    User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Could not find User"));

        if(userRepository.findById(user.getId()).equals(id)){
            user = new User(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find user");
        }

    }

    public void deleteById(int id) {
        userRepository.deleteById(id);

    }
}
