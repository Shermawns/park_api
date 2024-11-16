package com.shermann.park_api.dto.mapper;

import com.shermann.park_api.dto.RequestPasswordDTO;
import com.shermann.park_api.dto.RequestUserDTO;
import com.shermann.park_api.dto.ResponseUserDTO;
import com.shermann.park_api.models.User;
import com.shermann.park_api.models.enums.Role;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ResponseUserDTO> toListUserDTO (List<User> users){
        List<ResponseUserDTO> dtos = new ArrayList<>();
        for (User x : users){
            ResponseUserDTO userDTO = new ResponseUserDTO(x.getId(), x.getUsername(), x.getRole());
            dtos.add(userDTO);
        }
        return dtos;
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
