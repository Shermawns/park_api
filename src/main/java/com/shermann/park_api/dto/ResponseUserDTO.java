package com.shermann.park_api.dto;

import com.shermann.park_api.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {

    private Long id;
    private String username;
    private UserModel.Role role;

}
