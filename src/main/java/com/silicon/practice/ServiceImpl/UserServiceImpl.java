package com.silicon.practice.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicon.practice.model.LoginRequest;
import com.silicon.practice.model.LoginResponse;
import com.silicon.practice.model.User;
import com.silicon.practice.repository.UserRepository;
import com.silicon.practice.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return userRepository.findByUsername(loginRequest.getUsername())
                .filter(user -> passwordMatches(user, loginRequest.getPassword()))
                .map(user -> new LoginResponse(true, "Login successful"))
                .orElseGet(() -> new LoginResponse(false, "Invalid username or password"));
    }

    private boolean passwordMatches(User user, String rawPassword) {
        return user.getPassword().equals(rawPassword);
    }
}
