package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.User;

import java.security.NoSuchAlgorithmException;

public interface ValidationService {

    static String userSignIn(User user, String password) throws NoSuchAlgorithmException {

        String hashedPassword = user.hashPassword(password);

        if(hashedPassword.equals(user.getPassword())) {
            return "Login successful";
        } else {
            throw new IllegalArgumentException();
        }
    }

}
