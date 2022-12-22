package com.example.controller;

import com.example.jwtutil.JwtUtil;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.responsehandler.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> loginRequest) {


        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty() || !password.equals(user.get().getPassword())) {
            return ResponseEntity.badRequest().build();
        }
//        org.springframework.http.HttpRequest
        String token = jwtUtil.generateToken(username);
        return ResponseHandler.generateResponse(token, HttpStatus.OK);
    }

}