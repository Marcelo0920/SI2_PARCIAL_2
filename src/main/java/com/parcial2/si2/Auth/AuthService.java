package com.parcial2.si2.Auth;


import com.parcial2.si2.dto.RegisterRequest;
import com.parcial2.si2.model.Role;
import com.parcial2.si2.model.Usuario;
import com.parcial2.si2.repository.UserRepository;
import com.parcial2.si2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Método login
    public AuthResponse login(LoginRequest request) {

        System.out.println(request.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        System.out.println("ha pasado el filtro");
        UserDetails user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        System.out.println("Aca viene el token encontrado");
        System.out.println(token);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        Usuario user = Usuario.builder()
                .name(request.getName())
                .age(request.getAge())
                .password(passwordEncoder.encode( request.getPassword()))
                .email(request.getEmail())
                .role(Role.ADMIN)
                .phoneNumber(request.getPhoneNumber())
                .build();

        System.out.println(user);

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

    public AuthResponse registerTeacher(RegisterRequest request){
        Usuario user = Usuario.builder()
                .name(request.getName())
                .age(request.getAge())
                .password(passwordEncoder.encode( request.getPassword()))
                .email(request.getEmail())
                .role(Role.TEACHER)
                .phoneNumber(request.getPhoneNumber())
                .build();

        System.out.println(user);

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}