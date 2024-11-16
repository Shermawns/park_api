package com.shermann.park_api.services;

import com.shermann.park_api.controller.exceptions.EmailUniqueViolationException;
import com.shermann.park_api.controller.exceptions.EntityNotFoundExceptions;
import com.shermann.park_api.controller.exceptions.PasswordInvalidException;
import com.shermann.park_api.models.User;
import com.shermann.park_api.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions(String.format("User %s not found in the system", id))
        );
    }

    public User save(User user){
        try {
            return userRepository.save(user);
        }catch (org.springframework.dao.DataIntegrityViolationException ex){
                throw new EmailUniqueViolationException(String.format("The user %s is already registered", user.getUsername()));
        }
    }


    public User editPassword(Long id, String currentPassword, String newPassword, String confirmNewPassword) {


        User user = findById(id);

        if (!currentPassword.equals(user.getPassword())){
            throw new PasswordInvalidException("Error in authentication !");
        }

        if (!newPassword.equals(confirmNewPassword)){
            throw new PasswordInvalidException("The passwords are not correct");
        }

        user.setPassword(newPassword);

        return save(user);
    }
}
