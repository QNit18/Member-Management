package com.qnit18.service;

import com.qnit18.config.JwtProvider;
import com.qnit18.exception.AppException;
import com.qnit18.exception.ErrorCode;
import com.qnit18.model.User;
import com.qnit18.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserProfile(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        if (email == null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
