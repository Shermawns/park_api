package com.shermann.park_api.DTOS.mapper;

import com.shermann.park_api.DTOS.RequestUserDTO;
import com.shermann.park_api.DTOS.ResponseUserDTO;
import com.shermann.park_api.models.User;
import com.shermann.park_api.models.enums.Role;

public class UserMapper {
    public static User toUserRequest(RequestUserDTO requestUserDTO){
        User user = new User();
        user.setUsername(requestUserDTO.getUsername());
        user.setPassword(requestUserDTO.getPassword());
        return user;
    }

    public static ResponseUserDTO toResponseUserDTO(User user){
        ResponseUserDTO userResponse = new ResponseUserDTO();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setRole(user.getRole());

        return userResponse;
    }
}