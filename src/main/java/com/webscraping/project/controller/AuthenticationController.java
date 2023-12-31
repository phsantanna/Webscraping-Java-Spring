package com.webscraping.project.controller;


import com.webscraping.project.infra.security.TokenService;
import com.webscraping.project.users.AuthenticationDto;
import com.webscraping.project.users.LoginResponseDTO;
import com.webscraping.project.users.Users;
import com.webscraping.project.repository.UserRepository;
import com.webscraping.project.users.RegisterDto;
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

