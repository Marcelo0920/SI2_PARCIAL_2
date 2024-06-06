package com.parcial2.si2.utils;

import com.parcial2.si2.model.Usuario;
import com.parcial2.si2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class Utils {

    @Autowired
    private UserService userService;

    public String generateUserCode(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();

        StringBuilder code = new StringBuilder();

        code.setLength(0);

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            code.append(randomChar);
        }

        Optional<Usuario> user = userService.getUserByUserCode(code.toString());

        if(user.isEmpty()){
            return code.toString();
        } else{
          generateUserCode();
        }
        return "0000";
    }
}
