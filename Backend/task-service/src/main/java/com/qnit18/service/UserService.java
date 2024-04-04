package com.qnit18.service;

import com.qnit18.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE", url = "http://localhost:5001")
public interface UserService {

    @GetMapping("/api/users/profile")
    UserDto getUserProfile(@RequestHeader("Authorization") String jwt);
}
