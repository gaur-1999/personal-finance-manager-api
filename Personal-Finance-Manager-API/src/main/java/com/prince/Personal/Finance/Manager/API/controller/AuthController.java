package com.prince.Personal.Finance.Manager.API.controller;

import com.prince.Personal.Finance.Manager.API.dto.LoginRequest;
import com.prince.Personal.Finance.Manager.API.dto.RegisterRequest;
import com.prince.Personal.Finance.Manager.API.model.User;
import com.prince.Personal.Finance.Manager.API.security.JwtUtil;
import com.prince.Personal.Finance.Manager.API.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        User user=userService.registerUser(request);
        return ResponseEntity.ok("User register Successfully: "+user.getName());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        String token= jwtUtil.generateToken(request.getEmail());
        return ResponseEntity.ok(token);
    }
}
