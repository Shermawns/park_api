package com.shermann.park_api.DTOS.mapper;

import com.shermann.park_api.DTOS.RequestPasswordDTO;
import com.shermann.park_api.DTOS.RequestUserDTO;
import com.shermann.park_api.DTOS.ResponseUserDTO;
import com.shermann.park_api.models.User;
import com.shermann.park_api.models.enums.Role;

public class UserMapper {
    public static User toUserRequest(RequestUserDTO requestUserDTO){
        User user = new User();
        user.setUsername(requestUserDTO.getUsername());
        user.setPassword(requestUserDTO.getPassword());

        if (requestUserDTO.getRole() != null){
            user.setRole(Role.ADMIN);
        }

        return user;
    }

    public static ResponseUserDTO toResponseUserDTO(User user){
        ResponseUserDTO userResponse = new ResponseUserDTO();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setRole(user.getRole());

        return userResponse;
    }

    public static User newPasswordDTO(RequestPasswordDTO requestPasswordDTO){
        User user = new User();
        user.setPassword(requestPasswordDTO.getNewPassword());

        user.setPassword(requestPasswordDTO.getConfirmNewPassword());

        if (user.getPassword() != requestPasswordDTO.getConfirmNewPassword()){
            new Exception("WRONG PASSWORD");
        }

        return user;
    }
}
