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
        // Imprime la contraseña recibida para verificar en la consola
        System.out.println(request.getPassword());

        // Intenta autenticar las credenciales proporcionadas
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // Si la autenticación es exitosa, continua obteniendo los detalles del usuario
        System.out.println("ha pasado el filtro");
        UserDetails userDetails = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Castear UserDetails a Usuario para obtener el ID, nombre y correo
        Usuario usuario = (Usuario) userDetails;

        // Obtiene el token JWT
        String token = jwtService.getToken(userDetails);

        // Imprime el token generado para verificar en la consola
        System.out.println("Token generado:");
        System.out.println(token);

        // Construye la respuesta de autenticación con el token, ID, nombre y correo del usuario
        return AuthResponse.builder()
                .token(token)
                .userId(usuario.getId())
                .userName(usuario.getName()) // Nombre del usuario
                .userEmail(usuario.getEmail()) // Correo electrónico del usuario
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