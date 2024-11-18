package com.shermann.park_api.services;

import com.shermann.park_api.controller.exceptions.EmailUniqueViolationException;
import com.shermann.park_api.controller.exceptions.EntityNotFoundExceptions;
import com.shermann.park_api.controller.exceptions.PasswordInvalidException;
import com.shermann.park_api.models.UserModel;
import com.shermann.park_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public UserModel findById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions(String.format("User %s not found in the system", id))
        );
    }

    public UserModel save(UserModel userModel){
        try {
            return userRepository.save(userModel);
        }catch (org.springframework.dao.DataIntegrityViolationException ex){
                throw new EmailUniqueViolationException(String.format("The user %s is already registered", userModel.getUsername()));
        }
    }


    public UserModel editPassword(Long id, String currentPassword, String newPassword, String confirmNewPassword) {


        UserModel userModel = findById(id);

        if (!currentPassword.equals(userModel.getPassword())){
            throw new PasswordInvalidException("Error in authentication !");
        }

        if (!newPassword.equals(confirmNewPassword)){
            throw new PasswordInvalidException("The passwords are not correct");
        }

        userModel.setPassword(newPassword);

        return save(userModel);
    }
}
