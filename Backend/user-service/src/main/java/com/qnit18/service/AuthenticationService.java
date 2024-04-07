package com.qnit18.service;

import com.qnit18.dto.request.LoginRequest;
import com.qnit18.dto.response.AuthResponse;
import com.qnit18.model.User;

public interface AuthenticationService {

    AuthResponse authenticate(User user);

    AuthResponse login(LoginRequest loginRequest);
}
