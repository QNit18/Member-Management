package com.qnit18.service;

import com.qnit18.model.User;

import java.util.List;

public interface UserService {

    User getUserProfile(String jwt);

    List<User> getAllUser();
}
