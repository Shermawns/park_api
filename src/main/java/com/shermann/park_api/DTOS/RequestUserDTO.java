package com.shermann.park_api.DTOS;

import com.shermann.park_api.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDTO {

    private String username;
    private String password;
    private Role role;

}
