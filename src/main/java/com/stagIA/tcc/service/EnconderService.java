package com.stagIA.tcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EnconderService {


    @Autowired
    private PasswordEncoder encoder;



    public String encoder(String senha){

        return encoder.encode(senha);

    }

    public boolean matches(String senhaDigitada, String senhaBanco){

        return encoder.matches(senhaDigitada, senhaBanco);

    }
}
