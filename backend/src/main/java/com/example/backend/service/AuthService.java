package com.example.backend.service;

import com.example.backend.DTO.auth.AuthRequest;
import com.example.backend.DTO.auth.AuthResponse;
import com.example.backend.DTO.user.UserDTO;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(AuthRequest request) {

        if (userRepository.findByLogin(request.getLogin()).isPresent()) {
            return new AuthResponse(
                    409,
                    "User already exists"
            );
        }

        User user = new User();

        user.setLogin(request.getLogin());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);

        String token = jwtService.generateToken(user.getLogin());

        UserDTO userDto = new UserDTO(
                user.getId(),
                user.getLogin()
        );

        return new AuthResponse(
                201,
                "Created",
                userDto,
                token
        );
    }

    public AuthResponse login(AuthRequest request) {

        User user = userRepository
                .findByLogin(request.getLogin())
                .orElse(null);

        if (user == null) {
            return new AuthResponse(
                    401,
                    "Unauthorized"
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            return new AuthResponse(
                    403,
                    "Incorrect password"
            );
        }

        String token = jwtService.generateToken(
                user.getLogin()
        );

        UserDTO userDto = new UserDTO(
                user.getId(),
                user.getLogin()
        );

        return new AuthResponse(
                200,
                "OK",
                userDto,
                token
        );
    }
}