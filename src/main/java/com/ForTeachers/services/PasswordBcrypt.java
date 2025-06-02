package com.forteachers.services;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordBcrypt {

    private ValidPassword validPassword;
    private final PasswordEncoder encoder;

    public PasswordBcrypt(PasswordEncoder passwordEncoder, ValidPassword validPassword) {
        this.encoder = passwordEncoder;
        this.validPassword = validPassword;
    }


    public String hashing(String password) throws IllegalAccessException {

        if(this.validPassword.validPassoword(password)){
            return encoder.encode(password);
        }

        return null;
    }

    public Boolean matches(String password, String userPassword){
        return encoder.matches(password, userPassword);
    }
}
