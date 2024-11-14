package com.shermann.park_api.controller;

import com.shermann.park_api.DTOS.RequestUserDTO;
import com.shermann.park_api.DTOS.ResponseUserDTO;
import com.shermann.park_api.DTOS.mapper.UserMapper;
import com.shermann.park_api.models.User;
import com.shermann.park_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> result = userService.findAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User result = userService.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<ResponseUserDTO> create(@RequestBody RequestUserDTO requestUser){
        User user = UserMapper.toUserRequest(requestUser);

        User result = userService.save(user);

        ResponseUserDTO responseUserDTO = UserMapper.toResponseUserDTO(result);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUserDTO);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User user){
        User user1 = userService.editPassword(id, user.getPassword());
        return ResponseEntity.ok().body(user1);
    }

}
