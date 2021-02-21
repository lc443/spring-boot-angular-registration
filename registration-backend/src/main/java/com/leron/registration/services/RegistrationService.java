package com.leron.registration.services;

import com.leron.registration.models.User;
import com.leron.registration.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;


    public User saveUser (User user) {
       User user1 = registrationRepository.save(user);
        return user1;
    }

    public User getUserByEmail(String email) {
        return registrationRepository.findByEmail(email);
    }

    public User getUserByEmailAndPassword(String email, String password) {

        return registrationRepository.findByEmailAndPassword(email, password);

    }



    }
