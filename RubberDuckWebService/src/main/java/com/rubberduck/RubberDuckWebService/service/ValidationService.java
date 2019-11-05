package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.User;

import java.security.NoSuchAlgorithmException;

public interface ValidationService {

    String userSignIn(User user, String password) throws NoSuchAlgorithmException;

    String getAccessToken(User user, String userType);

    String getUserId(String accessToken, String desiredUserCategory);
}
