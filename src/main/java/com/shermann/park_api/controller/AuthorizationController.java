package com.shermann.park_api.controller;

import com.shermann.park_api.controller.exceptions.ErrorMessage;
import com.shermann.park_api.dto.UserLoginDTO;
import com.shermann.park_api.jwt.JwtToken;
import com.shermann.park_api.jwt.JwtUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class AuthorizationController {

    @Autowired
    private JwtUserDetailsService detailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody @Validated UserLoginDTO userLoginDTO, HttpServletRequest request){
        try{
            UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
            authenticationManager.authenticate(passwordAuthenticationToken);
            JwtToken token = detailsService.getTokenAuthenticate(userLoginDTO.getUsername());
            return ResponseEntity.ok().body(token);
        }
        catch (AuthenticationException ex){
            log.warn("Bad credentials from username '{}'",userLoginDTO.getUsername());
        }
        return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Invalid credentials"));
    }
}
