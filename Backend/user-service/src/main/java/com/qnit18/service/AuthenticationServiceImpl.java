package com.qnit18.service;


import com.qnit18.config.JwtProvider;
import com.qnit18.dto.request.LoginRequest;
import com.qnit18.dto.response.AuthResponse;
import com.qnit18.exception.AppException;
import com.qnit18.exception.ErrorCode;
import com.qnit18.model.User;
import com.qnit18.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    CustomerUserServiceImplementation customerUserDetails;

    @Override
    public AuthResponse authenticate(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String role = user.getRole();

        User isEmailExist = userRepository.findByEmail(email);

        if (isEmailExist!=null){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullName(fullName);
        createdUser.setRole(role);

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Success");
        authResponse.setStatus(true);

        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        String username = request.getEmail();
        String password = request.getPassword();

//        System.out.println(username + " " + password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login success");
        authResponse.setJwt(token);
        authResponse.setStatus(true);
        return authResponse;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetails.loadUserByUsername(username);

        System.out.println("Sign in userDetails: " + userDetails);

        if (userDetails == null){
//            System.out.println("Sign in userDetails - null : " + userDetails);
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
//            System.out.println("Sign in userDetails - password not mactch : " + userDetails);
            throw new AppException(ErrorCode.ERROR_PASSWORD);
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
