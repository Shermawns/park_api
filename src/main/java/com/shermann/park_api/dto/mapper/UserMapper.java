package com.shermann.park_api.dto.mapper;

import com.shermann.park_api.dto.RequestPasswordDTO;
import com.shermann.park_api.dto.RequestUserDTO;
import com.shermann.park_api.dto.ResponseUserDTO;
import com.shermann.park_api.models.UserModel;
import com.shermann.park_api.models.Role;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserModel toUserRequest(RequestUserDTO requestUserDTO){
        UserModel userModel = new UserModel();
        userModel.setUsername(requestUserDTO.getUsername());
        userModel.setPassword(requestUserDTO.getPassword());

        if (requestUserDTO.getRole() != null){
            userModel.setRole(Role.ADMIN);
        }

        return userModel;
    }

    public static List<ResponseUserDTO> toListUserDTO (List<UserModel> userModels){
        List<ResponseUserDTO> dtos = new ArrayList<>();
        for (UserModel x : userModels){
            ResponseUserDTO userDTO = new ResponseUserDTO(x.getId(), x.getUsername(), x.getRole());
            dtos.add(userDTO);
        }
        return dtos;
    }

    public static ResponseUserDTO toResponseUserDTO(UserModel userModel){
        ResponseUserDTO userResponse = new ResponseUserDTO();
        userResponse.setId(userModel.getId());
        userResponse.setUsername(userModel.getUsername());
        userResponse.setRole(userModel.getRole());

        return userResponse;
    }

    public static UserModel newPasswordDTO(RequestPasswordDTO requestPasswordDTO){
        UserModel userModel = new UserModel();
        userModel.setPassword(requestPasswordDTO.getNewPassword());

        userModel.setPassword(requestPasswordDTO.getConfirmNewPassword());

        if (userModel.getPassword() != requestPasswordDTO.getConfirmNewPassword()){
            new Exception("WRONG PASSWORD");
        }

        return userModel;
    }
}
