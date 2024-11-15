package com.shermann.park_api.controller;

import com.shermann.park_api.DTOS.RequestPasswordDTO;
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
    public ResponseEntity<List<ResponseUserDTO>> findAll(){
        List<User> result = userService.findAll();
        return ResponseEntity.ok().body(UserMapper.toListUserDTO(result));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDTO> findById(@PathVariable Long id){
        User result = userService.findById(id);
        return ResponseEntity.ok().body(UserMapper.toResponseUserDTO(result));
    }

    @PostMapping
    public ResponseEntity<ResponseUserDTO> create(@RequestBody RequestUserDTO requestUser){
        User user = UserMapper.toUserRequest(requestUser);

        User result = userService.save(user);

        ResponseUserDTO responseUserDTO = UserMapper.toResponseUserDTO(result);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUserDTO);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDTO> updatePassword(@PathVariable Long id, @RequestBody RequestPasswordDTO passwordDTO){

        User user1 = userService.editPassword(id, passwordDTO.getCurrentPassword(), passwordDTO.getNewPassword(), passwordDTO.getConfirmNewPassword());

        return ResponseEntity.ok().body(UserMapper.toResponseUserDTO(user1));
    }

}
