package com.shermann.park_api.controller;

import com.shermann.park_api.controller.exceptions.ErrorMessage;
import com.shermann.park_api.dto.RequestPasswordDTO;
import com.shermann.park_api.dto.RequestUserDTO;
import com.shermann.park_api.dto.ResponseUserDTO;
import com.shermann.park_api.dto.mapper.UserMapper;
import com.shermann.park_api.models.User;
import com.shermann.park_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "There are all operations like create user, edit password and list all users")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "List all users", description = "List all user in the BD", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully!", content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = ResponseUserDTO.class)))
            })
    })
    public ResponseEntity<List<ResponseUserDTO>> findAll(){
        List<User> result = userService.findAll();
        return ResponseEntity.ok().body(UserMapper.toListUserDTO(result));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find user by ID", description = "Feature to find a user by ID",
            responses = {
                    @ApiResponse(responseCode = "200",description = "User found successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseUserDTO.class))),
                    @ApiResponse(responseCode = "404",description = "The user doesn't exist",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    public ResponseEntity<ResponseUserDTO> findById(@PathVariable Long id){
        User result = userService.findById(id);
        return ResponseEntity.ok().body(UserMapper.toResponseUserDTO(result));
    }

    @PostMapping
    @Operation(summary = "Create user", description = "Feature to create a user",
            responses = {
                @ApiResponse(responseCode = "201",description = "User created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseUserDTO.class))),
                @ApiResponse(responseCode = "409",description = "The user's email is already registered",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                @ApiResponse(responseCode = "422",description = "Invalidated data",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<ResponseUserDTO> create(@RequestBody @Validated RequestUserDTO requestUser){
        User user = UserMapper.toUserRequest(requestUser);

        User result = userService.save(user);

        ResponseUserDTO responseUserDTO = UserMapper.toResponseUserDTO(result);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUserDTO);
    }

    @PatchMapping(value = "/{id}")
    @Operation(summary = "Change password", description = "Feature to change a user password",
            responses = {
                    @ApiResponse(responseCode = "204",description = "The password was changed successfully!",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "404",description = "The current password is wrong!",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400",description = "The passwords are not the same!",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    public ResponseEntity<ResponseUserDTO> updatePassword(@PathVariable Long id, @RequestBody @Validated RequestPasswordDTO passwordDTO){

        User user1 = userService.editPassword(id, passwordDTO.getCurrentPassword(), passwordDTO.getNewPassword(), passwordDTO.getConfirmNewPassword());

        return ResponseEntity.ok().body(UserMapper.toResponseUserDTO(user1));
    }

}
