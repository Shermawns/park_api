package com.shermann.park_api.services;

import com.shermann.park_api.models.User;
import com.shermann.park_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found in the system")
        );
    }

    public User save(User user){
        return userRepository.save(user);
    }

}
