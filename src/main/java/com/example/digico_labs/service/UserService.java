package com.example.digico_labs.service;

import com.example.digico_labs.repository.AuthenticationUserRepository;
import com.example.digico_labs.repository.model.LoginRequest;
import com.example.digico_labs.repository.model.RegisterRequest;
import com.example.digico_labs.repository.model.user.AuthenticationUser;
import com.example.digico_labs.repository.model.user.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserService {

    private AuthenticationUserRepository authenticationUserRepository;

    public UserService(AuthenticationUserRepository authenticationUserRepository) {
        this.authenticationUserRepository = authenticationUserRepository;
    }

    public Map<String,Object> logUser(LoginRequest request) {

        Map<String, Object> response = new HashMap<>();
        AuthenticationUser authenticationUser = authenticationUserRepository.findByUserName(request.getUserName());
        String message = "Incorrect credentials";

        if (authenticationUser == null) {
            response.put("message", message);
            response.put("logged",false);

        } else if(!authenticationUser.getPassword().equals(request.getPassword())) {
            response.put("message", message);
            response.put("logged",false);

        } else if (authenticationUser.getUserRole().equals(AuthenticationUser.UserRole.ROLE_ADMIN) && authenticationUser.getPassword().equals(request.getPassword())) {
            response.put("isAdmin", true);
            response.put("logged", true);
            response.put("userId", authenticationUser);
        } else if (authenticationUser.getUserRole().equals(AuthenticationUser.UserRole.ROLE_USER) && authenticationUser.getPassword().equals(request.getPassword())) {
            response.put("isAdmin", false);
            response.put("logged", true);
            response.put("userId", authenticationUser.getId());
        } else {
            response.put("message", message);
            response.put("logged",false);
        }
        return response;
    }

    public Map<String, Object> saveUser(RegisterRequest registerRequest) {
        Map<String, Object> response = new HashMap<>();

        if (authenticationUserRepository.existsByUserName(registerRequest.getUserName())) {
            response.put("message","User name already exist");
        } else if (authenticationUserRepository.existsByEmail(registerRequest.getEmail())) {
            response.put("message","Email already exist");
        } else {
            AuthenticationUser user = new AuthenticationUser();
            user.setEmail(registerRequest.getEmail());
            user.setUserName(registerRequest.getUserName());
            user.setFirstName(registerRequest.getFirstName());
            user.setLastName(registerRequest.getLastName());
            user.setAddress(registerRequest.getAddress());
            user.setNic(registerRequest.getNic());
            user.setPassword(registerRequest.getPassword());
            user.setTelephone(registerRequest.getTelephone());
            user.setOrderList(new ArrayList<>());
            user.setUserRole(AuthenticationUser.UserRole.ROLE_USER);
            user.setStatus(UserStatus.ACTIVE);
            AuthenticationUser savedUser = authenticationUserRepository.save(user);

            response.put("message", "success");
            response.put("isAdmin", false);
            response.put("logged", true);
            response.put("user", savedUser.getId());

        }
        return response;
    }
}
