package com.example.demo.controller;


import com.example.demo.infra.security.TokenService;
import com.example.demo.repository.UserRepository;
import com.example.demo.users.AuthenticationDto;
import com.example.demo.users.LoginResponseDTO;
import com.example.demo.users.RegisterDto;
import com.example.demo.users.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Users) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto dto) {
        if (this.userRepository.findByLogin(dto.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());
        Users newUser = new Users(dto.login(), encryptedPassword, dto.role());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/loginredirect")
    public String showLoginForm() {
        return "login";
    }
}

