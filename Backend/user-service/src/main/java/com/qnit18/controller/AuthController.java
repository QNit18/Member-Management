package com.qnit18.controller;

import com.qnit18.config.JwtProvider;
import com.qnit18.dto.request.LoginRequest;
import com.qnit18.dto.response.AuthResponse;
import com.qnit18.exception.AppException;
import com.qnit18.exception.ErrorCode;
import com.qnit18.model.User;
import com.qnit18.repository.UserRepository;
import com.qnit18.service.AuthenticationService;
import com.qnit18.service.AuthenticationServiceImpl;
import com.qnit18.service.CustomerUserServiceImplementation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthenticationServiceImpl authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(
            @RequestBody User user) throws Exception {
        AuthResponse authResponse = authenticationService.authenticate(user);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> sigin(@RequestBody LoginRequest request){
        AuthResponse authResponse = authenticationService.login(request);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
