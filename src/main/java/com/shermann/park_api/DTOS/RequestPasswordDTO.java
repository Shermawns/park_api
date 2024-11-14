package com.shermann.park_api.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RequestPasswordDTO {

    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;

}
