package com.leron.registration.repositories;

import com.leron.registration.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RegistrationRepository extends JpaRepository<User, IncompatibleClassChangeError> {


   public User findByEmail(String email);
   public User findByEmailAndPassword(String email, String password);
}
