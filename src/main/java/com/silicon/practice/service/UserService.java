package com.silicon.practice.service;

import com.silicon.practice.model.LoginRequest;
import com.silicon.practice.model.LoginResponse;

public interface UserService {

    LoginResponse login(LoginRequest loginRequest);
}
