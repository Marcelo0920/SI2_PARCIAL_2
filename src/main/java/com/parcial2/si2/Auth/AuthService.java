package com.parcial2.si2.Auth;


import com.parcial2.si2.builder.AdminBuilder;
import com.parcial2.si2.builder.TeacherBuilder;
import com.parcial2.si2.dto.RegisterRequest;
import com.parcial2.si2.model.Admin;
import com.parcial2.si2.model.Role;
import com.parcial2.si2.model.Teacher;
import com.parcial2.si2.model.Usuario;
import com.parcial2.si2.repository.UserRepository;
import com.parcial2.si2.service.JwtService;
import com.parcial2.si2.utils.Utils;
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

    @Autowired
    private Utils utils;


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
        String userCode = utils.generateUserCode();
        Admin admin = new AdminBuilder()
                .name(request.getName())
                .age(request.getAge())
                .password(passwordEncoder.encode( request.getPassword()))
                .email(request.getEmail())
                .userCode(userCode)
                .phoneNumber(request.getPhoneNumber())
                .build();

        System.out.println(admin);

        userRepository.save(admin);

        return AuthResponse.builder()
                .token(jwtService.getToken(admin))
                .build();

    }

    public AuthResponse registerTeacher(RegisterRequest request){
        String userCode = utils.generateUserCode();
        Teacher teacher = new TeacherBuilder()
                .name(request.getName())
                .age(request.getAge())
                .password(passwordEncoder.encode( request.getPassword()))
                .email(request.getEmail())
                .userCode(userCode)
                .phoneNumber(request.getPhoneNumber())
                .build();


        userRepository.save(teacher);

        return AuthResponse.builder()
                .token(jwtService.getToken(teacher))
                .build();

    }
}